package com.formwall.repositories;

import com.formwall.entities.Email;

public interface IEmailRepository {

	public abstract Email getByName(String name);

}