package com.enterprise.entity;

import java.text.SimpleDateFormat;
import java.util.*;

public class UserTemporaryPasswordEntity {

	private int _usertemporarypasswordid;
	private String _password;
	private int _userid;
	private boolean _isDeleted;
	private Date _createdDate;
	
	public UserTemporaryPasswordEntity()
	{
		this._usertemporarypasswordid = -1;
		this._userid = -1;
		this._password = "";
		this._isDeleted = false;
		this._createdDate = new Date();
	}
	
	public int getUserTemporaryPasswordId()
	{
		return this._usertemporarypasswordid;
	}
	
	public void setUserTemporaryPasswordId(int usertemporarypasswordid)
	{
		this._usertemporarypasswordid = usertemporarypasswordid;
	}
	
	public int getUserId()
	{
		return this._userid;
	}
	
	public void setUserId(int userid)
	{
		this._userid = userid;
	}
	
	public String getPassword()
	{
		if (this._password == null)
		{
			return "";
		}
		return this._password;
	}
	
	public void setPassword(String password)
	{
		this._password = password;
	}
	
	public boolean getIsDeleted()
	{
		return this._isDeleted;
	}
	
	public void setIsDeleted(boolean isdeleted)
	{
		this._isDeleted = isdeleted;
	}
	
	public Date getCreatedDate()
	{
		return this._createdDate;
	}
	
	public String getCreatedDateString()
	{
		SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
		return df.format(this._createdDate);		
	}
	
	public void setCreatedDate(Date createdDate)
	{
		this._createdDate = createdDate;
	}
}
