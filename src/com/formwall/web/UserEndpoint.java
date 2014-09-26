package com.formwall.web;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.MessagingException;

import com.formwall.entities.CustomUser;
import com.formwall.services.Credentials;
import com.formwall.services.DuplicateException;
import com.formwall.services.IAuthService;
import com.formwall.services.ICustomUserService;
import com.formwall.services.ISessionService;
import com.formwall.web.models.SessionVM;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.inject.Provider;

@Api(name = "formwallApi", version = "v1", clientIds={"616786009709-f6g6dm3jaj8jbobng8eo5dp3augg2dmj.apps.googleusercontent.com", com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID})
public class UserEndpoint {
	private ICustomUserService usrSvc;
	private ISessionService sessionSvc;
	private Provider<IAuthService> authSvcPrvdr;
	
	@Inject
	public UserEndpoint(ICustomUserService usrSvc, ISessionService sessionSvc, Provider<IAuthService> authSvc){
		this.sessionSvc = sessionSvc;
		this.usrSvc = usrSvc;
		this.authSvcPrvdr = authSvc;
	}
	
	public SessionVM signin(Credentials credentials) throws UnauthorizedException{
		SessionVM session = authSvcPrvdr.get().authenticate(credentials);
		if(session == null){
			throw new UnauthorizedException(AuthFailures.login.name());
		}
		return session;
	}

	public SessionVM register(@Named("email") String email)
			throws ConflictException, UnsupportedEncodingException, MessagingException {
		try{
			CustomUser user = usrSvc.registerCustom(email);
			return sessionSvc.beginSession(user);
		} catch(DuplicateException e){
			throw new ConflictException("User already exists");
		}
	}
}
