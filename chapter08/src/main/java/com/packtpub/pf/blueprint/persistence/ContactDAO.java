package com.packtpub.pf.blueprint.persistence;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.packtpub.pf.blueprint.bean.GenericDAO;
import com.packtpub.pf.blueprint.model.Contact;

@Named
@Singleton
public class ContactDAO extends GenericDAO implements Serializable{

	@Inject
	private EntityManager em; 
	
	public void persist(Contact contact) {
		EntityTransaction tx = em.getTransaction();
	    tx.begin();
	    em.persist(contact);
	    tx.commit();
	}
	
	public Contact load(Long id) {
	    Query query = em.createQuery("select d from Contact d where d.id =:id");
	    query.setParameter("id", id);
	    query.setMaxResults(1);
	    Contact r = (Contact) query.getResultList().get(0);
	    return r;
	}
	
	public void update(Contact contact) {
		em.merge(contact);
	}
	
	@SuppressWarnings("unchecked")
	public List<Contact> findAll() {
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
	    Query query = em.createQuery("select d from Contact d");
	    List<Contact> res = query.getResultList();
	    tx.commit();
	    return res;
	}

	public void delete(Long id) {
		EntityTransaction tx = em.getTransaction();
	    tx.begin();
		Query query = em.createQuery("delete Contact c where c.id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
		tx.commit();
	}
	
	
}
