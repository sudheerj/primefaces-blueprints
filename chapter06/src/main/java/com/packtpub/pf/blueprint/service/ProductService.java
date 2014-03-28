package com.packtpub.pf.blueprint.service;

import com.packtpub.pf.blueprint.persistence.HibernateUtil;
import com.packtpub.pf.blueprint.persistence.entity.Category;
import com.packtpub.pf.blueprint.persistence.entity.Discount;
import com.packtpub.pf.blueprint.persistence.entity.Product;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ramkumar Pillai  <psramkumar@gmail.com>
 * Date: 2/6/14
 * Time: 8:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProductService {
    private static final Logger _log = Logger.getLogger(ProductService.class);
    public final String OPEN_STATUS = "OPEN";

    public Category getCategory(long id) {
        org.hibernate.Transaction tx = getSession().beginTransaction();
        Category c = (Category) getSession().get(Category.class, id);
        tx.commit();
        getSession().close();
        return c;
    }

    public List getAllCategory() {
        org.hibernate.Transaction tx = getSession().beginTransaction();
        List list = getSession().createCriteria(Category.class).list();
        tx.commit();
        getSession().close();
        _log.info("Got all List of Categories: " + list);
        return list;
    }

    public void addCategory(Category c) {
        if (c != null) {
            org.hibernate.Transaction tx = getSession().beginTransaction();
            getSession().saveOrUpdate(c);
            tx.commit();
            getSession().close();
            _log.info("Added Successfully....");
        }
    }

    public void removeCategory(long id) {
        org.hibernate.Transaction tx = getSession().beginTransaction();
        Object o = getSession().get(Category.class, id);
        if (o != null) {
            getSession().delete(o);
        }
        tx.commit();
        getSession().close();
        _log.info("Removed Successfully....");
    }

    public List getAllDiscounts() {
        org.hibernate.Transaction tx = getSession().beginTransaction();
        List list = getSession().createCriteria(Discount.class).list();
        tx.commit();
        getSession().close();
        _log.info("Got all List of Discounts: " + list);
        return list;
    }

    public void addDiscount(Discount c) {
        if (c != null) {
            org.hibernate.Transaction tx = getSession().beginTransaction();
            getSession().saveOrUpdate(c);
            tx.commit();
            getSession().close();
            _log.info("Added Successfully....");
        }
    }

    public void removeDiscount(long id) {
        org.hibernate.Transaction tx = getSession().beginTransaction();
        Object o = getSession().get(Discount.class, id);
        if (o != null) {
            getSession().delete(o);
        }
        tx.commit();
        getSession().close();
        _log.info("Removed Successfully....");
    }


    public List getAllProducts() {
        org.hibernate.Transaction tx = getSession().beginTransaction();
        List list = getSession().createCriteria(Product.class).list();
        tx.commit();
        getSession().close();
        _log.info("Got all List of Products: " + list);
        return list;
    }

    public void addProduct(Product p) {
        if (p != null) {
            org.hibernate.Transaction tx = getSession().beginTransaction();
            getSession().saveOrUpdate(p);
            tx.commit();
            getSession().close();
            _log.info("Added Successfully....");
        }
    }

    public void removeProduct(long id) {
        org.hibernate.Transaction tx = getSession().beginTransaction();
        Object o = getSession().get(Product.class, id);
        if (o != null) {
            getSession().delete(o);
        }
        tx.commit();
        getSession().close();
        _log.info("Removed Successfully....");
    }

    public List getProductsForCategory(Category c) {
        org.hibernate.Transaction tx = getSession().beginTransaction();
        List list = getSession().createCriteria(Product.class)
                .add(Restrictions.eq("category", c))
                .addOrder(Order.desc("createDate")).list();
        tx.commit();
        getSession().close();
        _log.info("Added Successfully....");
        return list;
    }

    public List getAllOrders() {
        org.hibernate.Transaction tx = getSession().beginTransaction();
        List list = getSession().createCriteria(com.packtpub.pf.blueprint.persistence.entity.Order.class)
                .addOrder(Order.desc("createDate")).list();
        tx.commit();
        getSession().close();
        _log.info("Added Successfully....");
        return list;
    }

    public void addOrUpdateOrder(com.packtpub.pf.blueprint.persistence.entity.Order o) {
        if (o != null) {
            org.hibernate.Transaction tx = getSession().beginTransaction();
            getSession().saveOrUpdate(o);
            tx.commit();
            getSession().close();
            _log.info("Added Successfully....");
        }
    }


    private Session getSession() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        return sf.isClosed() ? sf.openSession() : sf.getCurrentSession();
    }

}
