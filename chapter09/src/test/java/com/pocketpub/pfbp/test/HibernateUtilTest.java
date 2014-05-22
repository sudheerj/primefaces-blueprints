package com.pocketpub.pfbp.test;

import com.packtpub.pf.blueprint.persistence.HibernateUtil;
import com.packtpub.pf.blueprint.persistence.entity.Profile;
import org.hibernate.Session;

import java.util.Date;
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
        Profile u = new Profile();
        u.setCreateDate(new Date());
        u.setFirstName("firstName");
        u.setLastName("lastNAme");
        u.setPassword("password1");

        session.save(u);
        u = new Profile();
        u.setCreateDate(new Date());
        u.setFirstName("firstName");
        u.setLastName("lastNAme");
        u.setPassword("password1");
        session.save(u);

        List all = session.createCriteria(Profile.class).list();

        System.out.println(all);
        //session.delete(cat);

        session.getTransaction().commit();


    }

}
