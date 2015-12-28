package com.enterprise.entity;

import java.util.*;

public class AnnounceTypeEntity {
	
	private int _announceTypeID;
	private String _name;
	
	public AnnounceTypeEntity()
	{
		this._announceTypeID = -1;
		this._name = "";
	}
	
	public int getAnnounceTypeID()
	{
		return this._announceTypeID;
	}
	
	public void setAnnounceTypeID(int AnnounceTypeID)
	{
		this._announceTypeID = AnnounceTypeID;
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


