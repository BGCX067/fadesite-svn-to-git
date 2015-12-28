package com.enterprise.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserEntity {

	private int _userid;
	private String _username;
	private String _firstName;
	private String _surname;
	private Date _dob;
	private String _emailAddress;
	private boolean _isDeleted;
	private boolean _isActivated;
	private String _gender;
	private String _location;
	private long _referenceid;
	private String _password;
	private String _address;
	private int _usertypeid;
	
	public UserEntity()
	{
		this._userid = -1;
		this._username = "";
		this._firstName = "";
		this._surname = "";
		this._dob = new Date();
		this._emailAddress = "";
		this._isDeleted = false;
		this._isActivated = false;
		this._gender = "";
		this._location = "";
		this._referenceid = -1;
		this._password = "";
	}
	
	public int getUserId()
	{
		return this._userid;
	}
	
	public void setUserId(int userid)
	{
		this._userid = userid;
	}
	
	public String getUsername()
	{
		if (this._username == null)
		{
			return "";
		}
		return this._username;	
	}
	
	public void setUsername(String username)
	{
		this._username = username;
	}
	
	public String getFirstname()
	{
		if (this._firstName == null)
		{
			return "";
		}
		return this._firstName;	
	}
	
	public void setFirstname(String firstname)
	{
		this._firstName = firstname;
	}
	
	public String getSurname()
	{
		if (this._surname == null)
		{
			return "";
		}
		return this._surname;	
	}
	
	public void setSurname(String surname)
	{
		this._surname = surname;
	}
	
	public Date getDOB()
	{
		return this._dob;
	}
	
	public String getDOBString()
	{
		SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
		return df.format(this._dob);
	}
	
	public void setDOB(Date dob)
	{
		this._dob = dob;
	}
	
	public String getEmailAddress()
	{
		if (this._emailAddress == null)
		{
			return "";
		}
		return this._emailAddress;	
	}
	
	public void setEmailAddress(String emailAddress)
	{
		this._emailAddress = emailAddress;
	}
	
	public boolean getIsDeleted()
	{
		return this._isDeleted;	
	}
	
	public void setIsDeleted(boolean isDeleted)
	{
		this._isDeleted = isDeleted;
	}
	
	public boolean getIsActivated()
	{
		return this._isActivated;	
	}
	
	public void setIsActivated(boolean isActivated)
	{
		this._isActivated = isActivated;
	}
	
	public String getGender()
	{
		if (this._gender == null)
		{
			return "";
		}
		return this._gender;	
	}
	
	public void setGender(String gender)
	{
		this._gender = gender;
	}
	
	public String getLocation()
	{
		if (this._location == null)
		{
			return "";
		}
		return this._location;	
	}
	
	public void setLocation(String location)
	{
		this._location = location;
	}
	
	public long getReferenceId()
	{
		return this._referenceid;
	}
	
	public void setAddress(String address)
	{
		this._address = address;
	}
	
	public String getAddress()
	{
		if (this._address == null)
		{
			return "";
		}
		return this._address;
	}
	
	public void setReferenceId(long referenceid)
	{
		this._referenceid = referenceid;
	}
	
	public String getPassword()
	{
		if (this._password == null)
		{
			return "";
		}
		return this._password;	
	}
	
	public void setPassword(String password)
	{
		this._password = password;
	}
	
	public void setUserTypeId(int usertypeid)
	{
		this._usertypeid = usertypeid;
	}
	
	public int getUserTypeId()
	{
		return this._usertypeid;
	}
}
