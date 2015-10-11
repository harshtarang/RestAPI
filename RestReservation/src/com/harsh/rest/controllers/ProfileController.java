package com.harsh.rest.controllers;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import com.harsh.dao.ProfileDAO;
import com.harsh.exception.AppException;
import com.harsh.model.Profile;


@Path("/profile")
@Api(tags={"profile"})
public class ProfileController 
{
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Fetch the Restaurant profile details" ,
	notes="Fetches hotel name,email, phone number and address")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"), 
			@ApiResponse(code = 500, message = "Internal Server Error")
			
	})
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
	@ApiOperation(value = "Update the Restaurant profile details" ,
	notes="Updates profile")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"), 
			@ApiResponse(code = 500, message = "Internal Server Error")
			
	})
	public Profile updateProfile(Profile profile)
	{
		ProfileDAO dao = new ProfileDAO();
		try {
			return dao.update(profile);
		} catch (AppException e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}

}
