package com.packt.pfblueprints.dao;

import java.util.ArrayList;
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
import com.packt.pfblueprints.model.ProgressStatus;

public class DealerDAO {

	private  SessionFactory sessionFactory;
	private  String dealerNumber;
	
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
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		dealerNumber=(String) sessionMap.get("dealertinnumber");
		// TODO Auto-generated constructor stub
	}

	public List<Advisor> getAllAdvisors() {
		sessionFactory = configureSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query queryResult=null;
		if(dealerNumber!=""){
			queryResult = session.createQuery("from Advisor where dealernumber = :dealerNum");
			queryResult.setParameter("dealerNum", dealerNumber);
			}else{
		    queryResult = session.createQuery("from Advisor");	
			}
		List<Advisor> allDealers = queryResult.list();
		for(Advisor dealerobj:allDealers){
			List<ProgressStatus> progressStatus=generateProgressStatus();
			dealerobj.setProgressStatus(progressStatus);
		}
		session.getTransaction().commit();
		return allDealers;
	}
	
	public List<ProgressStatus> generateProgressStatus(){
		 List<ProgressStatus> progressStatus=new ArrayList<ProgressStatus>();
		 progressStatus.add(new ProgressStatus("2000",((int)(Math.random()*10))+""));
		 progressStatus.add(new ProgressStatus("2002",((int)(Math.random()*10))+""));
		 progressStatus.add(new ProgressStatus("2004",((int)(Math.random()*10))+""));
		 progressStatus.add(new ProgressStatus("2006",((int)(Math.random()*10))+""));
		 return progressStatus;
	}
	
	public List<Advisor> deleteAdvisor(Advisor object) {
		sessionFactory = configureSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(object);
		Query queryResult = session.createQuery("from Advisor");
		List<Advisor> allDealers = queryResult.list();
		session.getTransaction().commit();
		return allDealers;

	}

}
