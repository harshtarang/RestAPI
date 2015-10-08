package com.harsh.rest.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
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
import com.harsh.exception.AppException;
import com.harsh.model.Reservation;


@Path("/reservations")
public class ReservationController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
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
//	
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Reservation createReservation(Reservation res)
//	{
//		ReservationDAO dao=new ReservationDAO();
//		//try 
//		//{
//			return dao.createReservation(res);
//		//} 
//		//catch (AppException e)
//		//{
//		//	throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
//		//}
//	}
//	
//	@PUT
//	@Path("{/id}")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public void updateReservation(@PathParam("id") int resId,Reservation res)
//	{
//		ReservationDAO dao=new ReservationDAO();
//		
//	}
//	
//	public void delete()
//	{
//		
//	}
//	

}
