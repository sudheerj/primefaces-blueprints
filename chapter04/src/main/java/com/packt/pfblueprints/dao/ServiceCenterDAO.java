package com.packt.pfblueprints.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.packt.pfblueprints.model.Dealer;

public class ServiceCenterDAO {

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

	public ServiceCenterDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Dealer> getAllDealers() {
		sessionFactory = configureSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query queryResult = session.createQuery("from Dealer");
		List<Dealer> allDealers = queryResult.list();
		session.getTransaction().commit();
		return allDealers;
	}
	
	public void updateDealerProfile(Dealer serviceCenterObj){
		sessionFactory = configureSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(serviceCenterObj); 
		session.getTransaction().commit();
	}
	
	public List<Dealer> deleteDealer(Dealer object) {
		sessionFactory = configureSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(object);
		Query queryResult = session.createQuery("from ServiceCenter");
		List<Dealer> allDealers = queryResult.list();
		session.getTransaction().commit();
		return allDealers;
	}

}
