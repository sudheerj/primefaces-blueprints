package com.packtpub.pf.blueprint.controller.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Data;
import com.packtpub.pf.blueprint.FacesUtil;
import com.packtpub.pf.blueprint.model.Contact;
import com.packtpub.pf.blueprint.persistence.ContactDAO;

@Data
@Named
@RequestScoped
public class ContactMB implements Serializable{

	private static final long serialVersionUID = -1890125026548028469L;
	private Contact bean = new Contact();
	private Long idToDelete;
	private List<Contact> contacts = new ArrayList<>();
	private String cid;
	
	@Inject
	private ContactDAO contactDAO;
	
	public String save() {
		contactDAO.persist(this.bean);
		return "list.xhtml";
	}

	public String add() {
		return "add.xhtml";
	}
	
	public String edit() {
		return "edit.xhtml";
	}
	
	public String delete() {
		String value = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		contactDAO.delete(Long.valueOf(value));
		FacesUtil.addSuccessMessage("Deletado!");
		return "list.xhtml";
	}

	public void remove() {
		contactDAO.delete(idToDelete);
		FacesUtil.addSuccessMessage("Deletado!");
	}
	
	public List<Contact> getContacts() {
		return contactDAO.findAll();
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public Long getIdToDelete() {
		return idToDelete;
	}

	public void setIdToDelete(Long idToDelete) {
		this.idToDelete = idToDelete;
	}
	
}
