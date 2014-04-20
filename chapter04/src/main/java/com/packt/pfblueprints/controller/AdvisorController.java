package com.packt.pfblueprints.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.packt.pfblueprints.dao.AdvisorDAO;
import com.packt.pfblueprints.model.Representative;

@ManagedBean
@ViewScoped
public class AdvisorController implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Representative>  advisorInfo=new ArrayList<Representative>();
	Representative repobj=new Representative();
	AdvisorDAO dao = new AdvisorDAO();
	
	@PostConstruct
	public void init() { 
		
		advisorInfo=dao.getAllRepresentatives();
	}
	
	public String navigateAccountSummary(){
		return "accountsinfo.xhtml?faces-redirect=true";
	}
	
	public List<Representative> getAdvisorInfo() {
		return advisorInfo;
	}

	public void setAdvisorInfo(List<Representative> advisorInfo) {
		this.advisorInfo = advisorInfo;
	}

	public Representative getRepobj() {
		return repobj;
	}

	public void setRepobj(Representative repobj) {
		this.repobj = repobj;
	}

}
