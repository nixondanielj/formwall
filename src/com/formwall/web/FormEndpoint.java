package com.formwall.web;

import javax.inject.Inject;

import com.formwall.services.IFormService;
import com.formwall.web.models.FormFormModel;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.Named;

@Api(name = "formwallApi", version="v1")
public class FormEndpoint {
	
	private IFormService formSvc;

	@Inject
	public FormEndpoint(IFormService formSvc){
		this.formSvc = formSvc;
	}
	
	public void postForm(FormFormModel model){
		formSvc.persist(model);
	}
	
	public FormFormModel get(@Named("id") String id){
		return formSvc.getById(id);
	}
}
