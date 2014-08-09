package com.formwall.entities;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;

public abstract class Repository {
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	protected Iterable<Entity> getIterable(String type){
		return datastore.prepare(new Query(type)).asIterable();
	}
	protected void persist(Entity entity){
		datastore.put(entity);
	}
}
