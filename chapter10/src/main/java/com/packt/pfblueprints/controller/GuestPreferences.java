package com.packt.pfblueprints.controller;

import java.io.Serializable;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@SessionScoped
public class GuestPreferences implements Serializable {

	private String theme = "home"; //default

	public String getTheme() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if(params.containsKey("theme")) {
			theme = params.get("theme");
		}
		
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
}
