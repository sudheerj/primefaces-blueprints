package com.packt.pfblueprints.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.packt.pfblueprints.dao.DealerDAO;
import com.packt.pfblueprints.model.Dealer;

@ManagedBean
@ViewScoped
public class DealerController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Dealer> dealerInfo=new ArrayList<Dealer>();
	
	private Dealer dealerobj=new Dealer();
	DealerDAO dao = new DealerDAO();
	
	@PostConstruct
	public void init() { 
		
		dealerInfo=dao.getAllAdvisors();
		
	}
	
	public String storeSelectedAdvisor(){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("advisornumber", dealerobj.getAdvisornumber());
		return "advisorinfo.xhtml?faces-redirect=true";
	}
	
	/* public void deleteDealer(){
		dealerInfo=dao.deleteDealer(dealerobj);
	}
*/
	public Dealer getDealerobj() {
		return dealerobj;
	} 

	public void setDealerobj(Dealer dealerobj) {
		this.dealerobj = dealerobj;
	}

	public List<Dealer> getDealerInfo() {
		return dealerInfo;
	}

	public void setdealerInfo(List<Dealer> dealerInfo) {
		this.dealerInfo = dealerInfo;
	}
	

}
