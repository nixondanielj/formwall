package com.formwall.entities;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class UserRepository extends Repository {
	public void create(User user){
		user.setId(persist(user.toEntity()).getKey());
	}
	
	public User getByEmail(String email){
		User u = null;
		Filter emailFilter = new FilterPredicate("email", FilterOperator.EQUAL, email);
		Entity userEntity = datastore.prepare(new Query(User.class.getSimpleName()).setFilter(emailFilter))
				.asSingleEntity();
		if(userEntity != null){
			u = new User(userEntity);
		}
		return u;
	}
}
