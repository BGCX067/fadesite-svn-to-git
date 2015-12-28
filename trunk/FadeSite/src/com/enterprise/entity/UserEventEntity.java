package com.enterprise.entity;

public class UserEventEntity {
	
	
	private int _userEventID;
	private int _eventID;
	private int _userID;
	private boolean _isDeleted;
	
	public UserEventEntity()
	{
		this._userEventID = -1;
		this._eventID = -1;
		this._userID = -1;
		this._isDeleted = false;
		
	}

	public int getUserEventID()
	{
		return this._userEventID;
	}
	
	public void setUserEventID(int userevent)
	{
		this._userEventID = userevent;
	}
	
	public int getEventID()
	{
		return this._eventID;
	}
	
	public void setEventID(int eventid)
	{
		this._eventID = eventid;
	}
	public int getUserID()
	{
		return this._userID;
	}
	
	public void setUserID(int userid)
	{
		this._userID = userid;
	}
	
	public void setIsDeleted(boolean isDeleted)
	{
		this._isDeleted = isDeleted;
	}
	public boolean getIsDeleted()
	{
		return this._isDeleted;
	}

}
