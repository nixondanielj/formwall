package com.formwall.repositories.concrete;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.formwall.entities.FieldType;
import com.formwall.repositories.BaseRepository;
import com.formwall.repositories.IFieldTypeRepository;

public class FieldTypeRepository extends BaseRepository implements IFieldTypeRepository {
	/* (non-Javadoc)
	 * @see com.formwall.repositories.concrete.IFieldTypeRepository#getAll()
	 */
	@Override
	public List<FieldType> getAll(){
		return ofy().load().type(FieldType.class).list();
	}

	@Override
	public FieldType getById(Long fieldTypeId) {
		return ofy().load().type(FieldType.class).id(fieldTypeId).now();
	}

	@Override
	public FieldType getByHtmlType(String type) {
		return ofy().load().type(FieldType.class).filter("htmlType", type).first().now();
	}
	
	
}
