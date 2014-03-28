package com.packt.pfblueprints.model;

import java.io.Serializable;

public class AccountSummary implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String investorName;
	private String registeredAccholderName;
	private String accountNumber;
	private String accountType;
	private String status;
	private String registrationDate;
	private String openDate;
	private String closeDate;
	private Boolean jointAccount;
	private String balanceUS;
	private String balanceUK;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInvestorName() {
		return investorName;
	}
	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}
	public String getRegisteredAccholderName() {
		return registeredAccholderName;
	}
	public void setRegisteredAccholderName(String registeredAccholderName) {
		this.registeredAccholderName = registeredAccholderName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public Boolean getJointAccount() {
		return jointAccount;
	}
	public void setJointAccount(Boolean jointAccount) {
		this.jointAccount = jointAccount;
	}
	public String getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}
	public String getBalanceUS() {
		return balanceUS;
	}
	public void setBalanceUS(String balanceUS) {
		this.balanceUS = balanceUS;
	}
	public String getBalanceUK() {
		return balanceUK;
	}
	public void setBalanceUK(String balanceUK) {
		this.balanceUK = balanceUK;
	}
	
	
}
