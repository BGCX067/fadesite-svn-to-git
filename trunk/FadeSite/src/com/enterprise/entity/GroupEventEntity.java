package com.enterprise.entity;

public class GroupEventEntity {
	
	private int _groupEventID;
	private int _eventID;
	private int _groupID;
	private boolean _isDeleted;
	
	public GroupEventEntity()
	{
		this._groupEventID = -1;
		this._eventID = -1;
		this._groupEventID = -1;
		this._isDeleted = false;
		
	}

	public int getGroupEventID()
	{
		return this._groupEventID;
	}
	
	public void setGroupEventID(int groupevent)
	{
		this._groupEventID = groupevent;
	}
	
	public int getEventID()
	{
		return this._eventID;
	}
	
	public void setEventID(int eventid)
	{
		this._eventID = eventid;
	}
	public int getGroupID()
	{
		return this._groupID;
	}
	
	public void setGroupID(int groupid)
	{
		this._groupID = groupid;
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
	
