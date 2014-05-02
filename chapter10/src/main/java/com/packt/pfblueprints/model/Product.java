package com.packt.pfblueprints.model;

import java.io.Serializable;


public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String prodname;
	private String prodcat;
	private String model;
	private int rating;
	private String discount;
	
	public Product(String prodname,String prodcat, String model, int rating, String discount) {
		super();
		this.prodname = prodname;
		this.prodcat = prodcat;
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
	
	public String getProdcat() {
		return prodcat;
	}

	public void setProdcat(String prodcat) {
		this.prodcat = prodcat;
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

	public int getRating() {
		return rating;
	}
}
