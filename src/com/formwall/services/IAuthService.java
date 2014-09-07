package com.formwall.services;

import com.formwall.entities.CustomUser;
import com.formwall.entities.Form;
import com.formwall.web.models.AuthenticationRequest;

public interface IAuthService {
	public void addRoleToUser(CustomUser user, Roles role);

	public boolean authenticate(Credentials credentials);

	public String generatePassword();

	public CustomUser getCurrentUser();

	public boolean hasPermission(Form form, PermissionLevels permission);

	public boolean hasMaxForms();

	public void addPermission(Form form, PermissionLevels permission);

	public boolean authenticate(AuthenticationRequest request);
	
	public boolean isOwner(Form form);
	
	public boolean canEdit(Form form);
	
	public boolean canViewReport(Form form);
	
	public boolean canViewData(Form form);
}
