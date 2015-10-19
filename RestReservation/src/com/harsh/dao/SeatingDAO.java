package com.harsh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.harsh.exception.AppException;
import com.harsh.model.Seating;
import com.harsh.utils.DBUtils;

public class SeatingDAO {
	
	public List<Seating> getSeats() throws AppException
	{
		List<Seating> tables=new ArrayList<Seating>();
		
		Connection con=DBUtils.getConnection();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try 
		{
			ps=con.prepareStatement("SELECT * FROM Seating");
			
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				
				Seating seating=new Seating();
				seating.setId(rs.getInt(1));
				seating.setWaitStatus(rs.getBoolean(2));
				seating.setSinceTime(rs.getTimestamp(3));
				seating.setResId(rs.getInt(4));
				tables.add(seating);
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
		
		return tables;
	}
	
	public Seating getTableById(int id) throws AppException
	{
		Seating seating=new Seating();
		
		Connection con=DBUtils.getConnection();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try 
		{
			ps=con.prepareStatement("SELECT * FROM Seating WHERE ID=?");
			ps.setInt(1, id);
			
			rs=ps.executeQuery();
			
			if(rs.next())
			{
				
				
				seating.setId(rs.getInt(1));
				seating.setWaitStatus(rs.getBoolean(2));
				seating.setSinceTime(rs.getTimestamp(3));
				seating.setResId(rs.getInt(4));
				
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
		
		return seating;
	}
	
	
	public int getEmptyTable() throws AppException
	{
		
		int tableId=0;
		Connection con=DBUtils.getConnection();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try 
		{
			ps=con.prepareStatement("SELECT * FROM Seating WHERE WAIT_STATUS=FALSE LIMIT 1");
			
			rs=ps.executeQuery();
			if(rs.next())
			{
				tableId=rs.getInt("ID");
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
		return tableId;
	}
	
	
	public Seating update(int id,Seating seating) throws AppException
	{
		Connection con=DBUtils.getConnection();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try
		{
			ps=con.prepareStatement("UPDATE Seating SET WAIT_STATUS =?,CREATE_TIME=?, RES_ID=? WHERE ID=?");
			ps.setBoolean(1, seating.isWaitStatus());
			ps.setTimestamp(2, seating.getSinceTime());
			ps.setInt(3, seating.getResId());
			ps.setInt(4, seating.getId());
			
			
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
		
	
		
		
		return seating;
	}

	public Seating getTableByReserveID(int resId) throws AppException {
		Seating seating=null;
		
		Connection con=DBUtils.getConnection();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try 
		{
			ps=con.prepareStatement("SELECT * FROM Seating WHERE RES_ID=?");
			ps.setInt(1, resId);
			
			rs=ps.executeQuery();
			
			if(rs.next())
			{
				
				seating=new Seating();
				seating.setId(rs.getInt(1));
				seating.setWaitStatus(rs.getBoolean(2));
				seating.setSinceTime(rs.getTimestamp(3));
				seating.setResId(rs.getInt(4));
				
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
		
		return seating;
	}

	public void freeTable(int id, Seating seat) throws AppException {
		seat.setResId(0);
		seat.setSinceTime(null);
		seat.setWaitStatus(false);
		update(id, seat);
		
	}
	
	
	
	

}
