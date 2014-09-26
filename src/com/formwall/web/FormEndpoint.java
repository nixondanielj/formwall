package com.formwall.web;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.formwall.services.FormFM;
import com.formwall.services.IAuthService;
import com.formwall.services.IFormService;
import com.formwall.services.PaywallException;
import com.formwall.services.PermissionsException;
import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.inject.Provider;

@Api(name = "formwallApi", version = "v1", clientIds={"616786009709-f6g6dm3jaj8jbobng8eo5dp3augg2dmj.apps.googleusercontent.com", com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID})
public class FormEndpoint {

	private IFormService formSvc;
	private Provider<IAuthService> authSvcPrvdr;

	@Inject
	public FormEndpoint(IFormService formSvc, Provider<IAuthService> authSvcPrvdr) {
		this.formSvc = formSvc;
		this.authSvcPrvdr = authSvcPrvdr;
	}

	public void postForm(FormFM model, User user, HttpServletRequest req)
			throws UnauthorizedException {
		if (authSvcPrvdr.get().authenticate(AuthHelper.getAuthReq(user, req))) {
			try {
				formSvc.persist(model);
			} catch (PermissionsException e) {
				e.printStackTrace();
				throw new UnauthorizedException(AuthFailures.permissions.name());
			} catch (PaywallException e) {
				e.printStackTrace();
				throw new UnauthorizedException(AuthFailures.paywall.name());
			}
		} else {
			throw new UnauthorizedException(AuthFailures.login.name());
		}
	}

	public FormFM get(@Named("id") Long id, User user,
			HttpServletRequest req) throws UnauthorizedException {
		if (authSvcPrvdr.get().authenticate(AuthHelper.getAuthReq(user, req))) {
			try {
				return formSvc.getFMById(id);
			} catch (PermissionsException e) {
				e.printStackTrace();
				throw new UnauthorizedException(AuthFailures.permissions.name());
			}
		} else {
			throw new UnauthorizedException(AuthFailures.login.name());
		}
	}
	
	public FormFM getNew(User user, HttpServletRequest req) throws UnauthorizedException {
		if(authSvcPrvdr.get().authenticate(AuthHelper.getAuthReq(user, req))){
			return formSvc.getNewForm();
		} else {
			throw new UnauthorizedException(AuthFailures.login.name());
		}
	}
}
