package com.packt.pfblueprints.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.packt.pfblueprints.model.Employee;

public class RegistrationDAO {
	private DataSource ds;
	Connection con; 


	public RegistrationDAO() throws SQLException {
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
	
	
   public boolean saveEmployee(Employee employee,String uniqueID) throws Exception{
	   try{
		   String pattern = "yyyy-MM-dd";
		   SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		   // Register employee
			PreparedStatement ps = con.prepareStatement(
					   "INSERT INTO blueprintsdb.employee(userid,firstname,lastname,dob,sex,maritalstatus,address,country,city,phone,postalcode,email,university,qualification,percentage,profession,experience,company,currentpack,expectedpack,joineddate,frameworks,dbs,servers,ides,password)VALUES('"+employee.getUserid()+"','"+employee.getFirstname()+"','"+employee.getLastname()+"','"+formatter.format(employee.getDob())+"','"+employee.getGender()+"','"+employee.getMaritalStatus()+"','"+employee.getAddress()+"','"+employee.getCountry()+"','"+employee.getCity()+"','"+employee.getPhone()+"','"+employee.getPostalCode()+"','"+employee.getEmail()+"','"+employee.getUniversity()+"','"+employee.getQualification()+"','"+employee.getPercentage()+"','"+employee.getProfession()+"',"+employee.getExperience()+",'"+employee.getCompany()+"',"+employee.getCurrentPack()+","+employee.getExpectedPack()+",'"+formatter.format(employee.getJoinedDate())+"','"+employee.getSelectedFrameworks()+"','"+employee.getSelectedDBs()+"','"+employee.getSelectedServer()+"','"+employee.getSelectedIDE()+"','"+uniqueID+"')"); 
			int count=ps.executeUpdate();
			if(count>0){
				return true;
			}
	   }catch(SQLException e){
		   e.printStackTrace();
		   
	   }catch(Exception e){
		   e.printStackTrace();
		   
	   }
	return false;
			
   }
	
}
