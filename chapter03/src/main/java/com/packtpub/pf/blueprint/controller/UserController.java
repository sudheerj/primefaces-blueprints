package com.packtpub.pf.blueprint.controller;

import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created with IntelliJ IDEA.
 * User: psramkumar
 * Date: 1/8/14
 * Time: 9:00 AM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class UserController {
    private static final Logger _log = Logger.getLogger(UserController.class);

    private String loginStatus;
    private String userName;
    private String password;
    private boolean loggedIn;

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String loginMeIn() {
        _log.info("Trying to Logging in now with UserName : " + userName);
        boolean loggedIn = "admin".equals(getUserName()) && "admin".equals(getPassword());
        this.loginStatus = loggedIn ? "Login Successful" : "Login failed";
        return loggedIn ? "/welcome.jsf?faces-redirect=true" : "/logmein.jsf?error=true";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
