package com.formwall.services.concrete;

import javax.inject.Inject;

import com.formwall.repositories.IFormRepository;
import com.formwall.services.IAuthService;
import com.formwall.services.IFormService;
import com.formwall.services.PaywallException;
import com.formwall.services.PermissionLevels;
import com.formwall.services.PermissionsException;
import com.formwall.web.models.FormFormModel;

public class FormService implements IFormService {

	private IAuthService authSvc;
	private IFormRepository formRepo;

	@Inject
	public FormService(IFormRepository formRepo, IAuthService authSvc){
		this.formRepo = formRepo;
		this.authSvc = authSvc;
	}
	
	/*
	 * if form is new
	 *   if user is free
	 *     if exceeded max forms and not in valid trial
	 *       throw PaywallException
	 * else
	 *   if permissions are not sufficient
	 *     throw PermissionsException
	 * persist form
	 */
	@Override
	public void persist(FormFormModel model) throws PermissionsException, PaywallException {
		if(model.form.getId() == null){
			if(authSvc.hasMaxForms()){
				throw new PaywallException();
			}
		} else if (!authSvc.hasPermission(model.form, PermissionLevels.Editor)){
			throw new PermissionsException();
		}
		formRepo.persist(model.form);
		try{
			authSvc.addPermission(model.form, PermissionLevels.Owner);
			model.form.addFields(model.fields);
		} catch (Exception e){
			// TODO rollback form, throw error
		}
		
	}

	@Override
	public FormFormModel getFMById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
