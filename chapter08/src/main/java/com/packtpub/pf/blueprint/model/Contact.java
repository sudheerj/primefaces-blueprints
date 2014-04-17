package com.packtpub.pf.blueprint.model;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity  
@Table(name="tb_contact")  
public class Contact implements Serializable{
	
	@GeneratedValue
	@Id
	private Long id;
	private String name;
	private String email;

	public String toString() {
		return this.id + " " + this.name + " " + this.email;
	}
}
