package com.packt.pfblueprints.model;

import java.io.Serializable;

public class InvestmentSummary implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String fundname;
	private String investmentNumber;
	private String investmentManager;
	private String marketingCompany;
	private String avgUnitPrice;
	private Double marketValue1;
	private Double marketValue2;
	private Double marketValue3;
	private Double marketValue4;
	private Double marketValue5;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFundname() {
		return fundname;
	}
	public void setFundname(String fundname) {
		this.fundname = fundname;
	}
	
	public String getInvestmentNumber() {
		return investmentNumber;
	}
	public void setInvestmentNumber(String investmentNumber) {
		this.investmentNumber = investmentNumber;
	}
	public String getInvestmentManager() {
		return investmentManager;
	}
	public void setInvestmentManager(String investmentManager) {
		this.investmentManager = investmentManager;
	}
	public String getMarketingCompany() {
		return marketingCompany;
	}
	public void setMarketingCompany(String marketingCompany) {
		this.marketingCompany = marketingCompany;
	}
	public String getAvgUnitPrice() {
		return avgUnitPrice;
	}
	public void setAvgUnitPrice(String avgUnitPrice) {
		this.avgUnitPrice = avgUnitPrice;
	}
	public Double getMarketValue1() {
		return marketValue1;
	}
	public void setMarketValue1(Double marketValue1) {
		this.marketValue1 = marketValue1;
	}
	public Double getMarketValue2() {
		return marketValue2;
	}
	public void setMarketValue2(Double marketValue2) {
		this.marketValue2 = marketValue2;
	}
	public Double getMarketValue3() {
		return marketValue3;
	}
	public void setMarketValue3(Double marketValue3) {
		this.marketValue3 = marketValue3;
	}
	public Double getMarketValue4() {
		return marketValue4;
	}
	public void setMarketValue4(Double marketValue4) {
		this.marketValue4 = marketValue4;
	}
	public Double getMarketValue5() {
		return marketValue5;
	}
	public void setMarketValue5(Double marketValue5) {
		this.marketValue5 = marketValue5;
	}
	
}
