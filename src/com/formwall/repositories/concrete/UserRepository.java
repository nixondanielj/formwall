package com.formwall.repositories.concrete;

import java.util.logging.Logger;

import com.formwall.entities.CustomUser;
import com.formwall.repositories.BaseRepository;
import com.formwall.repositories.IUserRepository;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class UserRepository extends BaseRepository implements IUserRepository {
	private final static Logger logger = Logger.getLogger(UserRepository.class.getName());
	/* (non-Javadoc)
	 * @see com.formwall.repositories.concrete.IUserRepository#create(com.formwall.entities.concrete.User)
	 */
	@Override
	public CustomUser create(CustomUser user){
		logger.info("Creating user " + user.getEmail());
		user.setId(persist(user.toEntity()).getKey());
		return user;
	}
	
	/* (non-Javadoc)
	 * @see com.formwall.repositories.concrete.IUserRepository#getByEmail(java.lang.String)
	 */
	@Override
	public CustomUser getByEmail(String email){
		CustomUser u = null;
		Filter emailFilter = new FilterPredicate("email", FilterOperator.EQUAL, email);
		Entity userEntity = datastore.prepare(new Query(CustomUser.class.getSimpleName()).setFilter(emailFilter))
				.asSingleEntity();
		if(userEntity != null){
			u = new CustomUser(userEntity);
		}
		return u;
	}
}
