package com.formwall.services.concrete;

import java.util.Date;

import javax.inject.Inject;

import com.formwall.entities.CustomUser;
import com.formwall.entities.Session;
import com.formwall.repositories.ISessionRepository;
import com.formwall.repositories.IUserRepository;
import com.formwall.services.ISessionService;

public class SessionService implements ISessionService {

	private ISessionRepository sessionRepo;
	private IUserRepository userRepo;
	@Inject
	public SessionService(ISessionRepository sessionRepo, IUserRepository userRepo){
		this.sessionRepo = sessionRepo;
		this.userRepo = userRepo;
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
	public boolean isValidSession(Session session) {
		return session != null && session.getExpiration().after(new Date());
	}
	@Override
	public CustomUser retrieveUser(String authcode) {
		Session session = sessionRepo.getByAuthCode(authcode);
		if(isValidSession(session)){
			renewSession(session);
			return userRepo.getById(session.getUserId());
		}
		return null;
	}

}
