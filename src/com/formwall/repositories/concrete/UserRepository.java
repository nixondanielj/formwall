package com.formwall.repositories.concrete;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.formwall.entities.CustomUser;
import com.formwall.repositories.BaseRepository;
import com.formwall.repositories.IUserRepository;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.KeyFactory;
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
		user.setId(KeyFactory.keyToString(persist(tryMapToEntity(CustomUser.class, user)).getKey()));
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
			u = tryMapFromEntity(userEntity, CustomUser.class);
		}
		return u;
	}

	@Override
	public void persist(CustomUser user) {
		persist(tryMapToEntity(CustomUser.class, user));
	}

	@Override
	public CustomUser getById(String id) {
		CustomUser user = null;
		try{
			Entity entity = datastore.get(KeyFactory.stringToKey(id));
			user = tryMapFromEntity(entity, CustomUser.class);
			logger.info(String.format("Retrieved user %s from store", user.getEmail()));
			
		} catch(EntityNotFoundException e){
			logger.log(Level.WARNING, String.format("User retrieval by key failed, key %s", id.toString()), e);
		}
		return user;
	}
}
