package com.harsh.app;

import io.swagger.jaxrs.config.BeanConfig;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class RestReserveApp extends ResourceConfig{
	
	
	public RestReserveApp()
	{
		
		packages("com.harsh.rest");
		
		register(io.swagger.jaxrs.listing.ApiListingResource.class);
		register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
		
		BeanConfig config = new BeanConfig();
		config.setBasePath("/RestReservation/api");
		config.setDescription("Restaurant Reservation System");
		config.setVersion("1.0");
		config.setSchemes(new String [] {"http"});
		config.setResourcePackage("com.harsh");
		config.setTitle("RestReservation App API");
		config.setScan(true);
	}
}
