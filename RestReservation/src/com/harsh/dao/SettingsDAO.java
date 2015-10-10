package com.harsh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.harsh.exception.AppException;
import com.harsh.model.Settings;
import com.harsh.utils.DBUtils;

public class SettingsDAO {
	
	public Settings getSettings() throws AppException
	{
		Settings settings=new Settings();
		
		Connection con=DBUtils.getConnection();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try 
		{
			ps=con.prepareStatement("SELECT * FROM settings");
			
			rs=ps.executeQuery();
			
			if(rs.next())
			{
				settings.setAutoAssign(rs.getBoolean(2));
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
		
		return settings;
	}
	
	
	public Settings updateSettings(Settings settings) throws AppException
	{
		
		Connection con=DBUtils.getConnection();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		try
		{
			ps=con.prepareStatement("UPDATE settings SET AUTO_ASSIGN=? WHERE ID=1");
			ps.setBoolean(1, settings.isAutoAssign());
			
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
		
		
		return settings;
	}
	

}
