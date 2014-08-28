package com.formwall.repositories.concrete;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.formwall.entities.Form;
import com.formwall.repositories.BaseRepository;
import com.formwall.repositories.IFormRepository;

public class FormRepository extends BaseRepository implements IFormRepository {
	public void persist(Form form){
		ofy().save().entity(form).now();
	}
}
