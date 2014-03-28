package com.pocketpub.pfbp.test;

import com.packtpub.pf.blueprint.persistence.HibernateUtil;
import com.packtpub.pf.blueprint.persistence.entity.Movie;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: psramkumar
 * Date: 2/6/14
 * Time: 7:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class HibernateUtilTest {

    public static void main(String[] args) {
        System.out.println("Testing Hibernate Utility Class");
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Movie cat = new Movie();

        cat.setTitle("name");

        session.save(cat);
        cat = new Movie();
        cat.setTitle("namedd");

        session.save(cat);

        List all = session.createCriteria(Movie.class).list();

        System.out.println(all);
        //session.delete(cat);

        session.getTransaction().commit();


    }

}
