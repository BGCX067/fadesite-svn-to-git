package com.enterprise.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserBanEntity {
	
	private int _userBanID;
	private int _userID;
	private Date _startDate;
	private Date _expireDate;
	private String _description;
	private boolean _isDeleted;
	
	
	public UserBanEntity()
	{
		this._isDeleted = false;
		this._userBanID = -1;
		this._userID = -1;
		this._startDate = new Date();
		this._expireDate = new Date();
		this._description = "";
	}
	
	public int getUserBanID()
	{
		return this._userBanID;
	}
	public void setUserBanID(int banID)
	{
		this._userBanID = banID;
	}
	
	public int getUserID()
	{
		return this._userID;
	}
	public void setUserID(int userID)
	{
		this._userID = userID;
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
	public void setStartDate(Date startDate)
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
	public void setExpireDate(Date expireDate)
	{
		this._expireDate = expireDate;
	}
	
	public void setdescription(String description)
	{
		this._description = description;
	}
	
	public String getDescription()
	{
		if (this._description == null)
		{
			return "";
		}
		return this._description;	
	}
	public boolean getIsDeleted()
	{
		return this._isDeleted;
	}
	
	public void setIsDeleted(boolean isDel)
	{
		this._isDeleted = isDel;
	}
	
}
	

