package com.enterprise.entity;

public class MessageEntity {

	private int _messageID;
	private int _sendeeID;
	private int _senderID;
	private String _contents;
	private String _title;
	private boolean _isRead;
	private boolean _isDeleted;
	private boolean _deletedBySender;
	private boolean _deletedBySendee;
	
	public MessageEntity()
	{
		this._messageID = -1;
		this._sendeeID = -1;
		this._senderID = -1;
		this._contents = "";
		this._isRead = false;
		this._isDeleted = false;
		this._deletedBySendee = false;
		this._deletedBySender = false;
		
	}
	
	public int getMessageID()
	{
		return this._messageID;
	}
	
	public void setMessageID (int MessageID)
	{
		this._messageID = MessageID;
	}
	
	public int getSendeeID()
	{
		return this._sendeeID;
	}
	
	public void setSendeeID(int sendeeID)
	{
		this._sendeeID = sendeeID;
	}
	
	public int getSenderID()
	{
		return this._senderID;
	}
	
	public void setSenderID(int senderID)
	{
		this._senderID = senderID;
	}
	
	public String getContents()
	{
		if (this._contents == null)
		{
			return "";
		}
		return this._contents;
	}
	
	public void setContents(String contents)
	{
		this._contents = contents;
	}
	
	public String getTitle()
	{
		if (this._title == null)
		{
			return "";
		}
		return this._title;
	}
	
	public void setTitle(String title)
	{
		this._title = title;
	}
	
	public boolean getIsRead()
	{
		return this._isRead;
	}
	
	public void setIsRead (boolean isRead)
	{
		this._isRead = isRead;
	}
	public boolean getIsDeleted()
	{
		return this._isDeleted;
	}
	
	public void setIsDeleted(boolean isdeleted)
	{
		this._isDeleted = isdeleted;
	}


public boolean getDeletedBySender()
{
	return this._deletedBySender;
}

public void setDeletedBySender(boolean isdeleted)
{
	this._deletedBySender = isdeleted;
}

public boolean getDeletedBySendee()
{
	return this._deletedBySendee;
}

public void setDeletedBySendee(boolean isdeleted)
{
	this._deletedBySendee = isdeleted;
}


}
