package com.formwall.repositories.concrete;

import java.util.ArrayList;
import java.util.List;

import com.formwall.entities.CustomUser;
import com.formwall.entities.Session;
import com.formwall.repositories.BaseRepository;
import com.formwall.repositories.ISessionRepository;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

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
	public Session createSessionForUser(CustomUser user){
		Session session = new Session();
		session.setUserId(user.getId());
		Entity e = tryMapToEntity(Session.class,session);
		persist(e);
		return session;
	}

	@Override
	public Session getByAuthCode(String authcode){
		try {
			Key key = KeyFactory.stringToKey(authcode);
			Entity sessionEntity = datastore.get(key);
			return tryMapFromEntity(sessionEntity, Session.class);
		} catch (EntityNotFoundException e) {
		} catch (IllegalArgumentException e){
		}
		return null;
	}

	@Override
	public void persist(Session session){
		persist(tryMapToEntity(Session.class,session));
	}

	@Override
	public List<Session> getByUser(CustomUser user){
		Query query = new Query(Session.class.getSimpleName());
		Filter filter = new FilterPredicate("userId", FilterOperator.EQUAL, user.getId());
		query.setFilter(filter);
		List<Session> results = new ArrayList<Session>();
		for(Entity e : datastore.prepare(query).asIterable()){
			results.add(tryMapFromEntity(e, Session.class));
		}
		return results;
	}

}
