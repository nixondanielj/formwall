package com.formwall.repositories;

import com.formwall.entities.Session;
import com.formwall.entities.User;

public interface ISessionRepository {

	public abstract Session createSessionForUser(User user);

}