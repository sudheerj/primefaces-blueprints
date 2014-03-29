package com.packt.pfblueprints.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.packt.pfblueprints.dao.dealerDAO;
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
		
		dealerInfo=dao.getAllDealers();
		FacesContext.getCurrentInstance().renderResponse();
		
	}
	
	public void deleteDealer(){
		dealerInfo=dao.deleteDealer(dealerobj);
	}

	public dealer getDealerobj() {
		return dealerobj;
	}

	public void setDealerobj(Dealer dealerobj) {
		this.dealerobj = dealerobj;
	}

	public List<Dealer> getDealerInfo() {
		return dealerInfo;
	}

	public void setdealerInfo(List<dealer> dealerInfo) {
		this.dealerInfo = dealerInfo;
	}
	

}
