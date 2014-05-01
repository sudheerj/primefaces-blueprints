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
public class AdminController implements Serializable {

	private static final long serialVersionUID = 1L;

	private String aboutus;
	private String contactus;
	private String disclaimer;
	private List<String> images;
	private TreeNode prodcutHierarchyRoot;
    private TreeNode selectedNode;

	public AdminController() {
		init();
		productTree();
	}

	public void init() {
		images = new ArrayList<String>();

		for (int i = 1; i <= 10; i++) {
			images.add("healthcare" + i + ".png");
		}

		aboutus = "This website is used to track and find the various health care products available in the market ";
		contactus = "Packt Publishing Limited,2nd Floor, Livery Place,35 Livery Street,BirminghamB3 2PB";
		disclaimer = "The information contained in this website is for information purposes only, and does not constitute, nor is it intended to constitute, the provision of health care product advice.";
	}

	public void productTree() {
		prodcutHierarchyRoot = new DefaultTreeNode("Root", null);
		TreeNode node0 = new DefaultTreeNode("Node 0", root);
		TreeNode node1 = new DefaultTreeNode("Node 1", root);
		TreeNode node2 = new DefaultTreeNode("Node 2", root);

		TreeNode node00 = new DefaultTreeNode("Node 0.0", node0);
		TreeNode node01 = new DefaultTreeNode("Node 0.1", node0);

		TreeNode node10 = new DefaultTreeNode("Node 1.0", node1);
		TreeNode node11 = new DefaultTreeNode("Node 1.1", node1);

		TreeNode node000 = new DefaultTreeNode("Node 0.0.0", node00);
		TreeNode node001 = new DefaultTreeNode("Node 0.0.1", node00);
		TreeNode node010 = new DefaultTreeNode("Node 0.1.0", node01);

		TreeNode node100 = new DefaultTreeNode("Node 1.0.0", node10);
	}

	public String getAboutus() {
		return aboutus;
	}

	public void setAboutus(String aboutus) {
		this.aboutus = aboutus;
	}

	public String getContactus() {
		return contactus;
	}

	public void setContactus(String contactus) {
		this.contactus = contactus;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public List<String> getImages() {
		return images;
	}
	
	

}
