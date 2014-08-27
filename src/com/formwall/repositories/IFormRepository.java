package com.formwall.repositories;

import java.util.List;

import com.formwall.entities.CustomUser;
import com.formwall.entities.Form;

public interface IFormRepository {
	public List<Form> getByUser(CustomUser user);

	public Form persist(Form form, CustomUser parent);

	public void persist(Form form);

}
