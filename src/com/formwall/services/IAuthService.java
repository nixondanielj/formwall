package com.formwall.services;

import com.formwall.entities.CustomUser;
import com.formwall.entities.Form;

public interface IAuthService {
	public void addRoleToUser(CustomUser user, Roles role);

	public boolean authenticate(Credentials credentials);

	public String generatePassword();

	public CustomUser getCurrentUser();

	public boolean hasPermission(Form form, PermissionLevels permission);

	public boolean canHaveMoreForms();

	public void addPermission(Form form, PermissionLevels permission);

	public boolean authenticate(AuthenticationRequest request);
}
