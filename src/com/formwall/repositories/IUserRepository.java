package com.formwall.repositories;

import com.formwall.entities.User;

public interface IUserRepository {

	public abstract void create(User user);

	public abstract User getByEmail(String email);

}