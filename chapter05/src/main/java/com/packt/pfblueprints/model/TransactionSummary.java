package com.packt.pfblueprints.model;

import java.io.Serializable;
import java.util.Date;

public class TransactionSummary implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String investmentNumber;
	private String transactionid;
	private String transactiontype;
	private String transactiondate;
	private String paymenttype;
	private String status;
	private String transactionunitprice;
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
	public String getInvestmentNumber() {
		return investmentNumber;
	}
	public void setInvestmentNumber(String investmentNumber) {
		this.investmentNumber = investmentNumber;
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
	
	public String getTransactiondate() {
		return transactiondate;
	}
	public void setTransactiondate(String transactiondate) {
		this.transactiondate = transactiondate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getTransactionunitprice() {
		return transactionunitprice;
	}
	public void setTransactionunitprice(String transactionunitprice) {
		this.transactionunitprice = transactionunitprice;
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
