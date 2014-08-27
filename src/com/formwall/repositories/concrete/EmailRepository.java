package com.formwall.repositories.concrete;

import com.formwall.entities.Email;
import com.formwall.repositories.BaseRepository;
import com.formwall.repositories.IEmailRepository;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class EmailRepository extends BaseRepository implements IEmailRepository {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.formwall.repositories.concrete.IEmailRepository#getByName(java.lang
	 * .String)
	 */
	@Override
	public Email getByName(String name){
		Filter nameFilter = new FilterPredicate("name", FilterOperator.EQUAL,
				name);
		Entity e = datastore.prepare(
				new Query(Email.class.getSimpleName()).setFilter(nameFilter))
				.asSingleEntity();
		Email email = tryMapFromEntity(e, Email.class);
		return email;
	}

}
