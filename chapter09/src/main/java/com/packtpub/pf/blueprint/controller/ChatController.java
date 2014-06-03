package com.packtpub.pf.blueprint.controller;

import java.io.Serializable;

import lombok.Data;
import org.primefaces.context.RequestContext;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
/**
 * Created with IntelliJ IDEA.
 * User: Ramkumar Pillai
 * Date: 1/8/14
 * Time: 9:00 AM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
@Data
public class ChatController implements Serializable {
    
    //private final PushContext pushContext = PushContextFactory.getDefault().getPushContext();

    private final EventBus eventBus = EventBusFactory.getDefault().eventBus();

    @ManagedProperty("#{chatUsers}")
    private ChatUsers users;

	private String privateMessage;
    
    private String globalMessage;
	
	private String username;
	
	private boolean loggedIn;
    
    private String privateUser;
    
    private final static String CHANNEL = "/{room}/";

    
	public String getUsername() {
        Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request instanceof HttpServletRequest) {
            HttpServletRequest rq = (HttpServletRequest) request;
            Object o = rq.getAttribute("username");
           if(null != o && username == null) {
               username = o.toString();
           }

        }
        return username;
	}

	public void sendGlobal() {
        eventBus.publish(CHANNEL + "*", username + ": " + globalMessage);
		
		globalMessage = null;
	}
    
    public void sendPrivate() {
        eventBus.publish(CHANNEL + privateUser, "[PM] " + username + ": " + privateMessage);
        
        privateMessage = null;
    }

    private boolean propogate = false;

    public void loginNow(String user){
        propogate = true;
        setUsername(user);
        users.add(username);
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('subscriber').connect('/" + username + "')");
        loggedIn = true;
    }
	public void login() {
        RequestContext requestContext = RequestContext.getCurrentInstance();

		if(users.contains(username)) {
            loggedIn = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username taken", "Try with another username."));
            requestContext.update("growl");
        }
        else {
            users.add(username);
            requestContext.execute("PF('subscriber').connect('/" + username + "')");
            loggedIn = true;
        }
	}
    
    public void disconnect() {
        //remove user and update ui
        users.remove(username);
        RequestContext.getCurrentInstance().update("form:users");
        
        //push leave information
        eventBus.publish(CHANNEL + "*", username + " left the channel.");
        
        //reset state
        propogate = false;
        loggedIn = false;
        username = null;
    }
}
