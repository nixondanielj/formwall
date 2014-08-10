package com.formwall.repositories;

import java.util.List;

import com.formwall.entities.FieldType;

public interface IFieldTypeRepository {

	public abstract List<FieldType> getAll();

}