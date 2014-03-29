package com.packt.pfblueprints.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.packt.pfblueprints.dao.InvestmentSummaryDAO;
import com.packt.pfblueprints.model.InvestmentSummary;


@ManagedBean
@ViewScoped
public class InvestmentSummaryController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<InvestmentSummary> investmentsInfo=new ArrayList<InvestmentSummary>();
	
	//private Dealer dealerobj=new Dealer();
	InvestmentSummaryDAO dao = new InvestmentSummaryDAO();
	
	@PostConstruct
	public void init() { 
		
		investmentsInfo=dao.getAllInvestments();
		FacesContext.getCurrentInstance().renderResponse();
		
	}

	public List<InvestmentSummary> getInvestmentsInfo() {
		return investmentsInfo;
	}

	public void setInvestmentsInfo(List<InvestmentSummary> investmentsInfo) {
		this.investmentsInfo = investmentsInfo;
	}
	
	/*public void deleteDealer(){
		dealerInfo=dao.deleteDealer(dealerobj);
	}
*/
	/*public dealer getDealerobj() {
		return dealerobj;
	}

	public void setDealerobj(Dealer dealerobj) {
		this.dealerobj = dealerobj;
	}*/

	
	

}
