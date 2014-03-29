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

import com.packt.pfblueprints.dao.InvestmentSummaryDAO;
import com.packt.pfblueprints.model.AccountSummary;
import com.packt.pfblueprints.model.InvestmentSummary;


@ManagedBean
@ViewScoped
public class InvestmentSummaryController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<InvestmentSummary> investmentsInfo=new ArrayList<InvestmentSummary>();
	private InvestmentSummary investmentobj=new InvestmentSummary();
	
	//private Dealer dealerobj=new Dealer();
	InvestmentSummaryDAO dao = new InvestmentSummaryDAO();
	
	@PostConstruct
	public void init() { 
		
		investmentsInfo=dao.getAllInvestments();
		FacesContext.getCurrentInstance().renderResponse();
		
	}
	
	public String storeSelectedInvestornum(){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("investmentNumber", investmentobj.getInvestmentNumber());
		return "transactionsummary.xhtml?faces-redirect=true";
	}

	public List<InvestmentSummary> getInvestmentsInfo() {
		return investmentsInfo;
	}

	public void setInvestmentsInfo(List<InvestmentSummary> investmentsInfo) {
		this.investmentsInfo = investmentsInfo;
	}

	public InvestmentSummary getInvestmentobj() {
		return investmentobj;
	}

	public void setInvestmentobj(InvestmentSummary investmentobj) {
		this.investmentobj = investmentobj;
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
