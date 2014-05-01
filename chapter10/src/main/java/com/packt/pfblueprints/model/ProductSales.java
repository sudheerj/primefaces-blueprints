package com.packt.pfblueprints.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.packt.pfblueprints.controller.Dobule;

public class ProductSales implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private String prodtype;
	private int qty;
	private Dobule preYearSales;
	private Dobule curYearSales;
	private String gainloss;

	public ProductSales(String prodtype, int qty, Dobule preYearSales,
			Dobule curYearSales, String gainloss) {
		super();
		this.prodtype = prodtype;
		this.qty = qty;
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

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public Dobule getPreYearSales() {
		return preYearSales;
	}

	public void setPreYearSales(Dobule preYearSales) {
		this.preYearSales = preYearSales;
	}

	public Dobule getCurYearSales() {
		return curYearSales;
	}

	public void setCurYearSales(Dobule curYearSales) {
		this.curYearSales = curYearSales;
	}

	public String getGainloss() {
		return gainloss;
	}

	public void setGainloss(String gainloss) {
		this.gainloss = gainloss;
	}
}
