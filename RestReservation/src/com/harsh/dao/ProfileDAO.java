package com.harsh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.harsh.exception.AppException;
import com.harsh.model.Profile;
import com.harsh.utils.DBUtils;

public class ProfileDAO {
	
	public Profile getProfile() throws AppException
	{
		Profile profile=new Profile();
		
		Connection con=DBUtils.getConnection();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try 
		{
			ps=con.prepareStatement("SELECT * FROM restProfile");
			
			rs=ps.executeQuery();
			
			if(rs.next())
			{
				profile.setName(rs.getString(2));
				profile.setEmail(rs.getString(3));
				profile.setPhone(rs.getString(4));
				profile.setAddress(rs.getString(5));
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
		
		return profile;
	}
	
	
	public Profile update(Profile profile) throws AppException
	{
		deleteProfile();
		createProfile(profile);
		return profile;
	}
	
	private void createProfile(Profile profile) throws AppException
	{
		Connection con=DBUtils.getConnection();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try 
		{
			ps=con.prepareStatement("INSERT INTO restProfile (HOTEL_NAME,EMAIL,PHONE,ADDRESS) VALUES (?,?,?,?)");
			ps.setString(1, profile.getName());
			ps.setString(2, profile.getEmail());
			ps.setString(3, profile.getPhone());
			ps.setString(4, profile.getAddress());
			
			ps.executeUpdate();
			
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
		
		
	}
	
	private void deleteProfile() throws AppException
	{
		Connection con=DBUtils.getConnection();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try 
		{
			ps=con.prepareStatement("DELETE FROM restProfile WHERE ID=1");
			ps.executeUpdate();
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
		
		
	}
	
	

}
