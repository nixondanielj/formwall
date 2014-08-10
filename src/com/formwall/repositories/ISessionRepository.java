package com.formwall.repositories;

import com.formwall.entities.Session;
import com.formwall.entities.CustomUser;

public interface ISessionRepository {

	public abstract Session createSessionForUser(CustomUser user);

}