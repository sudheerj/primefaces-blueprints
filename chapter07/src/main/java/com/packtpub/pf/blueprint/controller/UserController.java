package com.packtpub.pf.blueprint.controller;

import com.packtpub.pf.blueprint.persistence.entity.Movie;
import com.packtpub.pf.blueprint.persistence.entity.User;
import com.packtpub.pf.blueprint.service.DAOService;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.primefaces.event.CaptureEvent;

import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ramkumar Pillai
 * Date: 1/8/14
 * Time: 9:00 AM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class UserController implements Serializable {
    private static final Logger _log = Logger.getLogger(UserController.class);
    @Getter
    @Setter
    private boolean loggedIn = false;
    @Getter
    @Setter
    private User user = new User();
    @Getter
    @Setter
    private User userNow = new User();
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private List<String> photos = new ArrayList<String>();

    private static DAOService ds = new DAOService();

    @PostConstruct
    public void dummyData() {
        User us = new User();
        us.setUsername("admin");
        us.setPassword("admin");
        us.setFirstName("Ram");
        us.setLastName("Pillai");
        User ut = ds.validateUser("admin", "admin");
        if(ut == null){
            ds.addOrUpdateEntity(us);
        }
    }

    Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();

    public String loginMeIn() throws ServletException {
        userNow = ds.validateUser(username, password);
        _log.info("Trying to Logging in now with UserName : " + userNow.getUsername());
        this.loggedIn = userNow != null;
        if (request instanceof HttpServletRequest) {
            HttpServletRequest rq = (HttpServletRequest) request;
            rq.setAttribute("userName", username);
        }
        return this.loggedIn ? "/dashboard.jsf?faces-redirect=true" : "/logmein.jsf?error=true";
    }

    public String loginMeOut() throws ServletException {
        _log.info("Trying to LogOut now.....");
        this.loggedIn = false;
        user = null;
        userNow = null;
        if (request instanceof HttpServletRequest) {
            HttpServletRequest rq = (HttpServletRequest) request;
            rq.logout();
        }
        return "/welcome.jsf?faces-redirect=true";
    }

    public void oncapture(CaptureEvent captureEvent) {
        String photo = getRandomImageName();
        this.user.setAvatar(photo);
        byte[] data = captureEvent.getData();

        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String newFileName = servletContext.getRealPath("") + File.separator + "photocam" + File.separator + photo + ".png";

        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
        } catch (Exception e) {
            throw new FacesException("Error in writing captured image.");
        }
    }

    private String getRandomImageName() {
        int i = (int) (Math.random() * 10000000);
        return String.valueOf(i);
    }

    public void submit(ActionEvent event) {
        ds.addOrUpdateEntity(user);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "User Successfully added...", "User Successfully added...");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        _log.info("Done ....");
        prepareAddNewUser();

    }

    public void prepareAddNewUser() {
        user = new User();

    }

    public List<Movie> getAllMyMovies() {
        _log.info("Current User ID here --> " + userNow.getId());
        return ds.getMoviesByUserId(userNow);
    }

}

