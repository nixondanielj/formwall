package com.formwall.repositories;

import com.formwall.entities.CustomUser;
import com.google.appengine.api.datastore.Key;

public interface IUserRepository {

	public abstract CustomUser create(CustomUser user);

	public abstract CustomUser getByEmail(String email);

	public abstract void persist(CustomUser user);

	public abstract CustomUser getById(Key userId);

}