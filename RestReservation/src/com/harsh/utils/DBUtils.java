package com.harsh.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils 
{
	
	private static final String url="jdbc:mysql://localhost:3306/res_db";
	private static final String user="root";
	private static final String password="root";
	
	static
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBC driver loaded");
			
			
			
		}
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection()
	{
		Connection conn=null;
		
		try 
		{
			conn=DriverManager.getConnection(url, user, password);
			System.out.println("Connection to DB established");
		}
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
	public static void main(String[] args) 
	{
		
		DBUtils.getConnection();
	}


	public static void closeResource(PreparedStatement ps, ResultSet rs,
			Connection con) 
	{
		try 
		{
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (con != null) {
				con.close();
			}
		}
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
	}

}
