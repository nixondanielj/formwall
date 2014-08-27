package com.formwall.web;

import javax.inject.Inject;

import com.formwall.services.IFormService;
import com.formwall.services.PaywallException;
import com.formwall.services.PermissionsException;
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
		try {
			formSvc.persist(model);
		} catch (PermissionsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PaywallException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FormFormModel get(@Named("id") String id){
		return formSvc.getById(id);
	}
}
