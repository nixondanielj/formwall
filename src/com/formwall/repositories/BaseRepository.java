package com.formwall.repositories;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;

public abstract class BaseRepository {
	protected DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	protected Iterable<Entity> getIterable(String type){
		return datastore.prepare(new Query(type)).asIterable();
	}
	protected Entity persist(Entity entity){
		datastore.put(entity);
		return entity;
	}
}
