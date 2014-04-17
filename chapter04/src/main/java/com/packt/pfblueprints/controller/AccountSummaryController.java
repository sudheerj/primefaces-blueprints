package com.packt.pfblueprints.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.packt.pfblueprints.dao.AccountsDAO;
import com.packt.pfblueprints.dao.DealerDAO;
import com.packt.pfblueprints.model.AccountSummary;
import com.packt.pfblueprints.model.Dealer;

@ManagedBean
@ViewScoped
public class AccountSummaryController  implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private List<AccountSummary> accountsInfo=new ArrayList<AccountSummary>();
	
	private AccountSummary accountobj=new AccountSummary();
	AccountsDAO dao = new AccountsDAO();
	private LazyDataModel<AccountSummary> lazyAccSummaryDataModel;
	
	@PostConstruct
	public void init() { 
		
		lazyAccSummaryDataModel = new LazyAccountSummaryDataModel(){
			@Override
			public List<AccountSummary> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
				String sortOrderValue=null;
				if(sortField==null){
					sortField="investorName";
				}
				if(sortOrder.ASCENDING.equals("A")){
					sortOrderValue="ASC";
				}
				else if(sortOrder.DESCENDING.equals("D")){
					sortOrderValue="DSC";
				}
				else{
					sortOrderValue="ASC";
				}
				
				accountsInfo=dao.getAllAccounts(first,pageSize,sortField,sortOrderValue,filters); 
				this.setRowCount(10);
				return accountsInfo;
			}
		};
		
	}
	
	/* public void deleteDealer(){
		dealerInfo=dao.deleteDealer(dealerobj);
	}

	public dealer getDealerobj() {
		return dealerobj;
	} */

	
	public LazyDataModel<AccountSummary> getLazyAccSummaryDataModel() {
		return lazyAccSummaryDataModel;
	}

	

	public void setLazyAccSummaryDataModel(LazyDataModel<AccountSummary> lazyAccSummaryDataModel) {
		this.lazyAccSummaryDataModel = lazyAccSummaryDataModel;
	}

	public AccountSummary getAccountobj() {
		return accountobj;
	}

	public void setAccountobj(AccountSummary accountobj) {
		this.accountobj = accountobj;
	}
	
	
}
