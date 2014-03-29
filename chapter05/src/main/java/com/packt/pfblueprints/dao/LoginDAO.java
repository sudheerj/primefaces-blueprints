package com.packt.pfblueprints.dao;


import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.packt.pfblueprints.model.InvestorsList;



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
		super();

	}

	
	public boolean validateUser(String userid, String password) {
		try {
			sessionFactory = configureSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String query = "from InvestorsList  where username='" + userid + "' and password='" + password + "'";
		    Query queryobj = session.createQuery(query);
		    List<InvestorsList> list=queryobj.list();
		    int count=0;
		    if(list!=null){
		    count= list.size();
		    }
		    session.getTransaction().commit();
			if (count > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}
}
