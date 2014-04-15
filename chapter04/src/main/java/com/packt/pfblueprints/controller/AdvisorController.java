package com.packt.pfblueprints.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.packt.pfblueprints.dao.AdvisorDAO;
import com.packt.pfblueprints.model.Advisor;

@ManagedBean
@ViewScoped
public class AdvisorController implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Advisor>  advisorInfo=new ArrayList<Advisor>();
	
    Advisor advisorobj=new Advisor();
	AdvisorDAO dao = new AdvisorDAO();
	
	@PostConstruct
	public void init() { 
		
		advisorInfo=dao.getAllRepresentatives();
	}
	
	public String navigateAccountSummary(){
		return "accountsinfo.xhtml?faces-redirect=true";
	}

	
	public List<Advisor> getAdvisorInfo() {
		return advisorInfo;
	}

	public void setAdvisorInfo(List<Advisor> advisorInfo) {
		this.advisorInfo = advisorInfo;
	}

	public Advisor getAdvisorobj() {
		return advisorobj;
	}

	public void setAdvisorobj(Advisor advisorobj) {
		this.advisorobj = advisorobj;
	}
	
	/* public void deleteDealer(){
		dealerInfo=dao.deleteDealer(dealerobj);
	}

	public dealer getDealerobj() {
		return dealerobj;
	} */

	
	

}
