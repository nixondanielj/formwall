package com.formwall.services.concrete;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.formwall.entities.Field;
import com.formwall.entities.FieldType;
import com.formwall.entities.Form;
import com.formwall.repositories.IFieldRepository;
import com.formwall.repositories.IFieldTypeRepository;
import com.formwall.repositories.IFormRepository;
import com.formwall.services.FieldFM;
import com.formwall.services.FormFM;
import com.formwall.services.IAuthService;
import com.formwall.services.IFormService;
import com.formwall.services.PaywallException;
import com.formwall.services.PermissionLevels;
import com.formwall.services.PermissionsException;
import com.google.inject.Provider;

public class FormService implements IFormService {

	private Provider<IAuthService> authSvcPrvdr;
	private IFormRepository formRepo;
	private IFieldTypeRepository fieldTypeRepo;
	private IFieldRepository fieldRepo;

	@Inject
	public FormService(IFormRepository formRepo, Provider<IAuthService> authSvcPrvdr, IFieldTypeRepository fieldTypeRepo, IFieldRepository fieldRepo){
		this.formRepo = formRepo;
		this.authSvcPrvdr = authSvcPrvdr;
		this.fieldTypeRepo = fieldTypeRepo;
		this.fieldRepo = fieldRepo;
	}
	
	/*
	 * if form is new
	 *   if user is free
	 *     if exceeded max forms
	 *       throw PaywallException
	 * else
	 *   if permissions are not sufficient
	 *     throw PermissionsException
	 * persist form
	 */
	@Override
	public void persist(FormFM model) throws PermissionsException, PaywallException {
		Form form;
		if(model.getId() == null){
			if(authSvcPrvdr.get().hasMaxForms()){
				throw new PaywallException();
			}
			form = new Form();
		} else {
			form = formRepo.getById(model.getId());
			if (!authSvcPrvdr.get().canEdit(form)){
				throw new PermissionsException();
			}
		}
		form.setActive(model.isActive());
		if(model.isAvailableNow()){
			form.setAvailabilityStart(new Date());
		}
		form.setAvailabilityEnd(model.getAvailabilityEnd());
		form.setCustomId(model.getCustomId());
		form.setTitle(model.getTitle());
		List<Field> fields = new ArrayList<Field>();
		for(FieldFM fm : model.getFormFields()){
			fields.add(map(fm));
		}
		fieldRepo.persist(fields);
		form.addFields(fields);
		formRepo.persist(form);
		authSvcPrvdr.get().addPermission(form, PermissionLevels.Owner);
	}

	private Field map(FieldFM fm) {
		FieldType type = fieldTypeRepo.getById(fm.getFieldTypeId());
		Field field = new Field();
		field.setId(fm.getId());
		field.setFieldType(type);
		field.setLabel(fm.getLabel());
		field.setRequired(fm.isRequired());
		field.setPlaceholder(fm.getPlaceholder());
		return field;
	}

	@Override
	public FormFM getFMById(Long id) throws PermissionsException {
		FormFM fm = new FormFM();
		fm.setAvailableFieldTypes(fieldTypeRepo.getAll());
		Form form = formRepo.getById(id);
		if(form != null){
			if(!authSvcPrvdr.get().canEdit(form)){
				throw new PermissionsException();
			}
			fm.setActive(form.isActive());
			fm.setId(id);
			fm.setTitle(form.getTitle());
			fm.setAvailabilityStart(form.getAvailabilityStart());
			fm.setAvailabilityEnd(form.getAvailabilityEnd());
			fm.setCustomId(form.getCustomId());
			fm.setButtonText(form.getButtonText());
			for(Field field : form.getFields()){
				fm.getFormFields().add(map(field));
			}
		}
		return fm;
	}

	private FieldFM map(Field field) {
		FieldFM fm = new FieldFM();
		fm.setFieldTypeId(field.getFieldType().getId());
		fm.setId(field.getId());
		fm.setLabel(field.getLabel());
		fm.setRequired(field.isRequired());
		return fm;
	}

	@Override
	public FormFM getNewForm() {
		FormFM fm = new FormFM();
		fm.setAvailableFieldTypes(fieldTypeRepo.getAll());
		fm.setActive(false);
		fm.setTitle("My New Form");
		FieldFM field = new FieldFM();
		field.setLabel("Field 1");
		field.setFieldTypeId(fieldTypeRepo.getByHtmlType("text").getId());
		fm.setFormFields(new ArrayList<FieldFM>());
		fm.getFormFields().add(field);
		return fm;
	}

}
