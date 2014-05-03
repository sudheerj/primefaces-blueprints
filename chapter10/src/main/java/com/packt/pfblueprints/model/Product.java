package com.packt.pfblueprints.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
	private Long id;
	
	private String prodname;
	private String prodimage;
	private String prodcat;
	private int rating;
	private String discount;
	private String price;
	

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Product(String prodname,String prodimage,String prodcat,int rating, String discount,String price) {
		super();
		this.prodname = prodname;
		this.prodimage = prodimage;
		this.prodcat = prodcat;
		this.rating = rating;
		this.discount = discount;
		this.price = price;
	}
	
	public String getProdname() {
		return prodname;
	}

	public void setProdname(String prodname) {
		this.prodname = prodname;
	}
	
	public String getProdimage() {
		return prodimage;
	}

	public void setProdimage(String prodimage) {
		this.prodimage = prodimage;
	}
	
	public String getProdcat() {
		return prodcat;
	}

	public void setProdcat(String prodcat) {
		this.prodcat = prodcat;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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
