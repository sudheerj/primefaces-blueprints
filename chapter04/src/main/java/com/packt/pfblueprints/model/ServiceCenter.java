package com.packt.pfblueprints.model;

import java.io.Serializable;

public class ServiceCenter implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String dealerfirstname;
	private String dealerlastname;
	private String dealertinnumber;
	private String branchname;
	private String dor;
	private String noofadvisors;
	private String pan;
	private String status;
	private String address1;
	private String address2;
	private String country;
	private String city;
	private String contactnumber;
	private String postalcode;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDealerfirstname() {
		return dealerfirstname;
	}
	public void setDealerfirstname(String dealerfirstname) {
		this.dealerfirstname = dealerfirstname;
	}
	public String getDealerlastname() {
		return dealerlastname;
	}
	public void setDealerlastname(String dealerlastname) {
		this.dealerlastname = dealerlastname;
	}
	public String getDealertinnumber() {
		return dealertinnumber;
	}
	public void setDealertinnumber(String dealertinnumber) {
		this.dealertinnumber = dealertinnumber;
	}
	public String getBranchname() {
		return branchname;
	}
	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}
	public String getDor() {
		return dor;
	}
	public void setDor(String dor) {
		this.dor = dor;
	}
	public String getNoofadvisors() {
		return noofadvisors;
	}
	public void setNoofadvisors(String noofadvisors) {
		this.noofadvisors = noofadvisors;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getContactnumber() {
		return contactnumber;
	}
	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	
	

}
