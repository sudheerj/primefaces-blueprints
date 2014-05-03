package com.packt.pfblueprints.controller;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.TreeNode;
import com.packt.pfblueprints.model.Product;
import com.packt.pfblueprints.dao.ProductsDAO;


@ManagedBean
@ViewScoped
public class HealthkartController implements Serializable {

	private static final long serialVersionUID = 1L;

	private LazyDataModel<Product> lazyModel;
	private List<Product> productsInfo = new ArrayList<Product>();
	ProductsDAO dao=new ProductsDAO();
	
	@PostConstruct
	public void init() {

			lazyModel = new LazyDataModel<Product>() {
			@Override
			public List<Product> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				System.out.println("lazy loading begin");
				String sortOrderValue = null;
				/*if (sortField == null) {
					sortField = "investorName";
				}*/
				if (sortOrder.ASCENDING.equals("A")) {
					sortOrderValue = "ASC";
				} else if (sortOrder.DESCENDING.equals("D")) {
					sortOrderValue = "DSC";
				} else {
					sortOrderValue = "default";
				}

				productsInfo = dao.getAllProducts(first, pageSize, sortField,
						sortOrderValue, filters);
				this.setRowCount(productsInfo.size());
				return productsInfo;
			}
		};
	}
	
	public LazyDataModel<Product> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Product> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public List<Product> getProductsInfo() {
		return productsInfo;
	}

	public void setProductsInfo(List<Product> productsInfo) {
		this.productsInfo = productsInfo;
	}

	
}
