package com.formwall.repositories;

import java.util.List;

import com.formwall.entities.CustomUser;
import com.formwall.entities.Permission;

public interface IPermissionRepository {

	List<Permission> getByUser(CustomUser currentUser);

	Permission persist(Permission permission);

}
