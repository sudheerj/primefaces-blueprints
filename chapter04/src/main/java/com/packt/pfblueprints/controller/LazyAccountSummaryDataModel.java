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
        List<AccountSummary> data = new ArrayList<AccountSummary>();
 
        //filter
        for(AccountSummary accountSummaryObj : accountsInfo) {
            boolean match = true;
 
            for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                try {
                    String filterProperty = it.next();
                    String filterValue = filters.get(filterProperty).toString();
                    String fieldValue = String.valueOf(accountSummaryObj.getClass().getField(filterProperty).get(accountSummaryObj));
 
                    if(filterValue == null || fieldValue.startsWith(filterValue)) {
                        match = true;
                    }
                    else {
                        match = false;
                        break;
                    }
                } catch(Exception e) {
                    match = false;
                } 
            }
 
            if(match) {
                data.add(accountSummaryObj);
            }
        }
 
        //sort
        if(sortField != null) {
           // Collections.sort(data, new LazySorter(sortField, sortOrder));
        }
 
        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);
 
        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
}