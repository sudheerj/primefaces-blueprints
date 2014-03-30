package com.packt.pfblueprints.controller;

import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import javax.servlet.ServletContext;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.packt.pfblueprints.dao.AccountSummaryDAO;
import com.packt.pfblueprints.model.AccountSummary;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

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
	private CartesianChartModel categoryModel;
	private String base64Str1;
	private String base64Str2;
	private String base64Str3;
	private StreamedContent file1;
	private StreamedContent file2;
	private StreamedContent file3;
	private Boolean pieChartFlag=true;
	ServletContext servletContext = (ServletContext) FacesContext
     	    .getCurrentInstance().getExternalContext().getContext();
    private Integer type;
	
	@PostConstruct
	public void init() { 
		
		accountsInfo=dao.getAllAccounts();
		createPieModel();
		createCategoryModel();
		
	}
	
	private void createPieModel() {
		pieModelUS = new PieChartModel();
		pieModelUK = new PieChartModel();
 
        for(AccountSummary obj:accountsInfo){
        	pieModelUS.set(obj.getAccountType(),new Double(obj.getBalanceUS()));
        	pieModelUK.set(obj.getAccountType(),new Double(obj.getBalanceUK()));
        }
    }
	
	private void createCategoryModel() {  
        categoryModel = new CartesianChartModel();
        
        ChartSeries balanceUS = new ChartSeries();
        ChartSeries balanceUK = new ChartSeries();
  
        for(AccountSummary obj:accountsInfo){
        	balanceUS.set(obj.getAccountType(),new Double(obj.getBalanceUS()));
        	balanceUK.set(obj.getAccountType(),new Double(obj.getBalanceUK()));
        }
        
          
        balanceUS.setLabel("US_Balance");  
        balanceUK.setLabel("UK_Balance");  
  
        categoryModel.addSeries(balanceUS);  
        categoryModel.addSeries(balanceUK);  
    }  
	
	public void piechartUSBase64Str(){
		 
		 InputStream stream1 = servletContext.getResourceAsStream("/images/pie1.png");
		 file1 = new DefaultStreamedContent(stream1, "image/png", "US_Piechart.png");
	    if(base64Str1.split(",").length > 1){
	        String encoded = base64Str1.split(",")[1];
	        byte[] decoded = Base64.decodeBase64(encoded);
	        // Write to a .png file
	        try {
	            RenderedImage renderedImage = ImageIO.read(new ByteArrayInputStream(decoded));
	            ImageIO.write(renderedImage, "png", new File(servletContext.getRealPath("images/pie1.png"))); 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	   
	}
	
	public void piechartUKBase64Str(){
		 InputStream stream2 = servletContext.getResourceAsStream("/images/pie2.png");
		 file2 = new DefaultStreamedContent(stream2, "image/png", "Uk_Piechart.png");
		
	    if(base64Str2.split(",").length > 1){
	        String encoded = base64Str2.split(",")[1];
	        byte[] decoded = Base64.decodeBase64(encoded);
	        // Write to a .png file
	        try {
	            RenderedImage renderedImage = ImageIO.read(new ByteArrayInputStream(decoded));
	            ImageIO.write(renderedImage, "png", new File(servletContext.getRealPath("images/pie2.png"))); 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public void barchartBase64Str(){
		 InputStream stream2 = servletContext.getResourceAsStream("/images/bar.png");
		 file3 = new DefaultStreamedContent(stream2, "image/png", "BarChart.png");
		
	    if(base64Str3.split(",").length > 1){
	        String encoded = base64Str3.split(",")[1];
	        byte[] decoded = Base64.decodeBase64(encoded);
	        // Write to a .png file
	        try {
	            RenderedImage renderedImage = ImageIO.read(new ByteArrayInputStream(decoded));
	            ImageIO.write(renderedImage, "png", new File(servletContext.getRealPath("images/bar.png"))); 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public void changeOption(){
		if(type==1){
			pieChartFlag=true;	
		}else{
			pieChartFlag=false;
		}
	}
	
	public String storeSelectedAccount(){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("accountNumber", accountobj.getAccountNumber());
		return "investmentsummary.xhtml?faces-redirect=true";
	}
	
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {  
	    Document pdf = (Document) document;  
	    pdf.open();  
	    pdf.setPageSize(PageSize.A4);  
	  
	    ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();  
	    String logo = servletContext.getRealPath("") + File.separator +"resources" + File.separator + "images" + File.separator +"logo" + File.separator + "logo.png";  
	  
	    pdf.add(Image.getInstance(logo));  
	}  
	
	public void postProcessPDF(Object document) throws IOException, BadElementException, DocumentException {  
	     
	}  
	
	public void preProcessXLS(Object document) {  
	   
	}  
	
	public void postProcessXLS(Object document) {  
	    HSSFWorkbook wb = (HSSFWorkbook) document;  
	    HSSFSheet sheet = wb.getSheetAt(0);  
	    HSSFRow header = sheet.getRow(0);  
	      
	    HSSFCellStyle cellStyle = wb.createCellStyle();    
	    cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);  
	    cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
	      
	    for(int i=0; i < header.getPhysicalNumberOfCells();i++) {  
	        HSSFCell cell = header.getCell(i);  
	          
	        cell.setCellStyle(cellStyle);  
	    }  
	}  
	

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

	public StreamedContent getFile1() {
		return file1;
	}

	public void setFile1(StreamedContent file1) {
		this.file1 = file1;
	}

	public StreamedContent getFile2() {
		return file2;
	}

	public void setFile2(StreamedContent file2) {
		this.file2 = file2;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public CartesianChartModel getCategoryModel() {
		return categoryModel;
	}

	public void setCategoryModel(CartesianChartModel categoryModel) {
		this.categoryModel = categoryModel;
	}

	public String getBase64Str3() {
		return base64Str3;
	}

	public void setBase64Str3(String base64Str3) {
		this.base64Str3 = base64Str3;
	}

	public StreamedContent getFile3() {
		return file3;
	}

	public void setFile3(StreamedContent file3) {
		this.file3 = file3;
	}

	public Boolean getPieChartFlag() {
		return pieChartFlag;
	}

	public void setPieChartFlag(Boolean pieChartFlag) {
		this.pieChartFlag = pieChartFlag;
	}

	
}
