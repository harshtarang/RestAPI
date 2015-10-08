package com.harsh.exception;

public class AppException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4228727393959979963L;

	public AppException(String message)
	{
		super(message);
	}
	
	public AppException(String message, Throwable cause)
	{
		super(message,cause);
	}
}
