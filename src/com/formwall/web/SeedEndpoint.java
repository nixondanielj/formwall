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
import com.google.inject.Provider;

@Api(name = "formwallApi", version = "v1", clientIds={"616786009709-f6g6dm3jaj8jbobng8eo5dp3augg2dmj.apps.googleusercontent.com"})
public class SeedEndpoint {
	private ISeeder seeder;
	private Provider<IAuthService> authSvcPrvdr;

	@Inject
	public SeedEndpoint(ISeeder seeder, Provider<IAuthService> authSvcPrvdr) {
		this.seeder = seeder;
		this.authSvcPrvdr = authSvcPrvdr;
	}

	public void seed(@Named("clear") @Nullable boolean clear, User user,
			HttpServletRequest req) throws UnauthorizedException {
		if (authSvcPrvdr.get().authenticate(AuthHelper.getAuthReq(user, req))) {
			seeder.seed(clear);
		} else {
			throw new UnauthorizedException(AuthFailures.login.name());
		}
	}
}
