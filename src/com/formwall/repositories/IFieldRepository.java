package com.formwall.repositories;

import java.util.List;

import com.formwall.entities.Field;

public interface IFieldRepository {

	public abstract void persist(List<Field> fields);

}