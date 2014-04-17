package com.packt.pfblueprints.controller;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.packt.pfblueprints.model.AccountSummary;
 
public class LazyAccountSummaryDataModel extends LazyDataModel<AccountSummary> {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = -6407210686016928436L;
	private List<AccountSummary> accountsInfo=new ArrayList<AccountSummary>();
     
    public LazyAccountSummaryDataModel() {
		super();
	}


	@Override
    public AccountSummary getRowData(String rowKey) {
        for(AccountSummary accountSummaryObj : accountsInfo) {
            if(accountSummaryObj.getAccountNumber().equals(rowKey))
                return accountSummaryObj;
        }
 
        return null;
    }
 
    @Override
    public Object getRowKey(AccountSummary accountSummaryobj) {
        return accountSummaryobj.getAccountNumber();
    }
 
    @Override
    public List<AccountSummary> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
    	return new ArrayList<AccountSummary>();
    }
}