package com.formwall.repositories;

import com.formwall.entities.CustomUser;
import com.formwall.entities.Session;

public interface ISessionRepository {

	public abstract Session createSessionForUser(CustomUser user);

	public abstract Session getByAuthCode(String authcode);

	public abstract void persist(Session session);

}