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
	private  void renewSession(Session session) {
		session.setExpiration(new Date(new Date().getTime() + 15 * 60 * 1000));
		sessionRepo.persist(session);
	}
	@Override
	public Session beginSession(CustomUser user) {
		Session session = new Session();
		session.setUserId(user.getId());
		// abusing the side effect of creation on the call to persist in renewsession...
		renewSession(session);
		return session;
	}
	@Override
	public boolean isValidSession(Session session) {
		boolean isValid = session != null && session.getExpiration().after(new Date());
		if(isValid){
			renewSession(session);
		}
		return isValid;
	}
	@Override
	public Session retrieveSession(String authCode) {
		Session session = sessionRepo.getByAuthCode(authCode);
		if(!isValidSession(session)){
			session = null;
		}
		return session;
	}
	@Override
	public Session retrieveSession(CustomUser user) {
		// set it to null - if we don't find one, we'll return this null
		Session lastSession = null;
		// find the session with the latest expiration date
		for(Session session : sessionRepo.getByUser(user)){
			if(lastSession == null || session.getExpiration().after(lastSession.getExpiration())){
				lastSession = session;
			}
		}
		// if we found one, but it is not valid, return null
		if(lastSession != null && !isValidSession(lastSession)){
			lastSession = null;
		}
		return lastSession;
	}

}
