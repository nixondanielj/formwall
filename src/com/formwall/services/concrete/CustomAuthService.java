package com.formwall.services.concrete;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.formwall.entities.CustomUser;
import com.formwall.entities.Form;
import com.formwall.entities.Permission;
import com.formwall.repositories.IPermissionRepository;
import com.formwall.repositories.IUserRepository;
import com.formwall.services.Credentials;
import com.formwall.services.IAuthService;
import com.formwall.services.ICustomUserService;
import com.formwall.services.ISessionService;
import com.formwall.services.PermissionLevels;
import com.formwall.services.Roles;
import com.formwall.web.models.AuthenticationRequest;

public class CustomAuthService implements IAuthService {

	private IUserRepository userRepo;
	private ISessionService sessionSvc;
	private CustomUser currentUser;
	private IPermissionRepository permissionRepo;
	private ICustomUserService userSvc;
	private final static Logger logger = Logger.getLogger(CustomAuthService.class.getName());

	@Inject
	public CustomAuthService(ICustomUserService userSvc,
			IUserRepository userRepo, ISessionService sessionSvc,
			IPermissionRepository permissionRepo) {
		logger.info("Creating auth service");
		this.userRepo = userRepo;
		this.sessionSvc = sessionSvc;
		this.permissionRepo = permissionRepo;
		this.userSvc = userSvc;
	}

	@Override
	public void addRoleToUser(CustomUser user, Roles role) {
		if (!user.getRoles().contains(role.name())) {
			user.getRoles().add(role.name());
			userRepo.persist(user);
		}
	}

	@Override
	public boolean authenticate(Credentials credentials) {
		CustomUser user = userRepo.getByEmail(credentials.getUsername());
		if (user != null) {
			if(user.getPassword() == credentials.getPassword()){
				this.currentUser = user;
				sessionSvc.beginSession(user);
			}
		}
		return getCurrentUser() != null;
	}

	@Override
	public String generatePassword() {
		String random = UUID.randomUUID().toString();
		return random.substring(random.lastIndexOf("-") + 1);
	}

	@Override
	public CustomUser getCurrentUser() {
		return this.currentUser;
	}

	@Override
	public boolean hasPermission(Form form, PermissionLevels permission) {
		if (this.getCurrentUser() != null) {
			List<Permission> permissions = permissionRepo.getByUser(this
					.getCurrentUser());
			for (Permission p : permissions) {
				if (p.getForm().getId() == form.getId()) {
					PermissionLevels fp = PermissionLevels
							.valueOf(p.getLevel());
					if (fp == permission || fp == PermissionLevels.Owner) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean hasMaxForms() {
		// TODO need to put some real thought into this one...
		boolean canHave = false;
		if(getCurrentUser() != null){
			int currentForms = 0;
			for(Permission permission : permissionRepo.getByUser(getCurrentUser())){
				PermissionLevels pl = PermissionLevels.valueOf(permission.getLevel());
				if(pl == PermissionLevels.Editor || pl == PermissionLevels.Owner){
					currentForms++;
				}
			}
			return currentForms >= 3;
		}
		return canHave;
	}

	@Override
	public void addPermission(Form form, PermissionLevels permissionLevel) {
		if (getCurrentUser() != null && !hasPermission(form, permissionLevel)) {
			Permission oldPermission = permissionRepo.get(form, getCurrentUser());
			if(oldPermission != null){
				permissionRepo.delete(oldPermission);
			}
			Permission permission = new Permission();
			permission.setForm(form);
			permission.setUser(this.getCurrentUser());
			permission.setLevel(permissionLevel.name());
			permissionRepo.persist(permission);
		}
	}

	@Override
	public boolean authenticate(AuthenticationRequest request) {
		if (request.email != null) {
			if (!userSvc.alreadyExists(request.email)) {
				this.currentUser = userSvc.registerGoogle(request.email);
			} else {
				this.currentUser = userRepo.getByEmail(request.email);
			}
		} else if(request.authCode != null){
			this.currentUser = sessionSvc.retrieveUser(request.authCode);
		}
		return getCurrentUser() != null;
	}

}
