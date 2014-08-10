package com.formwall.web.guice;

import java.util.HashSet;
import java.util.Set;

import com.formwall.web.FieldTypeEndpoint;
import com.formwall.web.SeedEndpoint;
import com.formwall.web.UserEndpoint;
import com.google.api.server.spi.guice.GuiceSystemServiceServletModule;

public class GuiceSSSModule extends GuiceSystemServiceServletModule {
	@Override
	protected void configureServlets(){
		super.configureServlets();
		Set<Class<?>> serviceClasses = new HashSet<Class<?>>();
		serviceClasses.add(UserEndpoint.class);
		serviceClasses.add(FieldTypeEndpoint.class);
		serviceClasses.add(SeedEndpoint.class);
		this.serveGuiceSystemServiceServlet("/_ah/spi/*", serviceClasses);
	}
}
