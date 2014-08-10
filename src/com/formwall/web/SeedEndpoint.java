package com.formwall.web;

import javax.inject.Inject;

import com.formwall.utils.ISeeder;
import com.google.api.server.spi.config.Api;

@Api(name = "formwallApi", version = "v1")
public class SeedEndpoint {
	private ISeeder seeder;
	@Inject
	public SeedEndpoint(ISeeder seeder){
		this.seeder = seeder;
	}
	public void Seed(){
		seeder.seed();
	}
}
