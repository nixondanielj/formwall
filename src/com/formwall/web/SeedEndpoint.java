package com.formwall.web;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.formwall.services.IAuthService;
import com.formwall.utils.ISeeder;
import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.UnauthorizedException;

@Api(name = "formwallApi", version = "v1")
public class SeedEndpoint {
	private ISeeder seeder;
	private IAuthService authSvc;

	@Inject
	public SeedEndpoint(ISeeder seeder, IAuthService authSvc) {
		this.seeder = seeder;
		this.authSvc = authSvc;
	}

	public void seed(@Named("clear") @Nullable boolean clear, User user,
			HttpServletRequest req) throws UnauthorizedException {
		if (authSvc.authenticate(AuthHelper.getAuthReq(user, req))) {
			seeder.seed(clear);
		} else {
			throw new UnauthorizedException(AuthFailures.login.name());
		}
	}
}
