package com.packtpub.pf.blueprint;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


public class FacesUtil {

	public static void addError(String message) {
		addError(message, null);
	}

	public static void addSuccessMessage(String message) {
		addSuccessMessage(message, null);
	}

	public static void addSuccessMessage(String message, String compId) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				message, message);
		FacesContext.getCurrentInstance().addMessage(compId, msg);
	}

	
	public static void addError(String message, String compId) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				message, message);

		FacesContext.getCurrentInstance().addMessage(compId, msg);
	}
	
	
	
}
