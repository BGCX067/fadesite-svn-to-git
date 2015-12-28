package com.enterprise.entity;

public class GroupInvitationEntity {
	
	private int _groupInviteID;
	private int _inviterID;
	private int _inviteeID;
	private int _groupID;
	private boolean _isDeleted;
	private boolean _inviteAccepted;
	
	public GroupInvitationEntity()
	{
		this._groupInviteID = -1;
		this._inviterID = -1;
		this._inviteeID = -1;
		this._groupID = -1;
		this._isDeleted = false;
		this._inviteAccepted = false;
	}

	public int getGroupInviteID()
	{
		return this._groupInviteID;
	}
	public void setGroupInviteID(int invite)
	{
		this._groupInviteID = invite;
	}
	
	public int getInviterID()
	{
		return this._inviterID;
	}
	public void setInviterID(int inviter)
	{
		this._inviterID = inviter;
	}
	
	public int getInviteeID()
	{
		return this._inviteeID;
	}
	public void setInviteeID(int invitee)
	{
		this._inviteeID = invitee;
	}
	
	public int getGroupID()
	{
		return this._groupID;
	}
	public void setGroupID(int event)
	{
		this._groupID = event;
	}
	
	public boolean getIsDeleted()
	{
		return this._isDeleted;
	}
	
	public void setIsDeleted(boolean isDel)
	{
		this._isDeleted = isDel;
	}
	
	public boolean getInviteAccepted()
	{
		return this._inviteAccepted;
	}
	
	public void setInviteAccepted(boolean accepted)
	{
		this._inviteAccepted = accepted;
	}
	
}
