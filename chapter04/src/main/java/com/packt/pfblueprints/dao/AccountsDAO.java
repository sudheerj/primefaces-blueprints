package com.packt.pfblueprints.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.packt.pfblueprints.model.AccountSummary;
import com.packt.pfblueprints.model.Dealer;

public class AccountsDAO {

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

    
	public AccountsDAO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public List<AccountSummary> getAllAccounts(int first,int pageSize,String sortField,String sortOrder,Map<String,String> filters) {
		sessionFactory = configureSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query queryResult = session.createQuery("from AccountSummary WHERE id BETWEEN "+first+" AND "+(first+pageSize-1)+" ORDER BY "+sortField+" "+sortOrder);
		List<AccountSummary> allAccounts = queryResult.list();
		session.getTransaction().commit();
		return allAccounts;

	}
	public List<Dealer> deleteAdvisor(Dealer object) {
		sessionFactory = configureSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(object);
		Query queryResult = session.createQuery("from AccountSummary");
		List<Dealer> allDealers = queryResult.list();
		session.getTransaction().commit();
		return allDealers;

	}

}
