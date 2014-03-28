package com.packt.pfblueprints.controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.view.ViewScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.annotation.PostConstruct;

import org.primefaces.event.FlowEvent;

import com.packt.pfblueprints.dao.RegistrationDAO;
import com.packt.pfblueprints.model.Employee;

@ManagedBean
@SessionScoped
public class RegistrationController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Employee employee = new Employee();

	private Map<String, String> countries = new HashMap<String, String>();
	private Map<String, Map<String, String>> citiesData = new HashMap<String, Map<String, String>>();
	private Map<String, String> cities = new HashMap<String, String>();

	private List<String> allFrameworks = new ArrayList<String>();

	private List<String> allDBs = new ArrayList<String>();

	private List<String> allIDEs = new ArrayList<String>();

	private List<SelectItem> allServers;

	private Boolean skip;

	@SuppressWarnings("restriction")
	@PostConstruct
	public void init() {

		countries.put("UK", "UK");
		countries.put("US", "US");
		countries.put("India", "India");
		countries.put("Germany", "Germany");

		Map<String, String> citiesUK = new HashMap<String, String>();
		citiesUK.put("London", "London");
		citiesUK.put("Bristol", "Bristol");
		citiesUK.put("Derby", "Derby");

		Map<String, String> citiesUS = new HashMap<String, String>();
		citiesUS.put("Newyork", "Newyork");
		citiesUS.put("California", "California");
		citiesUS.put("Washington", "Washington");

		Map<String, String> citiesIndia = new HashMap<String, String>();
		citiesIndia.put("Delhi", "Delhi");
		citiesIndia.put("Chennai", "Chennai");
		citiesIndia.put("Banglore", "Banglore");

		Map<String, String> citiesGermany = new HashMap<String, String>();
		citiesGermany.put("Berlin", "Berlin");
		citiesGermany.put("Hamburg", "Hamburg");
		citiesGermany.put("Bavaria", "Bavaria");

		citiesData.put("UK", citiesUK);
		citiesData.put("US", citiesUS);
		citiesData.put("India", citiesIndia);
		citiesData.put("Germany", citiesGermany);

		allFrameworks.add("JSF");
		allFrameworks.add("Spring");
		allFrameworks.add("Struts");
		allFrameworks.add("Grails");

		allDBs.add("Oracle");
		allDBs.add("MySQL");
		allDBs.add("SQLServer");
		allDBs.add("DB2");

		allIDEs.add("Eclipse");
		allIDEs.add("NetBeans");
		allIDEs.add("Intellij");
		allIDEs.add("JDeveloper");

		allServers = new ArrayList<SelectItem>();
		SelectItemGroup group1 = new SelectItemGroup("Application Servers");
		SelectItemGroup group2 = new SelectItemGroup("Web servers");

		SelectItemGroup group11 = new SelectItemGroup("Open source");
		SelectItemGroup group12 = new SelectItemGroup("Proprietary");

		SelectItemGroup group21 = new SelectItemGroup("Open source");

		SelectItem option111 = new SelectItem("Glassfish");
		SelectItem option112 = new SelectItem("Jboss");
		SelectItem option113 = new SelectItem("TomEE");
		group11.setSelectItems(new SelectItem[] { option111, option112,
				option113 });

		SelectItem option121 = new SelectItem("Wellogic");
		SelectItem option122 = new SelectItem("Websphere");
		group12.setSelectItems(new SelectItem[] { option121, option122 });

		SelectItem option211 = new SelectItem("Tomcat");
		SelectItem option222 = new SelectItem("Jetty");
		SelectItem option223 = new SelectItem("Resin");
		group21.setSelectItems(new SelectItem[] { option211, option222,
				option223 });

		group1.setSelectItems(new SelectItem[] { group11, group12 });
		group2.setSelectItems(new SelectItem[] { group21 });

		allServers.add(group1);
		allServers.add(group2);
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Boolean getSkip() {
		return skip;
	}

	public void setSkip(Boolean skip) {
		this.skip = skip;
	}

	public Map<String, String> getCountries() {
		return countries;
	}

	public void setCountries(Map<String, String> countries) {
		this.countries = countries;
	}

	public Map<String, String> getCities() {
		return cities;
	}

	public void setCities(Map<String, String> cities) {
		this.cities = cities;
	}

	public List<String> getAllFrameworks() {
		return allFrameworks;
	}

	public void setAllFrameworks(List<String> allFrameworks) {
		this.allFrameworks = allFrameworks;
	}

	public List<String> getAllDBs() {
		return allDBs;
	}

	public void setAllDBs(List<String> allDBs) {
		this.allDBs = allDBs;
	}

	public List<String> getAllIDEs() {
		return allIDEs;
	}

	public void setAllIDEs(List<String> allIDEs) {
		this.allIDEs = allIDEs;
	}

	public List<SelectItem> getAllServers() {
		return allServers;
	}

	public void setAllServers(List<SelectItem> allServers) {
		this.allServers = allServers;
	}

	public String onFlowProcess(FlowEvent event) {

		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

	public void handleCountryChange(ValueChangeEvent event) {
		String city = (String) event.getNewValue();
		if (city != null && !city.equals(""))
			cities = citiesData.get(city);
		else
			cities = new HashMap<String, String>();
	}

	public void saveEmployee(ActionEvent actionEvent) throws SQLException {
		RegistrationDAO dao = new RegistrationDAO();
		String uniqueID = UUID.randomUUID().toString().substring(0, 8);
		boolean confirm=false;
		try {
			confirm = dao.saveEmployee(employee, uniqueID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (confirm) {
			FacesMessage msg = new FacesMessage("Registration is successful",
					"Welcome to our Jobsite.Your temporary password is:"
							+ uniqueID);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			FacesMessage msg = new FacesMessage("Registration is unsuccessful",
					"Please try with valid data");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	public List<String> complete(String query) {
		List<String> results = new ArrayList<String>();

		for (int i = 0; i < 10; i++) {
			results.add(query + i);
		}

		return results;
	}

}
