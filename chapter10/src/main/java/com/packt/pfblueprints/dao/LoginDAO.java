package com.packt.pfblueprints.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LoginDAO {

	private DataSource ds;
	Connection con;

	public LoginDAO() throws SQLException {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/blueprintsdb");
			if (ds == null) {
				throw new SQLException("Can't get data source");
			}
			// get database connection
			con = ds.getConnection();
			if (con == null) {
				throw new SQLException("Can't get database connection");
			}

		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	public boolean changepassword(String userid, String oldpassword,
			String newpassword) {
		try {
			// Persist employee
			PreparedStatement ps = con
					.prepareStatement("UPDATE blueprintsdb.employee SET password='"
							+ newpassword
							+ "' "
							+ " WHERE userid=(select userid FROM (select * FROM blueprintsdb.employee) as useralias WHERE userid='"
							+ userid + "'  and password='" + oldpassword + "')");
			int count = ps.executeUpdate();
			if (count > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;

	}

	public boolean validateUser(String userid, String password) {
		try {
			//Check the logged jobseeker is valid or not
			PreparedStatement ps = con
					.prepareStatement("select count(*) FROM (select * FROM blueprintsdb.employee) as useralias WHERE userid='"
							+ userid + "'  and password='" + password + "'");
			ResultSet resultSet = ps.executeQuery();
			int count = 0;
			while (resultSet.next()) {
				count++;
			}
			if (count > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}
}
