package com.formwall.web;

import java.util.List;

import javax.inject.Inject;

import com.formwall.entities.FieldType;
import com.formwall.repositories.IFieldTypeRepository;
import com.google.api.server.spi.config.Api;

@Api(name = "formwallApi", version="v1")
public class FieldTypeEndpoint {
	IFieldTypeRepository repo;
	
	@Inject
	public FieldTypeEndpoint(IFieldTypeRepository repo){
		this.repo = repo;
	}
	
	public List<FieldType> list(){
		return repo.getAll();
	}
}
