package com.packt.pfblueprints.model;

import java.io.Serializable;

public class Representative implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String repnumber;
	private String repname;
	private String advisornumber;
	private String dor;
	private String branch;
	private String status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRepnumber() {
		return repnumber;
	}
	public void setRepnumber(String repnumber) {
		this.repnumber = repnumber;
	}
	public String getRepname() {
		return repname;
	}
	public void setRepname(String repname) {
		this.repname = repname;
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

}
