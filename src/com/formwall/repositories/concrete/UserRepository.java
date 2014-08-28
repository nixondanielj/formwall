package com.formwall.repositories.concrete;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.logging.Logger;

import com.formwall.entities.CustomUser;
import com.formwall.repositories.BaseRepository;
import com.formwall.repositories.IUserRepository;

public class UserRepository extends BaseRepository implements IUserRepository {
	private final static Logger logger = Logger.getLogger(UserRepository.class.getName());
	/* (non-Javadoc)
	 * @see com.formwall.repositories.concrete.IUserRepository#create(com.formwall.entities.concrete.User)
	 */
	@Override
	public void persist(CustomUser user){
		logger.info("Creating user " + user.getEmail());
		ofy().save().entity(user);
	}
	
	/* (non-Javadoc)
	 * @see com.formwall.repositories.concrete.IUserRepository#getByEmail(java.lang.String)
	 */
	@Override
	public CustomUser getByEmail(String email){
		return ofy().load().type(CustomUser.class).filter("email", email).first().now();
	}

	@Override
	public CustomUser getById(Long id) {
		return ofy().load().type(CustomUser.class).id(id).now();
	}
}
