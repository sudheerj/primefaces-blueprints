package com.packt.pfblueprints.controller;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean
@ViewScoped
public class HealthkartController implements Serializable {

	private static final long serialVersionUID = 1L;

	private LazyDataModel<Product> lazyModel;
	
}
