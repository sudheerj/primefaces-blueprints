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

import com.packt.pfblueprints.dao.TransactionSummaryDAO;
import com.packt.pfblueprints.model.TransactionSummary;


@ManagedBean
@ViewScoped
public class TransactionSummaryController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<TransactionSummary> transactionsInfo=new ArrayList<TransactionSummary>();
	
	private TransactionSummary transactionobj=new TransactionSummary();
	TransactionSummaryDAO dao = new TransactionSummaryDAO();
	
	@PostConstruct
	public void init() { 
		
		transactionsInfo=dao.getAllTransactions();
		FacesContext.getCurrentInstance().renderResponse();
		
	}

	public String displayTransactions(){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("investmentNumber", "");
		return "accountsummary.xhtml?faces-redirect=true";
	}
	
	public List<TransactionSummary> getTransactionsInfo() {
		return transactionsInfo;
	}

	public void setTransactionsInfo(List<TransactionSummary> transactionsInfo) {
		this.transactionsInfo = transactionsInfo;
	}
	
	/*public void deleteDealer(){
		servicecenterInfo=dao.deleteDealer(servicecenterobj);
	}*/

	
	

}
