package com.formwall.repositories.concrete;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.formwall.entities.CustomUser;
import com.formwall.repositories.BaseRepository;
import com.formwall.repositories.IUserRepository;

public class UserRepository extends BaseRepository implements IUserRepository {
	/* (non-Javadoc)
	 * @see com.formwall.repositories.concrete.IUserRepository#create(com.formwall.entities.concrete.User)
	 */
	@Override
	public void persist(CustomUser user){
		ofy().save().entity(user).now();
	}
	
	/* (non-Javadoc)
	 * @see com.formwall.repositories.concrete.IUserRepository#getByEmail(java.lang.String)
	 */
	@Override
	public CustomUser getByEmail(String email){
		return ofy().load().type(CustomUser.class).id(email).now();
	}

	@Override
	public CustomUser getById(Long id) {
		return ofy().load().type(CustomUser.class).id(id).now();
	}
}
