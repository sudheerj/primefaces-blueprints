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

import com.packt.pfblueprints.model.Employee;
import com.packt.pfblueprints.model.JobPosts;

public class AdminDAO {

	private DataSource ds;
	Connection con;

	public AdminDAO() throws SQLException {
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

	public List<Employee> getEmployeeList() throws SQLException {
		PreparedStatement ps = con
				.prepareStatement("select firstname,lastname,sex,country,city,company from blueprintsdb.employee");

		// get jobposts data from database
		ResultSet result = ps.executeQuery();

		List<Employee> list = new ArrayList<Employee>();

		while (result.next()) {
			Employee employee = new Employee();

			employee.setFirstname(result.getString("firstname"));
			employee.setLastname(result.getString("lastname"));
			employee.setSex(result.getString("sex"));
			employee.setCountry(result.getString("country"));
			employee.setCity(result.getString("city"));
			employee.setCompany(result.getString("company"));

			list.add(employee);
		}

		return list;

	}

	public String getAboutus() throws SQLException {
		PreparedStatement ps = con
				.prepareStatement("select aboutus from blueprintsdb.adminconfig");
		ResultSet result = ps.executeQuery();
		String aboutus = null;
		while (result.next()) {
			aboutus = result.getString("aboutus");
		}

		return aboutus;

	}

	public String getContactus() throws SQLException {
		PreparedStatement ps = con
				.prepareStatement("select contactus from blueprintsdb.adminconfig");
		ResultSet result = ps.executeQuery();
		String contactus = null;
		while (result.next()) {
			contactus = result.getString("contactus");
		}

		return contactus;

	}

	public String getDisclaimer() throws SQLException {
		PreparedStatement ps = con
				.prepareStatement("select disclaimer from blueprintsdb.adminconfig");
		ResultSet result = ps.executeQuery();
		String disclaimer = null;
		while (result.next()) {
			disclaimer = result.getString("disclaimer");
		}

		return disclaimer;

	}

	public void updateAboutus(String aboutus) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("update blueprintsdb.adminconfig SET aboutus='"
					+ aboutus + "' where adminID='admin'");
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateContactus(String contactus) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("update blueprintsdb.adminconfig SET contactus='"
					+ contactus + "' where adminID='admin'");
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateDisclaimer(String disclaimer) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("update blueprintsdb.adminconfig SET disclaimer='"
					+ disclaimer + "' where adminID='admin'");
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
