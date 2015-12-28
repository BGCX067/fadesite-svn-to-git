package com.enterprise.entity;

import java.util.*;

public class GroupEntity {
	
	private int _groupID;
	private String _name;
	private String _description;
	private boolean _IsDeleted;

	public GroupEntity()
	{
		this._groupID = -1;
		this._name = "";
		this._description = "";
		this._IsDeleted = false;
	}
	
	public int getGroupID()
	{
		return this._groupID;
	}
	
	public void setGroupID(int groupid)
	{
		this._groupID = groupid;
	}
	
	public String getName()
	{
		return this._name;
	}
	
	public void setName(String name)
	{
		this._name = name;
	}
	
	public String getDescription()
	{
		return this._description;
	}
	
	public void setDescription(String desc)
	{
		this._description = desc;
	}
	
	public boolean getIsDeleted()
	{
		return this._IsDeleted;
	}
	
	public void setIsDeleted(boolean isDel)
	{
		this._IsDeleted = isDel;
	}
	
	}
	

