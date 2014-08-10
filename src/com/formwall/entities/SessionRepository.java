package com.formwall.entities;

import com.google.appengine.api.datastore.Entity;

public class SessionRepository extends Repository {

	public Session createSessionForUser(User user) {
		Session session = new Session();
		session.setUserId(user.getId());
		Entity e = session.toEntity();
		persist(e);
		return new Session(e);
	}

}
