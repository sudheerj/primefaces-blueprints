package com.packt.pfblueprints.dao;

import java.util.Iterator;
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

import com.packt.pfblueprints.model.Product;

public class ProductsDAO {

	private String productCategory;

	public ProductsDAO() {
		super();
	}

	public List<Product> getAllProducts(int first, int pageSize,
			String sortField, String sortOrderValue, Map<String, Object> filters) {
		Session session = getSession();// sessionFactory.openSession();
		session.beginTransaction();

		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		productCategory = (String) sessionMap.get("productCategory");
		System.out.println("PRODUCTDAO called"+productCategory);
		String query = null;
		
		Query queryResult=null;
		System.out.println("productCategory=="+productCategory);
		if (productCategory != "") {
			System.out.println("if block");
			query = "from Product where prodcat = :productCategory ";
			queryResult = session.createQuery(query);
			queryResult.setParameter("productCategory", productCategory);
		} else {
			System.out.println("else block");
			query = "from Product";
		    queryResult = session.createQuery(query);
		}
		List<Product> allProducts = queryResult.list();
		System.out.println("list size==" + allProducts.size());
		session.getTransaction().commit();
		return allProducts;

	}

	private Session getSession() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		return sf.isClosed() ? sf.openSession() : sf.getCurrentSession();
	}

}
