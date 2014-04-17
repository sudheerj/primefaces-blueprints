package com.packtpub.pf.blueprint.persistence;


import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * This bean is used to hold the entity manager factory and to produce the
 * conversation scoped entity manager for injection
 */
@Singleton
public class EntityManagerProducer {

	private static EntityManagerFactory factory;

	@Produces
	public EntityManagerFactory getEntityManagerFactory() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("persistence-unit");
		}
		return factory;
	}

	@Produces
	public EntityManager produceEntityManager() {
		return getEntityManagerFactory().createEntityManager();
	}

}
