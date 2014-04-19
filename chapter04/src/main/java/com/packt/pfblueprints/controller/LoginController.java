package com.packt.pfblueprints.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.packt.pfblueprints.dao.LoginDAO;


@ManagedBean
@ViewScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String userrole="S";

	public LoginController() {
		super();
	}

	public String validateUser() throws SQLException {
		FacesMessage msg = null;
		boolean isValidUser = false;
		LoginDAO dao = new LoginDAO();
		isValidUser = dao.validateUser(username, password,userrole);
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		 
		if (isValidUser) {
			if(userrole.equalsIgnoreCase("S")){
				return "/views/servicecenterinfo?faces-redirect=true";
				}
			else if(userrole.equalsIgnoreCase("D")){
				sessionMap.put("dealertinnumber", username);
				return "/views/dealerinfo?faces-redirect=true";
				}
			else if(userrole.equalsIgnoreCase("D")){
				sessionMap.put("advisornumber", username);
				return "/views/advisorinfo?faces-redirect=true";
				}
			else {
				return "/views/accountsinfo?faces-redirect=true";
				}
			
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
					"Invalid credentials");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}

	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

}