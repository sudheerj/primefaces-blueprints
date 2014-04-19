package com.packt.pfblueprints.model;

public class ProgressStatus{
	private String year;
	private String percentage;
	
	public ProgressStatus(String year, String percentage) {
		super();
		this.year = year;
		this.percentage = percentage;
	}
	
	public ProgressStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	
}
