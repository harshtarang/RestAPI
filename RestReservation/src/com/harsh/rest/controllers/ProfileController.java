package com.harsh.rest.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import com.harsh.dao.ProfileDAO;
import com.harsh.model.Profile;


@Path("/profile")
public class ProfileController 
{
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Profile getProfile()
	{
		
		try 
		{
			ProfileDAO dao=new ProfileDAO();
			return dao.getProfile();
		} 
		catch (Exception e) 
		{
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Profile updateProfile(Profile profile)
	{
		ProfileDAO dao = new ProfileDAO();
		return dao.update(profile);
	}

}
