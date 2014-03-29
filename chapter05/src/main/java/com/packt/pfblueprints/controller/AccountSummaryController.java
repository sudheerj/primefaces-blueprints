package com.packt.pfblueprints.controller;

import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.imageio.ImageIO;

import org.primefaces.model.chart.PieChartModel;

import com.packt.pfblueprints.dao.AccountSummaryDAO;
import com.packt.pfblueprints.model.AccountSummary;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;


@ManagedBean
@ViewScoped
public class AccountSummaryController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<AccountSummary> accountsInfo=new ArrayList<AccountSummary>();
	
	private AccountSummary accountobj=new AccountSummary();
	AccountSummaryDAO dao = new AccountSummaryDAO();
	private PieChartModel pieModelUS;
	private PieChartModel pieModelUK;
	private String base64Str1;
	private String base64Str2;
	
	@PostConstruct
	public void init() { 
		
		accountsInfo=dao.getAllAccounts();
		createPieModel();
		
	}
	
	private void createPieModel() {
		pieModelUS = new PieChartModel();
		pieModelUK = new PieChartModel();
 
        for(AccountSummary obj:accountsInfo){
        	pieModelUS.set(obj.getAccountType(),new Double(obj.getBalanceUS()));
        	pieModelUK.set(obj.getAccountType(),new Double(obj.getBalanceUK()));
        }
    }
	
	public void submittedBase64Str(ActionEvent event){
	   
	    if(base64Str1.split(",").length > 1){
	        String encoded = base64Str1.split(",")[1];
	        byte[] decoded = Base64.decode(encoded);
	        // Write to a .png file
	        try {
	            RenderedImage renderedImage = ImageIO.read(new ByteArrayInputStream(decoded));
	            ImageIO.write(renderedImage, "png", new File(ServletContext().getRealPath("relative/path/to/your/file"))); // use a proper path & file name here.
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    if(base64Str2.split(",").length > 1){
	        String encoded = base64Str2.split(",")[1];
	        byte[] decoded = Base64.decode(encoded);
	        // Write to a .png file
	        try {
	            RenderedImage renderedImage = ImageIO.read(new ByteArrayInputStream(decoded));
	            ImageIO.write(renderedImage, "png", new File("C:\\out.png")); // use a proper path & file name here.
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	/*public void deleteDealer(){
		accountsInfo=dao.deleteDealer(accountobj);
	}*/

	public List<AccountSummary> getAccountsInfo() {
		return accountsInfo;
	}

	public void setAccountsInfo(List<AccountSummary> accountsInfo) {
		this.accountsInfo = accountsInfo;
	}

	
	public PieChartModel getPieModelUS() {
		return pieModelUS;
	}

	public void setPieModelUS(PieChartModel pieModelUS) {
		this.pieModelUS = pieModelUS;
	}

	public PieChartModel getPieModelUK() {
		return pieModelUK;
	}

	public void setPieModelUK(PieChartModel pieModelUK) {
		this.pieModelUK = pieModelUK;
	}

	public AccountSummary getAccountobj() {
		return accountobj;
	}

	public void setAccountobj(AccountSummary accountobj) {
		this.accountobj = accountobj;
	}

	public String getBase64Str1() {
		return base64Str1;
	}

	public void setBase64Str1(String base64Str1) {
		this.base64Str1 = base64Str1;
	}

	public String getBase64Str2() {
		return base64Str2;
	}

	public void setBase64Str2(String base64Str2) {
		this.base64Str2 = base64Str2;
	}

	

	
}
