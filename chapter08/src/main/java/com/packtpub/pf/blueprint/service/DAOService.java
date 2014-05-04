package com.packtpub.pf.blueprint.service;

import com.packtpub.pf.blueprint.JobStatus;
import com.packtpub.pf.blueprint.persistence.entity.*;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ramkumar Pillai  <psramkumar@gmail.com>
 * Date: 2/6/14
 * Time: 8:18 PM
 * To change this template use File | Settings | File Templates.
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class DAOService {
    private static final Logger _log = Logger.getLogger(DAOService.class);
    public final String OPEN_STATUS = "OPEN";

    public Customer validateUser(String username, String password) {
        org.hibernate.Transaction tx = getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(Customer.class);
        criteria.add(Restrictions.eq("email", username));
        criteria.add(Restrictions.eq("password", password));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        Customer user = (Customer) criteria.uniqueResult();
        tx.commit();
        getSession().close();
        _log.info("Listed Successfully....");
        return user;
    }

    public List<Location> getAllLocations(){
        org.hibernate.Transaction tx = getSession().beginTransaction();
        List list = getSession().createCriteria(Location.class).list();
        tx.commit();
        getSession().close();
        _log.info("Listed Successfully....");
        return list;
    }


    public void addOrUpdateEntity(Object o) {
        if (o != null) {
            org.hibernate.Transaction tx = getSession().beginTransaction();
            getSession().saveOrUpdate(o);
            tx.commit();
            getSession().close();
            _log.info("Added Successfully....");
        }
    }

    public Object loadEntityById(Class<?> cl, Long id) {
        Object o = null;
        if (id != null) {
            org.hibernate.Transaction tx = getSession().beginTransaction();
            o = getSession().load(cl, id);
            tx.commit();
            getSession().close();
            _log.info(" Successfully Loaded Object....");
        }
        return o;
    }

    public List<PrintJobs> getJobsByCustomerId(Customer c) {
        org.hibernate.Transaction tx = getSession().beginTransaction();
        List list = getSession().createCriteria(PrintJobs.class)
                .add(Restrictions.eq("customer", c))
                .addOrder(Order.desc("createDate")).list();
        tx.commit();
        getSession().close();
        _log.info("Listed Successfully....");
        return list;
    }

    public List<PrintJobs> getJobsBySubmittedStatus() {
        org.hibernate.Transaction tx = getSession().beginTransaction();
        List list = getSession().createCriteria(PrintJobs.class)
                .add(Restrictions.eq("status", JobStatus.SUBMITTED))
                .addOrder(Order.desc("createDate")).list();
        tx.commit();
        getSession().close();
        _log.info("Listed Successfully....");
        return list;
    }


    public Location getLocationByFranchiseeNo(Long franchiseeNo){
        Location loc = null;
        if (franchiseeNo != null) {
            org.hibernate.Transaction tx = getSession().beginTransaction();
            Criteria criteria = getSession().createCriteria(Location.class);
            criteria.add(Restrictions.eq("franchiseeNo", franchiseeNo));
            loc = (Location) criteria.uniqueResult();
            tx.commit();
            getSession().close();
            _log.info("Added Successfully....");
        }
        return loc;

    }

    private Session getSession() {
        SessionFactory sf = com.packtpub.pf.blueprint.persistence.HibernateUtil.getSessionFactory();
        return sf.isClosed() ? sf.openSession() : sf.getCurrentSession();
    }


}
