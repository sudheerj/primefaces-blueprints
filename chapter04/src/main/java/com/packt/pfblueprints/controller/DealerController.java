package com.packt.pfblueprints.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.packt.pfblueprints.dao.DealerDAO;
import com.packt.pfblueprints.model.Advisor;

@ManagedBean
@ViewScoped
public class DealerController implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Advisor> dealerInfo=new ArrayList<Advisor>();
	private SelectItem[] managementcompanies;
	private String[] branches;
	private Advisor advisorobj=new Advisor();
	DealerDAO dao = new DealerDAO();
	
	@PostConstruct
	public void init() { 
		dealerInfo=dao.getAllAdvisors();
		createFilterCompanies();
		createFilterBranches();
	}
	
	public String storeSelectedAdvisor(){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("advisornumber", advisorobj.getAdvisornumber());
		return "advisorinfo.xhtml?faces-redirect=true";
	}
	
	public boolean filterByRevenue(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            return true;
        }
         
        if(value == null) {
            return false;
        }
         
        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
    }
	
	 private void createFilterCompanies()  {
	        managementcompanies = new SelectItem[5];

	        managementcompanies[0] = new SelectItem("", "Select");
	        managementcompanies[1] = new SelectItem("M&M", "M&M");
	        managementcompanies[2] = new SelectItem("LPL", "LPL");
	        managementcompanies[3] = new SelectItem("JK", "JK");
	        managementcompanies[4] = new SelectItem("IMI", "IMI");
	        
	    }
	 
	 private void createFilterBranches()  {
	        branches = new String[4];

	        branches[0] = "California";
	        branches[1] = "Washington";
	        branches[2] = "Newyork";
	        branches[3] = "Chestor";
	        
	    }
	
	public Advisor getAdvisorobj() {
		return advisorobj;
	}

	public void setAdvisorobj(Advisor advisorobj) {
		this.advisorobj = advisorobj;
	}

	public List<Advisor> getDealerInfo() {
		return dealerInfo;
	}

	public void setdealerInfo(List<Advisor> dealerInfo) {
		this.dealerInfo = dealerInfo;
	}

	public SelectItem[] getManagementcompanies() {
		return managementcompanies;
	}

	public void setManagementcompanies(SelectItem[] managementcompanies) {
		this.managementcompanies = managementcompanies;
	}

	public String[] getBranches() {
		return branches;
	}

	public void setBranches(String[] branches) {
		this.branches = branches;
	}

}
