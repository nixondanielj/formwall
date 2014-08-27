package com.formwall.services;

import com.formwall.web.models.FormFormModel;

public interface IFormService {

	void persist(FormFormModel model) throws PermissionsException, PaywallException;

	FormFormModel getById(String id);
	
}
