package com.packt.pfblueprints.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.packt.pfblueprints.dao.ServiceCenterDAO;
import com.packt.pfblueprints.model.ServiceCenter;

@ManagedBean
@ViewScoped
public class AccountSummaryController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<AccountSummary> accountsInfo=new ArrayList<AccountSummary>();
	
	private AccountSummary accountobj=new AccountSummary();
	AccountSummaryDAO dao = new AccountSummaryDAO();
	
	@PostConstruct
	public void init() { 
		
		accountsInfo=dao.getAllAccounts();
		
	}
	
	public void deleteDealer(){
		accountsInfo=dao.deleteDealer(accountobj);
	}

	public AccountsInfo getAccountsInfo(){
		return accountsInfo;
	}

	public void setAccountsInfo(AccountsInfo accountsInfo){
		this.accountsInfo=accountsInfo;
	}
}
