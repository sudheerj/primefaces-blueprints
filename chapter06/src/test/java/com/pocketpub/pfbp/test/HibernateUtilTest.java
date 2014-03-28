package com.pocketpub.pfbp.test;

import com.packtpub.pf.blueprint.persistence.HibernateUtil;
import com.packtpub.pf.blueprint.persistence.entity.Category;
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
        Category cat = new Category();

        cat.setName("name");

        session.save(cat);
        cat = new Category();
        cat.setName("namedd");

        session.save(cat);

        List all = session.createCriteria(Category.class).list();

        System.out.println(all);
        //session.delete(cat);

        session.getTransaction().commit();


    }

}
