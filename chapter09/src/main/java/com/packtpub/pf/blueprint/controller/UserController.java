package com.packtpub.pf.blueprint.controller;

import com.packtpub.pf.blueprint.persistence.entity.Profile;
import com.packtpub.pf.blueprint.persistence.entity.UserPost;
import com.packtpub.pf.blueprint.service.DAOService;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
    private Profile user = new Profile();
    @Getter
    @Setter
    private Profile userNow = new Profile();
    @Getter
    @Setter
    private String re_email;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private List<UserPost> myPosts = new ArrayList<>();
    @Getter
    @Setter
    private LazyDataUserPostModel lazyModel;
    @Getter
    @Setter
    private UserPost userPost = new UserPost();

    private static DAOService ds = new DAOService();

    public void saveUserPost(){
        userPost.setUser(userNow);
        userPost.setCreateDate(new Date());
        userPost.setPostType("Type");
        ds.addOrUpdateEntity(userPost);
        userPost = new UserPost();
        myPosts = getAllMyPosts();
    }

    @PostConstruct
    public void dummyData() {
        if(loggedIn) {
            myPosts = getAllMyPosts();
        }else{
            ds.validateUser("admin", "admin");
        }
        prepareAddNewUser();
        _log.info("done with sample data...");
        myPosts = getAllMyPosts();
        lazyModel = new LazyDataUserPostModel(myPosts);
    }

    Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();

    public String loginMeIn() throws ServletException {
        userNow = ds.validateUser(username, password);
        _log.info("Trying to Logging in now with UserName : " + username);
        this.loggedIn = userNow != null;
        if (request instanceof HttpServletRequest) {
            HttpServletRequest rq = (HttpServletRequest) request;
            rq.setAttribute("username", username);
        }
        return this.loggedIn ? "/welcome.jsf?faces-redirect=true" : "/welcome.jsf?error=true";
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

    private String getRandomImageName() {
        int i = (int) (Math.random() * 10000000);
        return String.valueOf(i);
    }

    public void submit(ActionEvent event) {
        ds.addOrUpdateEntity(user);
        _log.info("Done ....");
        prepareAddNewUser();

    }

    public void prepareAddNewUser() {
        user = new Profile();

    }

    public List<UserPost> getAllMyPosts() {
        _log.info("Current User ID here --> " + userNow.getId());
        List<UserPost> list = new ArrayList<>();
        for(long i = 0; i<300; i++){
            list.add(new UserPost(i, getRandomImageName(), getRandomImageName(), getRandomImageName(), new Date(), null));
        }
        return list; //ds.getUserPostForUser(userNow);
    }


}

