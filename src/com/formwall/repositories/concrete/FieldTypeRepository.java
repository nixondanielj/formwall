package com.formwall.repositories.concrete;

import java.util.ArrayList;
import java.util.List;

import com.formwall.entities.FieldType;
import com.formwall.repositories.BaseRepository;
import com.formwall.repositories.IFieldTypeRepository;
import com.google.appengine.api.datastore.Entity;

public class FieldTypeRepository extends BaseRepository implements IFieldTypeRepository {
	/* (non-Javadoc)
	 * @see com.formwall.repositories.concrete.IFieldTypeRepository#getAll()
	 */
	@Override
	public List<FieldType> getAll(){
		ArrayList<FieldType> results = new ArrayList<FieldType>();
		for(Entity entity : getIterable(FieldType.class.getSimpleName())){
			results.add(tryMapFromEntity(entity, FieldType.class));
		}
		return results;
	}
	
}
