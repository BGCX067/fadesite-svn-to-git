package com.enterprise.entity;

public class EventAnnounceEntity {
	
	
	private int _eventAnnounceID;
	private int _announceID;
	private int _eventID;
	private boolean _isDeleted;
	
	public EventAnnounceEntity()
	{
		this._eventAnnounceID = -1;
		this._announceID = -1;
		this._eventAnnounceID = -1;
		this._isDeleted = false;
		
	}

	public int getEventAnnounceID()
	{
		return this._eventAnnounceID;
	}
	
	public void setEventAnnounceID(int eventennounce)
	{
		this._eventAnnounceID = eventennounce;
	}
	
	public int getAnnounceID()
	{
		return this._announceID;
	}
	
	public void setAnnounceID(int announceid)
	{
		this._announceID = announceid;
	}
	public int getEventID()
	{
		return this._eventID;
	}
	
	public void setEventID(int eventid)
	{
		this._eventID = eventid;
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
