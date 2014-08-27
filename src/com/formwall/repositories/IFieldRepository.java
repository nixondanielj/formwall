package com.formwall.repositories;

import com.formwall.entities.Field;
import com.formwall.entities.Form;

public interface IFieldRepository {

	Field persist(Field field, Form form);

}
