package com.formwall.web;

import java.util.List;

import javax.inject.Inject;

import com.formwall.entities.FieldType;
import com.formwall.repositories.IFieldTypeRepository;
import com.google.api.server.spi.config.Api;

@Api(name = "formwallApi", version="v1", clientIds={"616786009709-f6g6dm3jaj8jbobng8eo5dp3augg2dmj.apps.googleusercontent.com", com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID})
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
