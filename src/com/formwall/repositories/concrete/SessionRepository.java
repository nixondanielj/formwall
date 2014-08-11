package com.formwall.repositories.concrete;

import com.formwall.entities.CustomUser;
import com.formwall.entities.Session;
import com.formwall.repositories.BaseRepository;
import com.formwall.repositories.ISessionRepository;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class SessionRepository extends BaseRepository implements
		ISessionRepository {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.formwall.repositories.concrete.ISessionRepository#createSessionForUser
	 * (com.formwall.entities.IUser)
	 */
	@Override
	public Session createSessionForUser(CustomUser user) {
		Session session = new Session();
		session.setUserId(user.getId());
		Entity e = session.toEntity();
		persist(e);
		return new Session(e);
	}

	@Override
	public Session getByAuthCode(String authcode) {
		try {
			Key key = KeyFactory.stringToKey(authcode);
			Entity sessionEntity = datastore.get(key);
			return new Session(sessionEntity);
		} catch (EntityNotFoundException e) {
		} catch (IllegalArgumentException e){
		}
		return null;
	}

	@Override
	public void persist(Session session) {
		persist(session.toEntity());
	}

}
