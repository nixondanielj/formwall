package com.formwall.repositories.concrete;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.formwall.entities.CustomUser;
import com.formwall.entities.Form;
import com.formwall.entities.Permission;
import com.formwall.repositories.IPermissionRepository;

public class PermissionRepository implements IPermissionRepository {

	@Override
	public List<Permission> getByUser(CustomUser currentUser) {
		return ofy().load().type(Permission.class).filter("user", currentUser)
				.list();
	}

	@Override
	public void persist(Permission permission) {
		ofy().save().entities(permission);
	}

	@Override
	public void delete(Permission oldPermission) {
		ofy().delete().entity(oldPermission);
	}

	@Override
	public List<Permission> get(Form form, CustomUser currentUser) {
		return ofy().load().type(Permission.class).filter("form", form)
				.filter("user", currentUser).list();
	}

}
