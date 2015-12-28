package com.enterprise.entity;

import java.text.SimpleDateFormat;
import java.util.*;

public class UserDegreeEntity {
	
	private int _userDegreeID;
	private int _userID;
	private int _degreeID;
	private Date _graduatingDate;
	private Boolean _isDeleted;


public UserDegreeEntity()
{
	this._userDegreeID = -1;
	this._userID = -1;
	this._degreeID = -1;
	this._isDeleted = false;
	this._graduatingDate = new Date();
}

public int getUserID()
{
	return this._userID;
}

public void setUserID(int userid)
{
	this._userID = userid;
}

public int getUserDegreeID()
{
	return this._userDegreeID;
}

public void setUserDegreeID(int degreeid)
{
	this._userDegreeID = degreeid;
}

public int getDegreeID()
{
	return this._degreeID;
}

public void setDegreeID(int degreeid)
{
	this._degreeID = degreeid;
}

public Date getGraduatingDate()
{
	return this._graduatingDate;
}

public String getGraduatingDateString()
{
	SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
	return df.format(this._graduatingDate);
}

public void setGraduatingDate(Date date)
{
	this._graduatingDate = date;
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

