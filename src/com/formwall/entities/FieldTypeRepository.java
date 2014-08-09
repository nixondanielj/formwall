package com.formwall.entities;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

public class FieldTypeRepository extends Repository {
	public List<FieldType> getAll(){
		ArrayList<FieldType> results = new ArrayList<FieldType>();
		for(Entity entity : getIterable(FieldType.class.getSimpleName())){
			results.add(new FieldType(entity));
		}
		if(results.size() == 0){
			Seed();
			return getAll();
		}
		return results;
	}
	
	private void create(FieldType type){
		persist(type.toEntity());
	}
	
	public void Seed(){
		List<Key> keysToDelete = new ArrayList<Key>();
		for(Entity e : getIterable(FieldType.class.getSimpleName())){
			keysToDelete.add(e.getKey());
		}
		if(keysToDelete.size() > 0){
			datastore.delete(keysToDelete);
		}
		create(new FieldType(){{
			setHtmlType("text");
		}});
		create(new FieldType(){{
			setHtmlType("email");
			setRegexValidator(".*@.*\\..*");
		}});
	}
}
