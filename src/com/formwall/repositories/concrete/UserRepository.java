package com.formwall.repositories.concrete;

import com.formwall.entities.User;
import com.formwall.repositories.BaseRepository;
import com.formwall.repositories.IUserRepository;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class UserRepository extends BaseRepository implements IUserRepository {
	/* (non-Javadoc)
	 * @see com.formwall.repositories.concrete.IUserRepository#create(com.formwall.entities.concrete.User)
	 */
	@Override
	public void create(User user){
		user.setId(persist(user.toEntity()).getKey());
	}
	
	/* (non-Javadoc)
	 * @see com.formwall.repositories.concrete.IUserRepository#getByEmail(java.lang.String)
	 */
	@Override
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
