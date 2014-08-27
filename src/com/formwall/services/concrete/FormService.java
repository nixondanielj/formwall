package com.formwall.services.concrete;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.formwall.entities.Field;
import com.formwall.entities.Form;
import com.formwall.repositories.IFieldRepository;
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
	private IFieldRepository fieldRepo;

	@Inject
	public FormService(IFormRepository formRepo, IAuthService authSvc, IFieldRepository fieldRepo){
		this.formRepo = formRepo;
		this.authSvc = authSvc;
		this.fieldRepo = fieldRepo;
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
			if(!authSvc.canHaveMoreForms()){
				throw new PaywallException();
			}
		} else if (!authSvc.hasPermission(model.form, PermissionLevels.Editor)){
			throw new PermissionsException();
		}
		model.form = formRepo.persist(model.form, authSvc.getCurrentUser());
		try{
			authSvc.addPermission(model.form, PermissionLevels.Owner);
			addFields(model.form, (Field[]) model.fields.toArray());
		} catch (Exception e){
			// TODO rollback form, throw error
		}
		
	}

	public void addFields(Form form, Field... fields) {
		List<String> fieldIds = new ArrayList<String>();
		for(Field field : fields){
			fieldRepo.persist(field, form);
			fieldIds.add(field.getId());
		}
		form.setFieldIds(fieldIds);
		formRepo.persist(form);
	}

	@Override
	public FormFormModel getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
