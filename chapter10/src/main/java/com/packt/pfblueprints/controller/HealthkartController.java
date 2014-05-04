package com.packt.pfblueprints.controller;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

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
	private int chunkSize=5;
	ProductsDAO dao=new ProductsDAO();
	
	
	@PostConstruct
	public void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("productCategory", "");
		lazyLoad();
	}
	
	public void selectCategory(String category){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("productCategory", category);
		System.out.println("selected category=="+category);
	}
	
	public void lazyLoad(){
		lazyModel = new LazyDataModel<Product>() {
			@Override
			public List<Product> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				String sortOrderValue = null;
				if (sortField == null) {
					sortField = "prodname";
				}
				if (sortOrder.ASCENDING.equals("A")) {
					sortOrderValue = "ASC";
				} else if (sortOrder.DESCENDING.equals("D")) {
					sortOrderValue = "DSC";
				} else {
					sortOrderValue = "ASC";
				}

				productsInfo = dao.getAllProducts(first, pageSize, sortField,
						sortOrderValue, filters);
				List<Product> filteredProductsInfo=new ArrayList<Product>();
				//rowCount
				int dataSize = productsInfo.size();
				this.setRowCount(dataSize);
				//paginate
				if(dataSize > pageSize) {
				try {
				   filteredProductsInfo=productsInfo.subList(first, first + pageSize);
				   return filteredProductsInfo;
				 }
				catch(IndexOutOfBoundsException e) {
				   filteredProductsInfo=productsInfo.subList(first, first + (dataSize % pageSize));
				   return filteredProductsInfo;
				 }
				 }
				else {
					return productsInfo;
				}
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

	public int getChunkSize() {
		return chunkSize;
	}

	public void setChunkSize(int chunkSize) {
		this.chunkSize = chunkSize;
	}

	
}
