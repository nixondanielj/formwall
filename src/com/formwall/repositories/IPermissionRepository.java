package com.formwall.repositories;

import java.util.List;

import com.formwall.entities.CustomUser;
import com.formwall.entities.Form;
import com.formwall.entities.Permission;

public interface IPermissionRepository {

	List<Permission> getByUser(CustomUser currentUser);

	void persist(Permission permission);

	void delete(Permission oldPermission);

	Permission get(Form form, CustomUser currentUser);

}
