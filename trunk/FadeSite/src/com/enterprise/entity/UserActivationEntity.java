package com.enterprise.entity;

import java.util.*;

public class UserActivationEntity {

	private int _useractivationid;
	private String _token;
	private int _userid;
	private boolean _isDeleted;
	
	public UserActivationEntity()
	{
		this._useractivationid = -1;
		this._userid = -1;
		this._token = "";
		this._isDeleted = false;
	}
	
	public int getUserActivationId()
	{
		return this._useractivationid;
	}
	
	public void setUserActivationId(int useractivationid)
	{
		this._useractivationid = useractivationid;
	}
	
	public int getUserId()
	{
		return this._userid;
	}
	
	public void setUserId(int userid)
	{
		this._userid = userid;
	}
	
	public String getToken()
	{
		if (this._token == null)
		{
			return "";
		}
		return this._token;
	}
	
	public void setToken(String token)
	{
		this._token = token;
	}
	
	public boolean getIsDeleted()
	{
		return this._isDeleted;
	}
	
	public void setIsDeleted(boolean isdeleted)
	{
		this._isDeleted = isdeleted;
	}
}
