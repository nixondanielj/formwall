package com.formwall.repositories;

import com.formwall.entities.CustomUser;

public interface IUserRepository {

	public abstract CustomUser create(CustomUser user);

	public abstract CustomUser getByEmail(String email);

}