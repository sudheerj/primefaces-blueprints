package com.packt.pfblueprints.controller;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import com.packt.pfblueprints.model.Product;
import com.packt.pfblueprints.model.ProductSales;

@ManagedBean
@ViewScoped
public class AdminController implements Serializable {

	private static final long serialVersionUID = 1L;

	private String aboutus;
	private String contactus;
	private String disclaimer;
	private List<String> images;
	private TreeNode productHierarchyRoot;
	private TreeNode productSalesRoot;
    private TreeNode selectedNode;
    private TreeNode selectedProductNode;
    private Map<String, String> themes;

	public AdminController() {
		init();
		setThemes();
		productTree();
		productSales();
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
	
	public void setThemes(){
		themes = new TreeMap<String, String>();
        themes.put("Aristo", "aristo");
        themes.put("Black-Tie", "black-tie");
        themes.put("Blitzer", "blitzer");
        themes.put("Bluesky", "bluesky");
        themes.put("Casablanca", "casablanca");
        themes.put("Cupertino", "cupertino");
        themes.put("Dark-Hive", "dark-hive");
        themes.put("Dot-Luv", "dot-luv");
        themes.put("Eggplant", "eggplant");
        themes.put("Excite-Bike", "excite-bike");
        themes.put("Flick", "flick");
        themes.put("Glass-X", "glass-x");
        themes.put("Hot-Sneaks", "hot-sneaks");
        themes.put("Humanity", "humanity");
        themes.put("Le-Frog", "le-frog");
        themes.put("Midnight", "midnight");
        themes.put("Mint-Choc", "mint-choc");
        themes.put("Overcast", "overcast");
        themes.put("Pepper-Grinder", "pepper-grinder");
        themes.put("Redmond", "redmond");
        themes.put("Rocket", "rocket");
        themes.put("Sam", "sam");
        themes.put("Smoothness", "smoothness");
        themes.put("South-Street", "south-street");
        themes.put("Start", "start");
        themes.put("Sunny", "sunny");
        themes.put("Swanky-Purse", "swanky-purse");
        themes.put("Trontastic", "trontastic");
        themes.put("UI-Darkness", "ui-darkness");
        themes.put("UI-Lightness", "ui-lightness");
        themes.put("Vader", "vader");
	}

	public void productTree() {
		productHierarchyRoot = new DefaultTreeNode("HealthCare Products", null);
		TreeNode node0 = new DefaultTreeNode("Health Devices", productHierarchyRoot);
		TreeNode node1 = new DefaultTreeNode("Diabetes Care", productHierarchyRoot);
		TreeNode node2 = new DefaultTreeNode("Beauty Care", productHierarchyRoot);
		TreeNode node3 = new DefaultTreeNode("Vitamins and Supplements", productHierarchyRoot);
		TreeNode node4 = new DefaultTreeNode("Sports and Fitness", productHierarchyRoot);

		TreeNode node00 = new DefaultTreeNode("Patient Care", node0);
		TreeNode node01 = new DefaultTreeNode("Monitoring Devices", node0);
		TreeNode node02 = new DefaultTreeNode("Pain Managements", node0);
		TreeNode node03 = new DefaultTreeNode("Doctors Care", node0);
		TreeNode node04 = new DefaultTreeNode("Repository Care", node0);
		TreeNode node05 = new DefaultTreeNode("Weight Management", node0);
		TreeNode node10 = new DefaultTreeNode("Monitoring Devices", node1);
		TreeNode node11 = new DefaultTreeNode("Weight management", node1);
		TreeNode node12 = new DefaultTreeNode("Diabetic Food", node1);
		TreeNode node20 = new DefaultTreeNode("Hair Care", node2);
		TreeNode node21 = new DefaultTreeNode("Face Care", node2);
		TreeNode node22 = new DefaultTreeNode("Make-up Care", node2);
		TreeNode node23 = new DefaultTreeNode("Grooming", node2);
		TreeNode node30 = new DefaultTreeNode("Ayurveda and Herbs", node3);
		TreeNode node31 = new DefaultTreeNode("Weight Management", node3);
		TreeNode node40 = new DefaultTreeNode("Foot Wear", node4);
		TreeNode node41 = new DefaultTreeNode("Sports appearal", node4);
		TreeNode node42 = new DefaultTreeNode("Team Sports", node4);
		TreeNode node43 = new DefaultTreeNode("Gym Equipments", node4);
		TreeNode node44 = new DefaultTreeNode("Muscle Building", node4);
		TreeNode node45 = new DefaultTreeNode("Weight Loss Equipments", node4);
		
		
		TreeNode node000 = new DefaultTreeNode("Mattress", node00);
		TreeNode node001 = new DefaultTreeNode("Wheelchairs", node00);
		TreeNode node002 = new DefaultTreeNode("Walking and Hearing aids", node00);
		TreeNode node003 = new DefaultTreeNode("Thermometer", node01);
		TreeNode node004 = new DefaultTreeNode("Blood pressure", node01);
		TreeNode node005 = new DefaultTreeNode("Weighing scales", node01);
		TreeNode node006 = new DefaultTreeNode("Massagers", node02);
		TreeNode node007 = new DefaultTreeNode("Heating pads", node02);
		TreeNode node008 = new DefaultTreeNode("Body supports", node02);
		TreeNode node009 = new DefaultTreeNode("Stethescope", node03);
		TreeNode node010 = new DefaultTreeNode("Glucometers", node03);
		TreeNode node011 = new DefaultTreeNode("Blook pressure", node03);
		TreeNode node012 = new DefaultTreeNode("Vaporiser", node04);
		TreeNode node013 = new DefaultTreeNode("Oxygen concentrator", node04);
		TreeNode node014 = new DefaultTreeNode("Weighing scales", node05);
		TreeNode node015 = new DefaultTreeNode("Body fat monitor", node05);
		TreeNode node100 = new DefaultTreeNode("Gluco meters", node10);
		TreeNode node101 = new DefaultTreeNode("Lancets", node10);
		TreeNode node102 = new DefaultTreeNode("Weighing scales", node11);
		TreeNode node103 = new DefaultTreeNode("Body fat monitor", node11);
		TreeNode node104 = new DefaultTreeNode("Treadmills", node11);
		TreeNode node105 = new DefaultTreeNode("Sugar free food", node12);
		TreeNode node106 = new DefaultTreeNode("Sugar free drinks", node12);
		TreeNode node200 = new DefaultTreeNode("Shampoo and Conditioners", node20);
		TreeNode node201 = new DefaultTreeNode("Hair fall and dandruf treatment", node20);
		TreeNode node202 = new DefaultTreeNode("Hair oils", node21);
		TreeNode node203 = new DefaultTreeNode("Creams and locations", node21);
		TreeNode node204 = new DefaultTreeNode("Sun protection", node21);
		TreeNode node205 = new DefaultTreeNode("Cleaners", node21);
		TreeNode node206 = new DefaultTreeNode("Facial parts", node22);
		TreeNode node207 = new DefaultTreeNode("Nails", node22);
		TreeNode node208 = new DefaultTreeNode("Face care", node23);
		TreeNode node209 = new DefaultTreeNode("Hair care", node23);
		TreeNode node210 = new DefaultTreeNode("Perfumes", node23);
		TreeNode node300 = new DefaultTreeNode("Face,hair and nails", node30);
		TreeNode node301 = new DefaultTreeNode("Herbs", node30);
		TreeNode node302 = new DefaultTreeNode("Weight loss", node31);
		TreeNode node313 = new DefaultTreeNode("Weight gain", node31);
		TreeNode node400 = new DefaultTreeNode("Fitness shoes", node40);
		TreeNode node401 = new DefaultTreeNode("Sports shoes", node40);
		TreeNode node402 = new DefaultTreeNode("T-Shirts and Jackets", node41);
		TreeNode node403 = new DefaultTreeNode("Tracks and bags", node41);
		TreeNode node404 = new DefaultTreeNode("Caps and Sunglasses", node41);
		TreeNode node405 = new DefaultTreeNode("Cricket", node42);
		TreeNode node406 = new DefaultTreeNode("Football", node42);
		TreeNode node407 = new DefaultTreeNode("Gloves and Belts", node43);
		TreeNode node408 = new DefaultTreeNode("Clothing and Bags", node43);
		TreeNode node409 = new DefaultTreeNode("Dumbells and Weights", node44);
		TreeNode node410 = new DefaultTreeNode("Pushup bars", node44);
		TreeNode node411 = new DefaultTreeNode("Yoga mats", node45);
		TreeNode node412 = new DefaultTreeNode("Steppers", node45);
		
	}
	
	public void productSales(){
		    productSalesRoot = new DefaultTreeNode("root", null);
         
		    TreeNode node0 = new DefaultTreeNode(new ProductSales("Health Devices", "80k","90k","1billion","2billion","+40%"), productSalesRoot);
			TreeNode node1 = new DefaultTreeNode(new ProductSales("Diabetes Care", "80k","90k","1billion","2billion","+40%"),productSalesRoot);
			TreeNode node2 = new DefaultTreeNode(new ProductSales("Beauty Care", "80k","90k","1billion","2billion","+40%"), productSalesRoot);
			TreeNode node3 = new DefaultTreeNode(new ProductSales("Vitamins and Supplements", "80k","90k","1billion","2billion","+40%"), productSalesRoot);
			TreeNode node4 = new DefaultTreeNode(new ProductSales("Sports and Fitness", "80k","90k","1billion","2billion","+40%"), productSalesRoot);

			TreeNode node00 = new DefaultTreeNode(new ProductSales("Patient Care", "80k","90k","1billion","2billion","+40%"), node0);
			TreeNode node01 = new DefaultTreeNode(new ProductSales("Monitoring Devices", "80k","90k","1billion","2billion","+40%"), node0);
			TreeNode node02 = new DefaultTreeNode(new ProductSales("Pain Managements", "80k","90k","1billion","2billion","+40%"), node0);
			TreeNode node03 = new DefaultTreeNode(new ProductSales("Doctors Care", "80k","90k","1billion","2billion","+40%"), node0);
			TreeNode node04 = new DefaultTreeNode(new ProductSales("Repository Care", "80k","90k","1billion","2billion","+40%"), node0);
			TreeNode node05 = new DefaultTreeNode(new ProductSales("Weight Management", "80k","90k","1billion","2billion","+40%"), node0);
			TreeNode node10 = new DefaultTreeNode(new ProductSales("Monitoring Devices", "80k","90k","1billion","2billion","+40%"), node1);
			TreeNode node11 = new DefaultTreeNode(new ProductSales("Weight management", "80k","90k","1billion","2billion","+40%"), node1);
			TreeNode node12 = new DefaultTreeNode(new ProductSales("Diabetic Food", "80k","90k","1billion","2billion","+40%"), node1);
			TreeNode node20 = new DefaultTreeNode(new ProductSales("Hair Care", "80k","90k","1billion","2billion","+40%"), node2);
			TreeNode node21 = new DefaultTreeNode(new ProductSales("Face Care", "80k","90k","1billion","2billion","+40%"), node2);
			TreeNode node22 = new DefaultTreeNode(new ProductSales("Make-up Care", "80k","90k","1billion","2billion","+40%"), node2);
			TreeNode node23 = new DefaultTreeNode(new ProductSales("Grooming", "80k","90k","1billion","2billion","+40%"), node2);
			TreeNode node30 = new DefaultTreeNode(new ProductSales("Ayurveda and Herbs", "80k","90k","1billion","2billion","+40%"), node3);
			TreeNode node31 = new DefaultTreeNode(new ProductSales("Weight Management", "80k","90k","1billion","2billion","+40%"), node3);
			TreeNode node40 = new DefaultTreeNode(new ProductSales("Foot Wear", "80k","90k","1billion","2billion","+40%"), node4);
			TreeNode node41 = new DefaultTreeNode(new ProductSales("Sports appearal", "80k","90k","1billion","2billion","+40%"), node4);
			TreeNode node42 = new DefaultTreeNode(new ProductSales("Team Sports", "80k","90k","1billion","2billion","+40%"), node4);
			TreeNode node43 = new DefaultTreeNode(new ProductSales("Gym Equipments", "80k","90k","1billion","2billion","+40%"), node4);
			TreeNode node44 = new DefaultTreeNode(new ProductSales("Muscle Building", "80k","90k","1billion","2billion","+40%"), node4);
			TreeNode node45 = new DefaultTreeNode(new ProductSales("Weight Loss Equipments", "80k","90k","1billion","2billion","+40%"), node4);
			
			
			TreeNode node000 = new DefaultTreeNode(new ProductSales("Mattress", "80k","90k","1billion","2billion","+40%"), node00);
			TreeNode node001 = new DefaultTreeNode(new ProductSales("Wheelchairs", "80k","90k","1billion","2billion","+40%"), node00);
			TreeNode node002 = new DefaultTreeNode(new ProductSales("Walking and Hearing aids", "80k","90k","1billion","2billion","+40%"), node00);
			TreeNode node003 = new DefaultTreeNode(new ProductSales("Thermometer", "80k","90k","1billion","2billion","+40%"), node01);
			TreeNode node004 = new DefaultTreeNode(new ProductSales("Blood pressure", "80k","90k","1billion","2billion","+40%"), node01);
			TreeNode node005 = new DefaultTreeNode(new ProductSales("Weighing scales", "80k","90k","1billion","2billion","+40%"), node01);
			TreeNode node006 = new DefaultTreeNode(new ProductSales("Massagers", "80k","90k","1billion","2billion","+40%"), node02);
			TreeNode node007 = new DefaultTreeNode(new ProductSales("Heating pads", "80k","90k","1billion","2billion","+40%"), node02);
			TreeNode node008 = new DefaultTreeNode(new ProductSales("Body supports", "80k","90k","1billion","2billion","+40%"), node02);
			TreeNode node009 = new DefaultTreeNode(new ProductSales("Stethescope", "80k","90k","1billion","2billion","+40%"), node03);
			TreeNode node010 = new DefaultTreeNode(new ProductSales("Glucometers", "80k","90k","1billion","2billion","+40%"), node03);
			TreeNode node011 = new DefaultTreeNode(new ProductSales("Blook pressure", "80k","90k","1billion","2billion","+40%"), node03);
			TreeNode node012 = new DefaultTreeNode(new ProductSales("Vaporiser", "80k","90k","1billion","2billion","+40%"), node04);
			TreeNode node013 = new DefaultTreeNode(new ProductSales("Oxygen concentrator", "80k","90k","1billion","2billion","+40%"), node04);
			TreeNode node014 = new DefaultTreeNode(new ProductSales("Weighing scales", "80k","90k","1billion","2billion","+40%"), node05);
			TreeNode node015 = new DefaultTreeNode(new ProductSales("Body fat monitor", "80k","90k","1billion","2billion","+40%"), node05);
			TreeNode node100 = new DefaultTreeNode(new ProductSales("Gluco meters", "80k","90k","1billion","2billion","+40%"), node10);
			TreeNode node101 = new DefaultTreeNode(new ProductSales("Lancets", "80k","90k","1billion","2billion","+40%"), node10);
			TreeNode node102 = new DefaultTreeNode(new ProductSales("Weighing scales", "80k","90k","1billion","2billion","+40%"), node11);
			TreeNode node103 = new DefaultTreeNode(new ProductSales("Body fat monitor", "80k","90k","1billion","2billion","+40%"), node11);
			TreeNode node104 = new DefaultTreeNode(new ProductSales("Treadmills", "80k","90k","1billion","2billion","+40%"), node11);
			TreeNode node105 = new DefaultTreeNode(new ProductSales("Sugar free food", "80k","90k","1billion","2billion","+40%"), node12);
			TreeNode node106 = new DefaultTreeNode(new ProductSales("Sugar free drinks", "80k","90k","1billion","2billion","+40%"), node12);
			TreeNode node200 = new DefaultTreeNode(new ProductSales("Shampoo and Conditioners", "80k","90k","1billion","2billion","+40%"), node20);
			TreeNode node201 = new DefaultTreeNode(new ProductSales("Hair fall and dandruf treatment", "80k","90k","1billion","2billion","+40%"), node20);
			TreeNode node202 = new DefaultTreeNode(new ProductSales("Hair oils", "80k","90k","1billion","2billion","+40%"), node21);
			TreeNode node203 = new DefaultTreeNode(new ProductSales("Creams and locations", "80k","90k","1billion","2billion","+40%"), node21);
			TreeNode node204 = new DefaultTreeNode(new ProductSales("Sun protection", "80k","90k","1billion","2billion","+40%"), node21);
			TreeNode node205 = new DefaultTreeNode(new ProductSales("Cleaners", "80k","90k","1billion","2billion","+40%"), node21);
			TreeNode node206 = new DefaultTreeNode(new ProductSales("Facial parts", "80k","90k","1billion","2billion","+40%"), node22);
			TreeNode node207 = new DefaultTreeNode(new ProductSales("Nails", "80k","90k","1billion","2billion","+40%"), node22);
			TreeNode node208 = new DefaultTreeNode(new ProductSales("Face care", "80k","90k","1billion","2billion","+40%"), node23);
			TreeNode node209 = new DefaultTreeNode(new ProductSales("Hair care", "80k","90k","1billion","2billion","+40%"), node23);
			TreeNode node210 = new DefaultTreeNode(new ProductSales("Perfumes", "80k","90k","1billion","2billion","+40%"), node23);
			TreeNode node300 = new DefaultTreeNode(new ProductSales("Face,hair and nails", "80k","90k","1billion","2billion","+40%"), node30);
			TreeNode node301 = new DefaultTreeNode(new ProductSales("Herbs", "80k","90k","1billion","2billion","+40%"), node30);
			TreeNode node302 = new DefaultTreeNode(new ProductSales("Weight loss", "80k","90k","1billion","2billion","+40%"), node31);
			TreeNode node313 = new DefaultTreeNode(new ProductSales("Weight gain", "80k","90k","1billion","2billion","+40%"), node31);
			TreeNode node400 = new DefaultTreeNode(new ProductSales("Fitness shoes", "80k","90k","1billion","2billion","+40%"), node40);
			TreeNode node401 = new DefaultTreeNode(new ProductSales("Sports shoes", "80k","90k","1billion","2billion","+40%"), node40);
			TreeNode node402 = new DefaultTreeNode(new ProductSales("T-Shirts and Jackets", "80k","90k","1billion","2billion","+40%"), node41);
			TreeNode node403 = new DefaultTreeNode(new ProductSales("Tracks and bags", "80k","90k","1billion","2billion","+40%"), node41);
			TreeNode node404 = new DefaultTreeNode(new ProductSales("Caps and Sunglasses", "80k","90k","1billion","2billion","+40%"), node41);
			TreeNode node405 = new DefaultTreeNode(new ProductSales("Cricket", "80k","90k","1billion","2billion","+40%"), node42);
			TreeNode node406 = new DefaultTreeNode(new ProductSales("Football", "80k","90k","1billion","2billion","+40%"), node42);
			TreeNode node407 = new DefaultTreeNode(new ProductSales("Gloves and Belts", "80k","90k","1billion","2billion","+40%"), node43);
			TreeNode node408 = new DefaultTreeNode(new ProductSales("Clothing and Bags", "80k","90k","1billion","2billion","+40%"), node43);
			TreeNode node409 = new DefaultTreeNode(new ProductSales("Dumbells and Weights", "80k","90k","1billion","2billion","+40%"), node44);
			TreeNode node410 = new DefaultTreeNode(new ProductSales("Pushup bars", "80k","90k","1billion","2billion","+40%"), node44);
			TreeNode node411 = new DefaultTreeNode(new ProductSales("Yoga mats", "80k","90k","1billion","2billion","+40%"), node45);
			TreeNode node412 = new DefaultTreeNode(new ProductSales("Steppers", "80k","90k","1billion","2billion","+40%"), node45);
	        
	}
	
	public Map<String, String> getThemes() {
        return themes;
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
	
	public TreeNode getProductHierarchyRoot() {
		return productHierarchyRoot;
	}

	public void setProductHierarchyRoot(TreeNode productHierarchyRoot) {
		this.productHierarchyRoot = productHierarchyRoot;
	}
	
	public TreeNode getProductSalesRoot() {
		return productSalesRoot;
	}

	public void setProductSalesRoot(TreeNode productSalesRoot) {
		this.productSalesRoot = productSalesRoot;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}
	
	public TreeNode getSelectedProductNode() {
		return selectedProductNode;
	}

	public void setSelectedProductNode(TreeNode selectedProductNode) {
		this.selectedProductNode = selectedProductNode;
	}

}
