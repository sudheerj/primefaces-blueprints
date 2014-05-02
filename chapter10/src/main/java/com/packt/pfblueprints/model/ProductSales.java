package com.packt.pfblueprints.model;

import java.io.Serializable;

public class ProductSales implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private String prodtype;
	private String qty1;
	private String qty2;
	private String preYearSales;
	private String curYearSales;
	private String gainloss;

	public ProductSales(String prodtype, String qty1, String qty2,
			String preYearSales, String curYearSales, String gainloss) {
		super();
		this.prodtype = prodtype;
		this.qty1 = qty1;
		this.qty2 = qty2;
		this.preYearSales = preYearSales;
		this.curYearSales = curYearSales;
		this.gainloss = gainloss;
	}

	public String getProdtype() {
		return prodtype;
	}

	public void setProdtype(String prodtype) {
		this.prodtype = prodtype;
	}

	public String getQty1() {
		return qty1;
	}

	public void setQty1(String qty1) {
		this.qty1 = qty1;
	}

	public String getQty2() {
		return qty2;
	}

	public void setQty2(String qty2) {
		this.qty2 = qty2;
	}

	public String getPreYearSales() {
		return preYearSales;
	}

	public void setPreYearSales(String preYearSales) {
		this.preYearSales = preYearSales;
	}

	public String getCurYearSales() {
		return curYearSales;
	}

	public void setCurYearSales(String curYearSales) {
		this.curYearSales = curYearSales;
	}

	public String getGainloss() {
		return gainloss;
	}

	public void setGainloss(String gainloss) {
		this.gainloss = gainloss;
	}

	
}
