package com.harsh.rest.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import com.harsh.dao.ReservationDAO;
import com.harsh.dao.SeatingDAO;
import com.harsh.dao.SettingsDAO;
import com.harsh.exception.AppException;
import com.harsh.model.Reservation;
import com.harsh.model.Seating;
import com.harsh.model.Settings;


@Path("/reservations")
@Api(tags={"reservations"})
public class ReservationController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Fetch all reservations" ,
	notes="Fetches all reservations from the DB")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"), 
			@ApiResponse(code = 500, message = "Internal Server Error")
			
	})
	public List<Reservation> getAllReservations()
	{
		
		try 
		{
			ReservationDAO dao=new ReservationDAO();
			return dao.getAllReservations();
		} 
		catch (Exception e) 
		{
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Fetch a reservation" ,
	notes="Fetches a reservation from the DB based on the ID")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"), 
			@ApiResponse(code = 404, message = "Reservation not found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
			
	})
	public Reservation getReservation(@PathParam("id") int resId)
	{
		
		
		
		try 
		{
			ReservationDAO dao=new ReservationDAO();
			Reservation res= dao.getReservation(resId);
			if(res==null)
				throw new WebApplicationException(Status.NOT_FOUND);
			else
			return res;
		}
		catch (AppException e) 
		{
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create a new reservation" ,
	notes="Create a new reservation with a JSON object")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"), 
			@ApiResponse(code = 500, message = "Internal Server Error")
			
	})
	public Reservation createReservation(Reservation res)
	{
		
		try 
		{
			ReservationDAO dao=new ReservationDAO();
			
			SettingsDAO setDAO=new SettingsDAO();
			Settings settings = setDAO.getSettings();
			
			
			if(!settings.isAutoAssign())
			{
				res.setWaitStatus(true);
				res.setTableId(0);
				res= dao.createReservation(res);
			}
			else
			{
				SeatingDAO seatingDAO=new SeatingDAO();
				int emptyTableId=seatingDAO.getEmptyTable();
				
				if(emptyTableId!=0)
				{
					res.setTableId(emptyTableId);
					res.setWaitStatus(false);
					res= dao.createReservation(res);
					Seating seat = new Seating(emptyTableId,true,res.getTime(),res.getId());
					seatingDAO.update(seat.getId(), seat);
				}
				
				
				
				
			}
			
			
			return res;
		} 
		catch (AppException e)
		{
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update a reservation" ,
	notes="Updates a reservation based on ID")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"), 
			@ApiResponse(code = 500, message = "Internal Server Error")
			
	})
	public Reservation updateReservation(@PathParam("id") int resId,Reservation res)
	{
		ReservationDAO dao=new ReservationDAO();
		try 
		{
			return dao.updateReservation(resId, res);
		} 
		catch (AppException e)
		{
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete a reservation" ,
	notes="Deletes a reservation based on ID")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"), 
			@ApiResponse(code = 500, message = "Internal Server Error")
			
	})
	public void delete(@PathParam("id") int resId)
	{
		try 
		{
			ReservationDAO dao=new ReservationDAO();
			 dao.deleteReservation(resId);
			 SeatingDAO seatDAO = new SeatingDAO();
			 Seating seat= seatDAO.getTableByReserveID(resId);
			 if(seat!=null)
			 seatDAO.freeTable(seat.getId(),seat);
		} 
		catch (Exception e) 
		{
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
			
		}
	}
	
}

