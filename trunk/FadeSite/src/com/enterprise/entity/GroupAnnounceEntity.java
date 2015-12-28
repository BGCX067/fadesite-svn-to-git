package com.enterprise.entity;

public class GroupAnnounceEntity {
	
	
	private int _groupAnnounceID;
	private int _announceID;
	private int _groupID;
	private boolean _isDeleted;
	
	public GroupAnnounceEntity()
	{
		this._groupAnnounceID = -1;
		this._announceID = -1;
		this._groupAnnounceID = -1;
		this._isDeleted = false;
		
	}

	public int getGroupAnnounceID()
	{
		return this._groupAnnounceID;
	}
	
	public void setGroupAnnounceID(int groupennounce)
	{
		this._groupAnnounceID = groupennounce;
	}
	
	public int getAnnounceID()
	{
		return this._announceID;
	}
	
	public void setAnnounceID(int announceid)
	{
		this._announceID = announceid;
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
