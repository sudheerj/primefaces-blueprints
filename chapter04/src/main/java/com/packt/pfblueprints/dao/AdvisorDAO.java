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

import com.packt.pfblueprints.model.Advisor;

public class AdvisorDAO {

	private  SessionFactory sessionFactory;
	private  String advisorNumber;

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

    
	public AdvisorDAO() {
		super();
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		advisorNumber=(String) sessionMap.get("advisornumber");
		// TODO Auto-generated constructor stub
	}


	public List<Advisor> getAllRepresentatives() {
		sessionFactory = configureSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query queryResult=null;
		if(advisorNumber!=""){
			queryResult = session.createQuery("from Advisor where advisornumber = :advisorNum");
			queryResult.setParameter("advisorNum", advisorNumber);
			}else{
		    queryResult = session.createQuery("from Advisor");	
			}
		List<Advisor> allAdvisors = queryResult.list();
		session.getTransaction().commit();
		return allAdvisors;

	}
	/*public List<Dealer> deleteAdvisor(Dealer object) {
		sessionFactory = configureSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(object);
		Query queryResult = session.createQuery("from Advisor");
		List<Dealer> allDealers = queryResult.list();
		session.getTransaction().commit();
		return allDealers;

	}*/

}
