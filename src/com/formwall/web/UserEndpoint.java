package com.formwall.web;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.MessagingException;

import com.formwall.entities.CustomUser;
import com.formwall.services.ICustomUserService;
import com.formwall.services.ISessionService;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.response.ConflictException;

@Api(name = "formwallApi", version = "v1")
public class UserEndpoint {
	private ICustomUserService usrSvc;
	private ISessionService sessionSvc;
	
	@Inject
	public UserEndpoint(ICustomUserService usrSvc, ISessionService sessionSvc){
		this.sessionSvc = sessionSvc;
		this.usrSvc = usrSvc;
	}

	public String register(@Named("email") String email)
			throws ConflictException, UnsupportedEncodingException, MessagingException {
		if(usrSvc.alreadyExists(email)){
			throw new ConflictException("User already exists");
		}
		CustomUser user = usrSvc.registerByEmail(email);
		return sessionSvc.beginSession(user).getAuthCode();
	}
}
