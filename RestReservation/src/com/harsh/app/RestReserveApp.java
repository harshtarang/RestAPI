package com.harsh.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class RestReserveApp extends ResourceConfig{
	
	
	public RestReserveApp()
	{
		
		packages("com.harsh.rest");
	}
}
