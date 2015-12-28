package com.enterprise.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EventUpdateEntity {

	private int _eventUpdateID;
	private int _eventID;
	private Date _startDate;
	private Date _expireDate;
	private int _updatedByUser;
	private Date _updateDate;
	private String _title;
	private String _description;
	private String _location;
	private boolean _isApproved;
	private boolean _isDeleted;
	
	public EventUpdateEntity()
	{
		this._eventID = -1;
		this._startDate = new Date();
		this._expireDate = new Date();
		this._updatedByUser = -1;
		this._updateDate = new Date();
		this._title = "";
		this._description = "";
		this._isApproved = false;
		this._isDeleted = false;
		this._location = "";
		
	}
	
	public int getEventUpdateID()
	{
		return this._eventUpdateID;
	}
	
	public void setEventUpdateID(int updateID)
	{
		this._eventUpdateID = updateID;
	}
	
	public int getEventID()
	{
		return this._eventID;
	}
	
	public void setEventID (int announceID)
	{
		this._eventID = announceID;
	}
	
	public String getStartDateString()
	{
		SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
		return df.format(this._startDate);
	}
	
	public Date getStartDate()
	{
		return this._startDate;
	}
	
	public void setStartDate (Date startDate)
	{
		this._startDate = startDate;
	}
	
	public String getExpireDateString()
	{
		SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
		return df.format(this._expireDate);
	}
	
	public Date getExpireDate()
	{
		return this._expireDate;
	}
	
	public void setExpireDate (Date expireDate)
	{
		this._expireDate = expireDate;
	}
	

	
	public int getUpdatedByUser()
	{
		return this._updatedByUser;
	}
	
	public void setUpdatedByUser (int userID)
	{
		this._updatedByUser = userID;
	}
	

	public String getUpdateDateString() 
	{
		SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
		return df.format(this._updateDate);
	}
	
	public Date getUpdateDate()
	{
		return this._updateDate;
	}
	
	public void setUpdateDate (Date updateDate)
	{
		this._updateDate = updateDate;
	}

	public String getTitle() 
	{
		if (this._title == null)
		{
			return "";
		}
		return this._title;
	}
	
	public void setTitle (String title)
	{
		this._title = title;
	}
	
	public String getDescription() 
	{
		if (this._description == null)
		{
			return "";
		}
		return this._description;
	}
	
	public void setDescription (String description)
	{
		this._description = description;
	}
	
	public Boolean getIsApproved() 
	{
		return this._isApproved;
	}
	
	public void setIsApproved (boolean isApproved)
	{
		this._isApproved = isApproved;
	}
	
	public boolean getIsDeleted()
	{
		return this._isDeleted;
	}
	
	public void setIsDeleted (boolean isDeleted)
	{
		this._isDeleted = isDeleted;
	}
	
	public String getLocation()
	{
		return this._location;
	}
	
	public void setLocation (String location)
	{
		this._location = location;
	}
}
