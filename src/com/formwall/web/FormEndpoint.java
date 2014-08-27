package com.formwall.web;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.formwall.services.IAuthService;
import com.formwall.services.IFormService;
import com.formwall.services.PaywallException;
import com.formwall.services.PermissionsException;
import com.formwall.web.models.FormFormModel;
import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.UnauthorizedException;

@Api(name = "formwallApi", version = "v1")
public class FormEndpoint {

	private IFormService formSvc;
	private IAuthService authSvc;

	@Inject
	public FormEndpoint(IFormService formSvc, IAuthService authSvc) {
		this.formSvc = formSvc;
		this.authSvc = authSvc;
	}

	public void postForm(FormFormModel model, User user, HttpServletRequest req)
			throws UnauthorizedException {
		if (authSvc.authenticate(AuthHelper.getAuthReq(user, req))) {
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

	public FormFormModel get(@Named("id") String id, User user,
			HttpServletRequest req) throws UnauthorizedException {
		if (authSvc.authenticate(AuthHelper.getAuthReq(user, req))) {
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
}
