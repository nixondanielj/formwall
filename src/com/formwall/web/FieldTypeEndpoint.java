package com.formwall.web;

import java.util.List;

import com.formwall.entities.FieldType;
import com.formwall.entities.FieldTypeRepository;
import com.google.api.server.spi.config.Api;

@Api(name = "formwallApi", version="v1")
public class FieldTypeEndpoint {
	FieldTypeRepository repo = new FieldTypeRepository();
	
	public List<FieldType> list(){
		return repo.getAll();
	}
}
