package com.formwall.repositories.concrete;

import com.formwall.entities.Session;
import com.formwall.entities.User;
import com.formwall.repositories.BaseRepository;
import com.formwall.repositories.ISessionRepository;
import com.google.appengine.api.datastore.Entity;

public class SessionRepository extends BaseRepository implements ISessionRepository {

	/* (non-Javadoc)
	 * @see com.formwall.repositories.concrete.ISessionRepository#createSessionForUser(com.formwall.entities.IUser)
	 */
	@Override
	public Session createSessionForUser(User user) {
		Session session = new Session();
		session.setUserId(user.getId());
		Entity e = session.toEntity();
		persist(e);
		return new Session(e);
	}

}
