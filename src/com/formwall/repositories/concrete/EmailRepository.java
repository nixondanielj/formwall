package com.formwall.repositories.concrete;

import com.formwall.entities.Email;
import com.formwall.repositories.BaseRepository;
import com.formwall.repositories.IEmailRepository;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class EmailRepository extends BaseRepository implements IEmailRepository {
	/* (non-Javadoc)
	 * @see com.formwall.repositories.concrete.IEmailRepository#getByName(java.lang.String)
	 */
	@Override
	public Email getByName(String name){
		Filter nameFilter = new FilterPredicate("name", FilterOperator.EQUAL, name);
		return new Email(
				datastore.prepare(new Query(Email.class.getSimpleName()).setFilter(nameFilter)).asSingleEntity()
		);
	}
	
	public void Seed(){
		
	}

}
