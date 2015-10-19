package com.harsh.model;

import java.sql.Timestamp;

public class Seating {

	
	private int id;
	private boolean waitStatus;
	private Timestamp sinceTime;
	private int resId;
	
	public Seating()
	{
		
	}
	
	public Seating(int id, boolean waitStatus, Timestamp sinceTime, int resId)
	{
		setId(id);
		setWaitStatus(waitStatus);
		setSinceTime(sinceTime);
		setResId(resId);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isWaitStatus() {
		return waitStatus;
	}
	public void setWaitStatus(boolean waitStatus) {
		this.waitStatus = waitStatus;
	}
	public Timestamp getSinceTime() {
		return sinceTime;
	}
	public void setSinceTime(Timestamp sinceTime) {
		this.sinceTime = sinceTime;
	}
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	
	
}
