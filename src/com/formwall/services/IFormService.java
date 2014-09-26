package com.formwall.services;


public interface IFormService {

	void persist(FormFM model) throws PermissionsException, PaywallException;

	FormFM getFMById(Long id) throws PermissionsException;

	FormFM getNewForm();
	
}
