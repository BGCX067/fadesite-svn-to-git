package com.enterprise.entity;

public class DegreeEntity {
	
	private int _degreeID;
	private String _degreeName;



public DegreeEntity()
{
	this._degreeID = -1;
	this._degreeName = "";
}

public int getDegreeID()
{
	return this._degreeID;
}
public void setDegreeID(int degreeid)
{
	this._degreeID = degreeid;
}

public String getDegreeName()
{
	if (this._degreeName == null)
	{
		return "";
	}
	return this._degreeName;
}
public void setDegreeName(String degree)
{
	this._degreeName = degree;
}
}
