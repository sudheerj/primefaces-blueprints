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

public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String prodname;
	private String model;
	private int rating;
	private String discount;
	
	public Product(String prodname, String model, int rating, String discount) {
		super();
		this.prodname = prodname;
		this.model = model;
		this.rating = rating;
		this.discount = discount;
	}
	
	public String getProdname() {
		return prodname;
	}

	public void setProdname(String prodname) {
		this.prodname = prodname;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getRating() {
		return rating;
	}
}
