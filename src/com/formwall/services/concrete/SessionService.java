package com.formwall.services.concrete;

import java.util.Date;

import javax.inject.Inject;

import com.formwall.entities.CustomUser;
import com.formwall.entities.Session;
import com.formwall.repositories.ISessionRepository;
import com.formwall.services.ISessionService;
import com.formwall.web.models.SessionVM;

public class SessionService implements ISessionService {

	private ISessionRepository sessionRepo;
	@Inject
	public SessionService(ISessionRepository sessionRepo){
		this.sessionRepo = sessionRepo;
	}
	private  void renewSession(Session session) {
		session.setExpiration(new Date(new Date().getTime() + 15 * 60 * 1000));
		sessionRepo.persist(session);
	}
	@Override
	public SessionVM beginSession(CustomUser user) {
		Session session = new Session();
		session.setUser(user);
		// abusing the side effect of creation on the call to persist in renewsession...
		renewSession(session);
		SessionVM vm = new SessionVM();
		vm.token = session.getId();
		return vm;
	}
	public boolean isValidSession(Session session) {
		return session != null && session.getExpiration().after(new Date());
	}
	@Override
	public CustomUser retrieveUser(String authcode) {
		Session session = sessionRepo.getByAuthCode(authcode);
		if(isValidSession(session)){
			renewSession(session);
			return session.getUser();
		}
		return null;
	}

}
