package com.harsh.rest.controllers;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import com.harsh.dao.SeatingDAO;
import com.harsh.exception.AppException;
import com.harsh.model.Seating;

@Path("/seating")
@Api(tags={"seating"})
public class SeatingController {

	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Fetch the Seating details" ,
	notes="Fetches table id, wait status, confirmed since date and reservation id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"), 
			@ApiResponse(code = 500, message = "Internal Server Error")
			
	})
	public List<Seating> getSeats()
	{
		
		try 
		{
			SeatingDAO dao=new SeatingDAO();
			return dao.getSeats();
		} 
		catch (Exception e) 
		{
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Fetch a table details" ,
	notes="Fetch table details based on id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"), 
			@ApiResponse(code =404, message = "Not found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
			
	})
	public Seating getTableById(@PathParam("id") int id)
	{
		SeatingDAO dao = new SeatingDAO();
		try {
			return dao.getTableById(id);
		} catch (AppException e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update the Seating details" ,
	notes="Updates table details based on id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"), 
			@ApiResponse(code = 500, message = "Internal Server Error")
			
	})
	public Seating update(@PathParam("id") int id,Seating seating)
	{
		SeatingDAO dao = new SeatingDAO();
		try {
			return dao.update(id,seating);
		} catch (AppException e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
}
