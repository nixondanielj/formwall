package com.formwall.web.guice;

import com.formwall.repositories.IPermissionRepository;
import com.formwall.repositories.IUserRepository;
import com.formwall.services.IAuthService;
import com.formwall.services.ICustomUserService;
import com.formwall.services.ISessionService;
import com.formwall.services.concrete.CustomAuthService;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class AuthSvcProvider implements Provider<IAuthService> {

	private IUserRepository userRepo;
	private ISessionService sessionSvc;
	private IPermissionRepository permissionRepo;
	private ICustomUserService userSvc;

	@Inject
	public AuthSvcProvider(ICustomUserService userSvc,
			IUserRepository userRepo, ISessionService sessionSvc,
			IPermissionRepository permissionRepo) {
		this.userRepo = userRepo;
		this.sessionSvc = sessionSvc;
		this.permissionRepo = permissionRepo;
		this.userSvc = userSvc;
	}
	
	@Override
	public IAuthService get() {
		return new CustomAuthService(userSvc, userRepo, sessionSvc, permissionRepo);
	}

}
