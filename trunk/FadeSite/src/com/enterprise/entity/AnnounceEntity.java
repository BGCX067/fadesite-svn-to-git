package com.enterprise.entity;

import java.text.SimpleDateFormat;
import java.util.*;

public class AnnounceEntity {

	private int _announceID;
	private Date _startDate;
	private Date _expireDate;
	private int _createdByUser;
	private int _updatedByUser;
	private Date _createDate;
	private Date _updateDate;
	private String _title;
	private String _description;
	private boolean _isApproved;
	private int _announceType;
	private boolean _isDeleted;

	public AnnounceEntity() 
	{
			this._announceID = -1;
			this._startDate = new Date();
			this._expireDate = new Date();
			this._createdByUser = -1;
			this._updatedByUser = -1;
			this._createDate = new Date();
			this._updateDate = new Date();
			this._title = "";
			this._description = "";
			this._isApproved = false;
			this._isDeleted = false;
			this._announceType = -1;
			
	}
	
	public int getAnnounceID()
	{
		return this._announceID;
	}
	
	public void setAnnounceID (int announceID)
	{
		this._announceID = announceID;
	}
	
	public Date getStartDate()
	{
		return this._startDate;
	}
	
	public String getStartDateString()
	{
		SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
		return df.format(this._startDate);
	}
	
	public void setStartDate (Date startDate)
	{
		this._startDate = startDate;
	}
	
	public Date getExpireDate()
	{
		return this._expireDate;
	}
	
	public String getExpireDateString()
	{
		SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
		return df.format(this._expireDate);
	}
	
	public void setExpireDate (Date expireDate)
	{
		this._expireDate = expireDate;
	}
	
	public int getCreatedByUser()
	{
		return this._createdByUser;
	}
	
	public void setCreatedByUser(int userID)
	{
		this._createdByUser = userID;
	}
	
	public int getUpdatedByUser()
	{
		return this._updatedByUser;
	}
	
	public void setUpdatedByUser (int userID)
	{
		this._updatedByUser = userID;
	}
	
	public Date getCreateDate() 
	{
		return this._createDate;
	}
	
	public void setCreateDate (Date createDate)
	{
		this._createDate = createDate;
	}
	
	public Date getUpdateDate() 
	{
		return this._updateDate;
	}
	
	public String getUpdateDateString()
	{
		SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
		return df.format(this._updateDate);
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
	
	public int getAnnounceType() 
	{
		return this._announceType;
	}
	
	public void setAnnounceType (int announceType)
	{
		this._announceType = announceType;
	}
	
	public boolean getIsDeleted()
	{
		return this._isDeleted;
	}
	
	public void setIsDeleted (boolean isDeleted)
	{
		this._isDeleted = isDeleted;
	}
}
	

