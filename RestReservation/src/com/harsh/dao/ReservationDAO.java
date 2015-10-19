package com.harsh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.harsh.exception.AppException;
//import com.harsh.exception.AppException;
import com.harsh.model.Reservation;
import com.harsh.utils.DBUtils;

public class ReservationDAO {
	
	public List<Reservation> getAllReservations() throws AppException
	{
		List<Reservation> resList = new ArrayList<Reservation>();
		Connection con=DBUtils.getConnection();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try 
		{
			ps=con.prepareStatement("SELECT * FROM reservations");
			
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				Reservation res=new Reservation();
				
				res.setId(rs.getInt("ID"));
				res.setName(rs.getString("FULL_NAME"));
				res.setEmail(rs.getString("EMAIL"));
				res.setOccasion(rs.getString("OCCASION"));
				res.setPartySize(rs.getInt("PARTY_SIZE"));
				res.setPhone(rs.getString("PHONE"));
				res.setTime(rs.getTimestamp("DATE_TIME"));
				res.setWaitStatus(rs.getBoolean("WAIT_STATUS"));
				res.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				res.setTableId(rs.getInt("TABLE_ID"));
				resList.add(res);
				
				
			}
		}
		
		catch (SQLException e) 
		{
		
			e.printStackTrace();
			throw new AppException(e.getMessage(),e.getCause());
		}
		finally 
		{
			DBUtils.closeResource(ps, rs, con);
		}
		
		return resList;
	}
	
	
	
	public Reservation getReservation(int resId) throws AppException
	{
		Reservation res=null;
		
		Connection con=DBUtils.getConnection();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try 
		{	
			ps=con.prepareStatement("SELECT * FROM reservations WHERE ID =?");
			ps.setInt(1, resId);
			
			rs=ps.executeQuery();
			
			if(rs.next())
			{
				res=new Reservation();
				res.setId(rs.getInt("ID"));
				res.setName(rs.getString("FULL_NAME"));
				res.setEmail(rs.getString("EMAIL"));
				res.setOccasion(rs.getString("OCCASION"));
				res.setPartySize(rs.getInt("PARTY_SIZE"));
				res.setPhone(rs.getString("PHONE"));
				res.setTime(rs.getTimestamp("DATE_TIME"));
				res.setWaitStatus(rs.getBoolean("WAIT_STATUS"));
				res.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				res.setTableId(rs.getInt("TABLE_ID"));
			}
			
		}
		
		catch (SQLException e) 
		{
		
			e.printStackTrace();
			throw new AppException(e.getMessage(),e.getCause());
		}
		finally
		{
			DBUtils.closeResource(ps, rs, con);
		}
		return res;
		
		
	}
	
	public Reservation createReservation(Reservation res) throws AppException
	{
		Connection con=DBUtils.getConnection();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try 
		{
			
			
			ps=con.prepareStatement("INSERT INTO reservations (FULL_NAME,EMAIL, OCCASION, PARTY_SIZE, PHONE, DATE_TIME, WAIT_STATUS, CREATE_TIME, TABLE_ID) VALUES (?,?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, res.getName());
			ps.setString(2, res.getEmail());
			ps.setString(3, res.getOccasion());
			ps.setInt(4, res.getPartySize());
			ps.setString(5, res.getPhone());
			ps.setTimestamp(6, res.getTime());
			ps.setBoolean(7, res.getWaitStatus());
			ps.setTimestamp(8, res.getCreateTime());
			ps.setInt(9, res.getTableId());
			
			
			ps.executeUpdate();
			
			rs=ps.getGeneratedKeys();
			
			if(rs.next())
			{
				res.setId(rs.getInt(1));
			}
			
		}
		catch (SQLException e) {
		
			e.printStackTrace();
			throw new AppException(e.getMessage(),e.getCause());
		}
		finally
		{
			DBUtils.closeResource(ps, rs, con);
		}
		
		return res;
	}
	
	public Reservation createAutoReservation(Reservation res) throws AppException
	{
		Connection con=DBUtils.getConnection();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try 
		{
		
			
			ps=con.prepareStatement("INSERT INTO reservations (FULL_NAME,EMAIL, OCCASION, PARTY_SIZE, PHONE, DATE_TIME, WAIT_STATUS, CREATE_TIME, TABLE_ID) VALUES (?,?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, res.getName());
			ps.setString(2, res.getEmail());
			ps.setString(3, res.getOccasion());
			ps.setInt(4, res.getPartySize());
			ps.setString(5, res.getPhone());
			ps.setTimestamp(6, res.getTime());
			ps.setBoolean(7, res.getWaitStatus());
			ps.setTimestamp(8, res.getCreateTime());
			ps.setInt(9, res.getTableId());
			
			
			ps.executeUpdate();
			
			rs=ps.getGeneratedKeys();
			
			if(rs.next())
			{
				res.setId(rs.getInt(1));
			}
			
		}
		catch (SQLException e) {
		
			e.printStackTrace();
			throw new AppException(e.getMessage(),e.getCause());
		}
		finally
		{
			DBUtils.closeResource(ps, rs, con);
		}
		
		return res;
	}
	
	public Reservation updateReservation(int resId, Reservation res) throws AppException
	{
		Connection con=DBUtils.getConnection();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try
		{
			ps=con.prepareStatement("UPDATE reservations SET FULL_NAME =?,EMAIL=?, OCCASION=?, PARTY_SIZE=?,PHONE=?,DATE_TIME=?,WAIT_STATUS=?,CREATE_TIME=?,TABLE_ID =? WHERE ID=?");
			ps.setString(1, res.getName());
			ps.setString(2, res.getEmail());
			ps.setString(3, res.getOccasion());
			ps.setInt(4, res.getPartySize());
			ps.setString(5, res.getPhone());
			ps.setTimestamp(6, res.getTime());
			ps.setBoolean(7, res.getWaitStatus());
			ps.setTimestamp(8, res.getCreateTime());
			ps.setInt(9, res.getTableId());
			ps.setInt(10, resId);
			
			ps.executeUpdate();
			
		}
		catch (SQLException e) {
			
			e.printStackTrace();
			throw new AppException(e.getMessage(),e.getCause());
		}
		finally
		{
			DBUtils.closeResource(ps, rs, con);
		}
		
		
		return res;
	}
	
	
	public void deleteReservation(int resId) throws AppException
	{
		Connection con=DBUtils.getConnection();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try
		{
			ps=con.prepareStatement("DELETE FROM reservations WHERE ID =?");
			ps.setInt(1, resId);
			
			ps.executeUpdate();
		}
		catch (SQLException e) {
			
			e.printStackTrace();
			throw new AppException(e.getMessage(),e.getCause());
		}
		finally
		{
			DBUtils.closeResource(ps, rs, con);
		}
	}
	

}
