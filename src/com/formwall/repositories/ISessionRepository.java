package com.formwall.repositories;

import java.util.List;

import com.formwall.entities.CustomUser;
import com.formwall.entities.Session;

public interface ISessionRepository {

	public abstract Session getByAuthCode(String authcode);

	public abstract void persist(Session session);

	public abstract List<Session> getByUser(CustomUser user);

}