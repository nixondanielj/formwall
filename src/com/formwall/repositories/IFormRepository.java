package com.formwall.repositories;

import com.formwall.entities.Form;

public interface IFormRepository {

	public void persist(Form form);

	public Form getById(Long id);

}
