package com.packt.pfblueprints.model;

import java.io.Serializable;
import java.util.Date;

public class TransactionSummary implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String fundname;
	private String transactionid;
	private String transactiontype;
	private Date transactiondate;
	private String paymenttype;
	private String status;
	private String transctionunitprice;
	private String transactionunits;
	private String grossamount;
	private String deductions;
	private String netamount;
	
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
	
	public String getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}
	public String getTransactiontype() {
		return transactiontype;
	}
	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}
	public Date getTransactiondate() {
		return transactiondate;
	}
	public void setTransactiondate(Date transactiondate) {
		this.transactiondate = transactiondate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTransctionunitprice() {
		return transctionunitprice;
	}
	public void setTransctionunitprice(String transctionunitprice) {
		this.transctionunitprice = transctionunitprice;
	}
	public String getTransactionunits() {
		return transactionunits;
	}
	public void setTransactionunits(String transactionunits) {
		this.transactionunits = transactionunits;
	}
	public String getGrossamount() {
		return grossamount;
	}
	public void setGrossamount(String grossamount) {
		this.grossamount = grossamount;
	}
	public String getDeductions() {
		return deductions;
	}
	public void setDeductions(String deductions) {
		this.deductions = deductions;
	}
	public String getNetamount() {
		return netamount;
	}
	public void setNetamount(String netamount) {
		this.netamount = netamount;
	}
	public String getPaymenttype() {
		return paymenttype;
	}
	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}
	
	
}
