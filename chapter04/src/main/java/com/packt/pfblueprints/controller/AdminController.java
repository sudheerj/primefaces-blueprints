package com.packt.pfblueprints.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;


@ManagedBean
@ViewScoped
public class AdminController implements Serializable {

	private static final long serialVersionUID = 1L;

	private String aboutus;
	private String contactus;
	private String disclaimer;
	private List<String> images;
	public AdminController() {
		init();
	}
	public void init(){
		images = new ArrayList<String>();
		 
	    for(int i=1;i<=10;i++) {
	            images.add("galleria" + i + ".jpg");
	     }
	        
		aboutus="This website is used to track the mutual fund details such as service center,dealer,advisor,representative and account summary details";
		contactus="Packt Publishing Limited,2nd Floor, Livery Place,35 Livery Street,BirminghamB3 2PB";
		disclaimer="The information contained in this website is for information purposes only, and does not constitute, nor is it intended to constitute, the provision of financial product advice.";
	}
	public String getAboutus() {
		return aboutus;
	}
	public void setAboutus(String aboutus) {
		this.aboutus = aboutus;
	}
	public String getContactus() {
		return contactus;
	}
	public void setContactus(String contactus) {
		this.contactus = contactus;
	}
	public String getDisclaimer() {
		return disclaimer;
	}
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	public List<String> getImages() {
		return images;
	}
	
}