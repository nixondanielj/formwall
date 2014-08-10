package com.formwall.web;

import javax.inject.Inject;

import com.formwall.utils.ISeeder;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

@Api(name = "formwallApi", version = "v1")
public class SeedEndpoint {
	private ISeeder seeder;
	@Inject
	public SeedEndpoint(ISeeder seeder){
		this.seeder = seeder;
	}
	public void seed(@Named("clear") @Nullable boolean clear){
		seeder.seed(clear);
	}
}
