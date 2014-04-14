package com.packt.pfblueprints.model;

import java.io.Serializable;
import java.util.List;

public class Dealer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String dealernumber;
	private String advisorname;
	private String advisornumber;
	private String dor;
	private String branch;
	private String status;
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
	public String getDor() {
		return dor;
	}
	public void setDor(String dor) {
		this.dor = dor;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public List<ProgressStatus> getProgressStatus() {
		return progressStatus;
	}
	public void setProgressStatus(List<ProgressStatus> progressStatus) {
		this.progressStatus = progressStatus;
	}



}
