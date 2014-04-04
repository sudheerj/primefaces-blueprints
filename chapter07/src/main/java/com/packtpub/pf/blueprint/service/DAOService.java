package com.packtpub.pf.blueprint.service;

import com.packtpub.pf.blueprint.persistence.HibernateUtil;
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
import java.util.ArrayList;
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

    public User validateUser(String username, String password) {
        org.hibernate.Transaction tx = getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("username", username));
        criteria.add(Restrictions.eq("password", password));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        User user = (User) criteria.uniqueResult();
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

    public List<MovieSchedule> getAllEvents(){
        org.hibernate.Transaction tx = getSession().beginTransaction();
        List list = getSession().createCriteria(MovieSchedule.class).list();
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

    public Object loadEntityById(Class<MovieSchedule> cl, Long id) {
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

    public List getAllMovies() {
        org.hibernate.Transaction tx = getSession().beginTransaction();
        List list = getSession().createCriteria(Movie.class)
                .addOrder(Order.desc("releaseDate")).list();
        tx.commit();
        getSession().close();
        _log.info("Listed Successfully....");
        return list;
    }


    public void addOrUpdateMovie(Movie m) {
        addOrUpdateEntity(m);
    }


    public List<Movie> getMoviesByUserId(User u) {
        org.hibernate.Transaction tx = getSession().beginTransaction();
        List list = getSession().createCriteria(Movie.class)
                .add(Restrictions.eq("user", u))
                .addOrder(Order.desc("releaseDate")).list();
        tx.commit();
        getSession().close();
        _log.info("Listed Successfully....");
        return list;
    }

    public List<Comment> getAllCommentsByMovie(Movie m) {
        org.hibernate.Transaction tx = getSession().beginTransaction();
        List list = getSession().createCriteria(Comment.class)
                .add(Restrictions.eq("movie", m))
                .addOrder(Order.desc("createDate")).list();
        tx.commit();
        getSession().close();
        _log.info("Listed Successfully....");
        return list;
    }

    public List<String> getTagsStartWith(String chars){
        List<String> list = new ArrayList<>();
        org.hibernate.Transaction tx = getSession().beginTransaction();
        List tags = getSession().createCriteria(Tags.class)
                .add(Restrictions.ilike("name", chars))
                .addOrder(Order.asc("name")).list();
        tx.commit();
        getSession().close();
        if(tags != null){
            for (Tags t: (List<Tags>) tags){
               list.add(t.getName());
            }
        }
        _log.info("Listed Successfully....");
        return list;
    }

    public Tags getTagByName(String name){
        org.hibernate.Transaction tx = getSession().beginTransaction();
        Tags tag = (Tags) getSession().createCriteria(Tags.class)
                .add(Restrictions.eq("name", name).ignoreCase())
                .uniqueResult();
        tx.commit();
        getSession().close();
        _log.info("Listed Successfully....");
        return tag;
    }

    private Session getSession() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        return sf.isClosed() ? sf.openSession() : sf.getCurrentSession();
    }


}
