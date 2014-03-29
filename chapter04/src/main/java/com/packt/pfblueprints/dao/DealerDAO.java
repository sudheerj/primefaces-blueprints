package com.packt.pfblueprints.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.packt.pfblueprints.model.Dealer;

public class DealerDAO {

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

    
	public DealerDAO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public List<Dealer> getAllAdvisors() {
		sessionFactory = configureSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query queryResult = session.createQuery("from Dealer");
		List<Dealer> allDealers = queryResult.list();
		session.getTransaction().commit();
		return allDealers;

	}
	public List<Dealer> deleteAdvisor(Dealer object) {
		sessionFactory = configureSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(object);
		Query queryResult = session.createQuery("from Dealer");
		List<Dealer> allDealers = queryResult.list();
		session.getTransaction().commit();
		return allDealers;

	}

}
