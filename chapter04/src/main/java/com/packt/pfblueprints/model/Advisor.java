package com.packt.pfblueprints.model;

import java.io.Serializable;
import java.util.List;

public class Advisor implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String dealernumber;
	private String advisorname;
	private String advisornumber;
	private String managementcompany;
	private String branch;
	private int year;
	private boolean status;
	private int revenue;
	private List<ProgressStatus> progressStatus;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDealernumber() {
		return dealernumber;
	}
	public void setDealernumber(String dealernumber) {
		this.dealernumber = dealernumber;
	}
	public String getAdvisorname() {
		return advisorname;
	}
	public void setAdvisorname(String advisorname) {
		this.advisorname = advisorname;
	}
	public String getAdvisornumber() {
		return advisornumber;
	}
	public void setAdvisornumber(String advisornumber) {
		this.advisornumber = advisornumber;
	}
	
	public String getManagementcompany() {
		return managementcompany;
	}
	public void setManagementcompany(String managementcompany) {
		this.managementcompany = managementcompany;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getRevenue() {
		return revenue;
	}
	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<ProgressStatus> getProgressStatus() {
		return progressStatus;
	}
	public void setProgressStatus(List<ProgressStatus> progressStatus) {
		this.progressStatus = progressStatus;
	}

}
