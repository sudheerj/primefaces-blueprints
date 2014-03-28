package com.packt.pfblueprints.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.packt.pfblueprints.model.JobPosts;

public class JobPostsDAO {
	private DataSource ds;
	Connection con; 


	public JobPostsDAO() throws SQLException {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/blueprintsdb");
			if (ds == null)
				throw new SQLException("Can't get data source");

			// get database connection
			con = ds.getConnection();

			if (con == null)
				throw new SQLException("Can't get database connection");

		} catch (NamingException e) {
			e.printStackTrace();
		}

	}
	public List<JobPosts> getAllJobs() throws SQLException{
		PreparedStatement ps 
		= con.prepareStatement(
		   "select company,domain,experience,position,location from blueprintsdb.jobposts");
		
		//get jobposts data from database
				ResultSet result =  ps.executeQuery();

		List<JobPosts> list = new ArrayList<JobPosts>();
		 
		while(result.next()){
			JobPosts post = new JobPosts();
 
			post.setCompany(result.getString("company"));
			post.setDomain(result.getString("domain"));
			post.setExperience(result.getString("experience"));
			post.setPosition(result.getString("position"));
			post.setLocation(result.getString("location"));
 
			list.add(post);
		}
 
		return list;
		
	}
	
}
