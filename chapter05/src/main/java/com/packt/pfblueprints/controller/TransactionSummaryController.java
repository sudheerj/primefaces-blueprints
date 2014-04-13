package com.packt.pfblueprints.controller;

import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.chart.DonutChartModel;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.packt.pfblueprints.dao.TransactionSummaryDAO;
import com.packt.pfblueprints.model.TransactionSummary;

@ManagedBean
@ViewScoped
public class TransactionSummaryController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<TransactionSummary> transactionsInfo = new ArrayList<TransactionSummary>();

	TransactionSummaryDAO dao = new TransactionSummaryDAO();
	private DonutChartModel donutModel;
	private Integer optionValue=1;
	private Boolean pageOnly=false;
	private String base64Str;
	private StreamedContent file;
	ServletContext servletContext = (ServletContext) FacesContext
     	    .getCurrentInstance().getExternalContext().getContext();

	@PostConstruct
	public void init() {

		transactionsInfo = dao.getAllTransactions();
		createDonutModel();
	}

	public String displayAllTransactions() {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("investmentNumber", "");
		return "transactionsummary.xhtml?faces-redirect=true";
	}

	public void preProcessPDF(Object document) throws IOException,
			BadElementException, DocumentException {
		Document pdf = (Document) document;
		pdf.setPageSize(PageSize.A4);
		pdf.open();

		ServletContext servletContext = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();
		String logo = servletContext.getRealPath("") + File.separator
				+ "resources" + File.separator + "images" + File.separator
				+ "logo" + File.separator + "logo.png";
		Image image = Image.getInstance(logo);
		image.scaleAbsolute(100f, 50f);
		pdf.add(image);
		// add a couple of blank lines
		pdf.add(Chunk.NEWLINE);
		pdf.add(Chunk.NEWLINE);
		Font fontbold = FontFactory.getFont("Times-Roman", 16, Font.BOLD);
		fontbold.setColor(55, 55, 55);
		;
		pdf.add(new Paragraph("Transaction Summary", fontbold));
		// add a couple of blank lines
		pdf.add(Chunk.NEWLINE);
		pdf.add(Chunk.NEWLINE);
	}

	public void postProcessPDF(Object document) throws IOException,
			BadElementException, DocumentException {
		Document pdf = (Document) document;
		pdf.add(Chunk.NEWLINE);
		Font fontbold = FontFactory.getFont("Times-Roman", 14, Font.BOLD);
		pdf.add(new Paragraph("Disclaimer", fontbold));
		pdf.add(Chunk.NEWLINE);
		pdf.add(new Paragraph(
				"The information contained in this website is for information purposes only, and does not constitute, nor is it intended to constitute, the provision of financial product advice."));
		pdf.add(new Paragraph(
				"This website is intended to track the investor account summary information,investments and transaction in a partcular period of time. "));
	}

	public void postProcessXLS(Object document) {
		HSSFWorkbook wb = (HSSFWorkbook) document;
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow header = sheet.getRow(0);

		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
			HSSFCell cell = header.getCell(i);

			cell.setCellStyle(cellStyle);
		}

		Row row = sheet.createRow((short) sheet.getLastRowNum() + 3);
		Cell cellDisclaimer = row.createCell(0);
		HSSFFont customFont = wb.createFont();
		customFont.setFontHeightInPoints((short) 10);
		customFont.setFontName("Arial");
		customFont.setColor(IndexedColors.BLACK.getIndex());
		customFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		customFont.setItalic(true);

		cellDisclaimer.setCellValue("Disclaimer");
		HSSFCellStyle cellStyleDisclaimer = wb.createCellStyle();
		cellStyleDisclaimer.setFont(customFont);
		cellDisclaimer.setCellStyle(cellStyleDisclaimer);

		Row row1 = sheet.createRow(sheet.getLastRowNum() + 2);
		Cell cellDisclaimerContent1 = row1.createCell(0);
		cellDisclaimerContent1
				.setCellValue("The information contained in this website is for information purposes only, and does not constitute, nor is it intended to constitute, the provision of financial product advice.");

		Row row2 = sheet.createRow(sheet.getLastRowNum() + 1);
		Cell cellDisclaimerContent2 = row2.createCell(0);
		cellDisclaimerContent2
				.setCellValue("This website is intended to track the investor account summary information,investments and transaction in a partcular period of time. ");

	}

	private void createDonutModel() {
		donutModel = new DonutChartModel();
		Map<String, Number> circle1 = new LinkedHashMap<String, Number>();
		Map<String, Number> circle2 = new LinkedHashMap<String, Number>();
		Map<String, Number> circle3 = new LinkedHashMap<String, Number>();
		Map<String, Number> circle4 = new LinkedHashMap<String, Number>();

		for (TransactionSummary obj : transactionsInfo) {
			if (obj.getTransactiontype().equalsIgnoreCase("Sell")) {
				circle1.put(obj.getPaymenttype(),
						new Integer(obj.getNetamount()));
			}
			if (obj.getTransactiontype().equalsIgnoreCase("BUY")) {
				circle2.put(obj.getPaymenttype(),
						new Integer(obj.getNetamount()));
			}
			if (obj.getTransactiontype().equalsIgnoreCase("TransferIn")) {
				circle3.put(obj.getPaymenttype(),
						new Integer(obj.getNetamount()));
			}
			if (obj.getTransactiontype().equalsIgnoreCase("TransferOut")) {
				circle4.put(obj.getPaymenttype(),
						new Integer(obj.getNetamount()));
			}

		}
		donutModel.addCircle(circle1);
		donutModel.addCircle(circle2);
		donutModel.addCircle(circle3);
		donutModel.addCircle(circle4);
		
		donutModel.setTitle("Transaction Summary");
		donutModel.setLegendPosition("e");
		donutModel.setSliceMargin(5);
		donutModel.setShowDataLabels(true);
		donutModel.setDataFormat("percent");
		donutModel.setShadow(false);
	}
	
	public void changeExportOption() {
		if(optionValue==1){
			pageOnly=false;
		}else{
			pageOnly=true;
		}
	}
	
	public void donutchartBase64Str(){
		 InputStream stream = servletContext.getResourceAsStream("/images/donut.png");
		 file = new DefaultStreamedContent(stream, "image/png", "DonutChart.png");
		
	    if(base64Str.split(",").length > 1){
	        String encoded = base64Str.split(",")[1];
	        byte[] decoded = Base64.decodeBase64(encoded);
	        // Write to a .png file
	        try {
	            RenderedImage renderedImage = ImageIO.read(new ByteArrayInputStream(decoded));
	            ImageIO.write(renderedImage, "png", new File(servletContext.getRealPath("images/donut.png"))); 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}

	public List<TransactionSummary> getTransactionsInfo() {
		return transactionsInfo;
	}

	public void setTransactionsInfo(List<TransactionSummary> transactionsInfo) {
		this.transactionsInfo = transactionsInfo;
	}

	public DonutChartModel getDonutModel() {
		return donutModel;
	}

	public void setDonutModel(DonutChartModel donutModel) {
		this.donutModel = donutModel;
	}

	public int getOptionValue() {
		return optionValue;
	}

	public void setOptionValue(int optionValue) {
		this.optionValue = optionValue;
	}

	public Boolean getPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(Boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public String getBase64Str() {
		return base64Str;
	}

	public void setBase64Str(String base64Str) {
		this.base64Str = base64Str;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	
	
}
