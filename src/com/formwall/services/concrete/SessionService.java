package com.formwall.services.concrete;

import java.util.Date;

import com.formwall.entities.CustomUser;
import com.formwall.entities.Session;
import com.formwall.repositories.ISessionRepository;
import com.formwall.services.ISessionService;

public class SessionService implements ISessionService {

	private ISessionRepository sessionRepo;
	public SessionService(ISessionRepository sessionRepo){
		this.sessionRepo = sessionRepo;
	}
	@Override
	public boolean isValidSession(String authcode) {
		Session session = sessionRepo.getByAuthCode(authcode);
		if(session != null && session.getExpiration().after(new Date())){
			renewSession(session);
			return true;
		}
		return false;
	}
	private void renewSession(Session session) {
		session.setExpiration(new Date(new Date().getTime() + 15 * 60 * 1000));
		sessionRepo.persist(session);
	}
	@Override
	public Session beginSession(CustomUser user) {
		Session session = new Session();
		session.setUserId(user.getId());
		renewSession(session);
		return session;
	}

}
