package com.formwall.repositories;

import java.lang.reflect.Field;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;

public abstract class BaseRepository {
	protected DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();

	protected Iterable<Entity> getIterable(String type) {
		return datastore.prepare(new Query(type)).asIterable();
	}

	protected Entity persist(Entity entity) {
		datastore.put(entity);
		return entity;
	}

	protected <T> Entity tryMapToEntity(Class<T> clazz, T item){
		try {
			Entity entity = new Entity(clazz.getSimpleName());
			for (Field field : clazz.getDeclaredFields()) {
				if (field.getName() == "id") {
					KeyFactory.stringToKey(field.get(item).toString());
				} else {
					entity.setProperty(field.getName(), field.get(item));
				}
			}
			return entity;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	protected <T> T tryMapFromEntity(Entity e, Class<T> clazz){
		try {
			T t = clazz.newInstance();
			for (Field field : clazz.getDeclaredFields()) {
				if(field.getName() == "id"){
					field.set(t, KeyFactory.keyToString(e.getKey()));
				} else if (e.getProperty(field.getName()) != null) {
					field.set(
							t,
							tryMapField(e.getProperty(field.getName()),
									field.getType()));
				}
			}
			return t;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	private <T> T tryMapField(Object property, Class<T> fieldType) {
		try {
			return (T) property;
		} catch (Exception e) {
			return null;
		}
	}
}
