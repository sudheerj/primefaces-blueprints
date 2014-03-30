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

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;

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
	private CartesianChartModel linearModel; 
	
	//private Dealer dealerobj=new Dealer();
	InvestmentSummaryDAO dao = new InvestmentSummaryDAO();
	
	@PostConstruct
	public void init() { 
		
		investmentsInfo=dao.getAllInvestments();
		createLinearModel();
		
	}
	
	public String storeSelectedInvestornum(){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("investmentNumber", investmentobj.getInvestmentNumber());
		return "transactionsummary.xhtml?faces-redirect=true";
	}
	
	public String displayAllInvestments(){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("investmentNumber", "");
		return "investmentsummary.xhtml?faces-redirect=true";
	}

	 private void createLinearModel() {  
	        linearModel = new CartesianChartModel(); 
	        
	        for(InvestmentSummary obj:investmentsInfo){
	        	 LineChartSeries series = new LineChartSeries();
	        	 series.setLabel(obj.getFundname());  
	        	 
	        	 series.set("MarketValue1", obj.getMarketValue1());
	        	 series.set("MarketValue2", obj.getMarketValue2());
	        	 series.set("MarketValue3", obj.getMarketValue3());
	        	 series.set("MarketValue4", obj.getMarketValue4());
	        	 series.set("MarketValue5", obj.getMarketValue5());
	        	 
	        	 linearModel.addSeries(series);
	        }
	         
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

	public CartesianChartModel getLinearModel() {
		return linearModel;
	}

	public void setLinearModel(CartesianChartModel linearModel) {
		this.linearModel = linearModel;
	}
	
	
	

}
