package com.enterprise.entity;

public class EventInvitationEntity {
	
	private int _eventInviteID;
	private int _inviterID;
	private int _inviteeID;
	private int _eventID;
	private boolean _isDeleted;
	private boolean _inviteAccepted;
	
	public EventInvitationEntity()
	{
		this._eventInviteID = -1;
		this._inviterID = -1;
		this._inviteeID = -1;
		this._eventID = -1;
		this._isDeleted = false;
		this._inviteAccepted = false;
	}

	public int getEventInviteID()
	{
		return this._eventInviteID;
	}
	public void setEventInviteID(int invite)
	{
		this._eventInviteID = invite;
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
	
	public int getEventID()
	{
		return this._eventID;
	}
	public void setEventID(int event)
	{
		this._eventID = event;
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
