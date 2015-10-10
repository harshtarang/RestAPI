package com.harsh.rest.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;


import com.harsh.dao.SettingsDAO;
import com.harsh.model.Settings;


@Path("/settings")
public class SettingsController {

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Settings getSettings()
	{
		
		try 
		{
			SettingsDAO dao=new SettingsDAO();
			return dao.getSettings();
		} 
		catch (Exception e) 
		{
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Settings updateSettings(Settings settings)
	{
		try
		{
			SettingsDAO dao = new SettingsDAO();
			return dao.updateSettings(settings);
		}
		catch (Exception e) 
		{
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
			
		}
		
	}
}
