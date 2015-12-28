package com.enterprise.entity;

public class UserGroupEntity {
	
	private int _userGroupID;
	private int _groupID;
	private int _userID;
	private boolean _isDeleted;
	
	public UserGroupEntity()
	{
		this._userGroupID = -1;
		this._groupID = -1;
		this._userID = -1;
		this._isDeleted = false;
	}
	
	public int getUserGroupID()
	{
		return this._userGroupID;
	}
	public void setUserGroupID(int usergroupID)
	{
		this._userGroupID = usergroupID;
	}
	
	public int getGroupID()
	{
		return this._groupID;
	}
	public void setGroupID(int groupid)
	{
		this._groupID = groupid;
	}
	
	public int getUserID()
	{
		return this._userID;
	}
	public void setUserID(int userid)
	{
		this._userID = userid;
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
