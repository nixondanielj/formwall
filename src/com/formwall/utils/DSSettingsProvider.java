package com.formwall.utils;

import javax.inject.Singleton;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

@Singleton
public class DSSettingsProvider implements ISettingsProvider{
	private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	private MemcacheService cache = MemcacheServiceFactory.getMemcacheService();

	@Override
	public String getAdminEmail() {
		return retrieveFromDS("adminEmail").toString();
	}
	
	private Object retrieve(String term){
		Object value = cache.get(term);
		if(value == null){
			value = retrieveFromDS(term);
			if(value != null){
				cache.put(term, value, Expiration.byDeltaSeconds(24*3600));
			}
		}
		return value;
	}
	
	private Object retrieveFromDS(String term){
		Filter filter = new FilterPredicate("term", FilterOperator.EQUAL, term);
		Query query = new Query("Setting").setFilter(filter);
		Entity e = datastore.prepare(query).asSingleEntity();
		if(e != null) return e.getProperty("value");
		return null;
	}
	@Override
	public String getWelcomeMessageName() {
		return retrieve("welcome").toString();
	}
	
}
