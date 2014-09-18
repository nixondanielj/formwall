package com.formwall.repositories.concrete;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.formwall.entities.CustomUser;
import com.formwall.entities.Session;
import com.formwall.repositories.BaseRepository;
import com.formwall.repositories.ISessionRepository;

public class SessionRepository extends BaseRepository implements
		ISessionRepository {

	@Override
	public Session getByAuthCode(String authcode){
		return ofy().load().type(Session.class).id(Long.parseLong(authcode)).now();
	}

	@Override
	public void persist(Session session){
		ofy().save().entity(session).now();
	}

	@Override
	public List<Session> getByUser(CustomUser user){
		return ofy().load().type(Session.class).filter("user", user).list();
	}

}
