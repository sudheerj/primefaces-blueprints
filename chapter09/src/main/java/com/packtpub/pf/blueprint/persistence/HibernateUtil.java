package com.packtpub.pf.blueprint.persistence;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created with IntelliJ IDEA.
 * User: Ramkumar Pillai <psramkumar@gmail.com>
 * <p/>
 * Date: 2/8/14
 * Time: 7:51 PM
 * Hibernate util class is used to handle Hibernate Session.
 * Keep this credit
 */

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration().configure();
        // configures settings from hibernate.cfg.xml

        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();

        // If you miss the below line then it will complain about a missing dialect setting
        serviceRegistryBuilder.applySettings(configuration.getProperties());

        ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

}