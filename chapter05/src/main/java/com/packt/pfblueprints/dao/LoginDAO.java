package com.packt.pfblueprints.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.packt.pfblueprints.dao.Configuration;
import com.packt.pfblueprints.dao.HibernateException;
import com.packt.pfblueprints.dao.Session;
import com.packt.pfblueprints.dao.SessionFactory;
import com.packt.pfblueprints.dao.StandardServiceRegistryBuilder;

public class LoginDAO {

	private  SessionFactory sessionFactory;

	private  SessionFactory configureSessionFactory()
			throws HibernateException {
		Configuration configuration = new Configuration();
		configuration.configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionfactory = configuration
				.buildSessionFactory(builder.build());
		return sessionfactory;
	}

    
	

	public LoginDAO() throws SQLException {
		this.super();

	}

	
	public boolean validateUser(String userid, String password) {
		try {
			sessionFactory = configureSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String query = "select * from investorslist  where username='" + userid + "' and password='" + password + "'";
		    Query query = session.createQuery(query);
		    int count= query.list();
		    session.getTransaction().commit();
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
