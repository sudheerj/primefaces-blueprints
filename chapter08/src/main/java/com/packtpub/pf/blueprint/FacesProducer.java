package com.packtpub.pf.blueprint;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@ApplicationScoped
public class FacesProducer {

	private FacesContext context = FacesContext.getCurrentInstance();
	
	@Produces
	public FacesContext getFaces() {
		return context;
	}
	
	
}
