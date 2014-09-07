package com.formwall.repositories.concrete;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.formwall.entities.Field;
import com.formwall.repositories.IFieldRepository;

public class FieldRepository implements IFieldRepository {
	/* (non-Javadoc)
	 * @see com.formwall.repositories.concrete.IFieldRepository#persist(com.formwall.entities.Field)
	 */
	@Override
	public void persist(List<Field> fields){
		ofy().save().entities(fields).now();
	}
}
