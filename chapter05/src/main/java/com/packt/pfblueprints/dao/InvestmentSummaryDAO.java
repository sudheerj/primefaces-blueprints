package com.packt.pfblueprints.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.packt.pfblueprints.model.InvestmentSummary;


public class InvestmentSummaryDAO {

	private  SessionFactory sessionFactory;
	private  String accountNumber;

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

    
	public InvestmentSummaryDAO() {
		super();
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		accountNumber=(String) sessionMap.get("accountNumber");
		// TODO Auto-generated constructor stub
	}


	public List<InvestmentSummary> getAllInvestments() {
		sessionFactory = configureSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query queryResult=null;
		if(accountNumber!=""){
		queryResult = session.createQuery("from InvestmentSummary where accountNumber = :accNum");
		queryResult.setParameter("accNum", accountNumber);
		}else{
	    queryResult = session.createQuery("from InvestmentSummary");	
		}
		List<InvestmentSummary> allInvestments = queryResult.list();
		session.getTransaction().commit();
		return allInvestments;
	}
	

}
