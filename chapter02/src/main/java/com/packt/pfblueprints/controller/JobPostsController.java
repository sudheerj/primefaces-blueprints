package com.packt.pfblueprints.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.packt.pfblueprints.dao.JobPostsDAO;
import com.packt.pfblueprints.model.JobPosts;

@ManagedBean
@ViewScoped
public class JobPostsController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<JobPosts> jobslist=new ArrayList<JobPosts>();
	
	@SuppressWarnings("restriction")
	@PostConstruct  
	public void init() { 
		JobPostsDAO dao;
		try {
			dao = new JobPostsDAO();
			jobslist=dao.getAllJobs();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public List<JobPosts> getJobslist() {
		return jobslist;
	}

	public void setJobslist(List<JobPosts> jobslist) {
		this.jobslist = jobslist;
	}
	
	
}
