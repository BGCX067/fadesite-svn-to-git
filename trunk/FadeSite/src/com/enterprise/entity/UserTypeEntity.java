package com.enterprise.entity;

import java.util.*;

public class UserTypeEntity {

	private int _usertypeid;
	private String _name;
	
	public UserTypeEntity()
	{
		this._usertypeid = -1;
		this._name = "";
	}
	
	public int getUserTypeId()
	{
		return this._usertypeid;
	}
	
	public void setUserTypeId(int usertypeid)
	{
		this._usertypeid = usertypeid;
	}
	
	public String getName()
	{
		return this._name;
	}
	
	public void setName(String name)
	{
		this._name = name;
	}
}
