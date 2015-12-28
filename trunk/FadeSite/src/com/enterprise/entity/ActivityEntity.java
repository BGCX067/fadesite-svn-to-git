package com.enterprise.entity;

import java.text.SimpleDateFormat;
import java.util.*;

public class ActivityEntity{
	
		private int _activityID;
		private int _userID;
		private Date _startDate;
		private Date _endDate;
		private String _name;
		private String _location;
		private String _organisation;
		private String _description;
		private String _position;
		private Boolean _isDeleted;
		private String _project;
		
		public ActivityEntity()
		{
			this._activityID = -1;
			this._userID = -1;
			this._startDate = new Date();
			this._endDate = new Date();
			this._name = "";
			this._location = "";
			this._organisation = "";
			this._description = "";
			this._position = "";
			this._isDeleted = false;
			this._project = "";
		}
		
		public int getActivityID()
		{
			return this._activityID;
		}
		
		public void setActivityID(int activityid)
		{
			this._activityID = activityid;
		}
		
		public int getUserID()
		{
			return this._userID;
		}
		
		public void setUserID(int userid)
		{
			this._userID = userid;
		}
		
		public Date getStartDate()
		{
			return this._startDate;
		}
		
		public String getStartDateString()
		{
			SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
			return df.format(this._startDate);
		}
		
		public void setStartDate(Date startdate)
		{
			this._startDate = startdate;
		}
		
		
		public Date getEndDate()
		{
			return this._endDate;
		}
		
		public String getEndDateString()
		{
			SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
			return df.format(this._endDate);
		}
		
		public void setEndDate(Date enddate)
		{
			this._endDate = enddate;
		}
		
		public String getName()
		{
			return this._name;
		}
		
		public void setName (String name)
		{
			this._name = name;
		}
		
		public String getLocation()
		{
			return this._location;
		}
		
		public void setLocation (String location)
		{
			this._location = location;
		}
		
		public String getOrganisation()
		{
			return this._organisation;
		}
		
		public void setOrganisation (String organisation)
		{
			this._organisation = organisation;
		}
		
		public String getDescription()
		{
			return this._description;
		}
		
		public void setDescription (String description)
		{
			this._description = description;
		}
		
		public String getPosition()
		{
			return this._position;
		}
		
		public void setPosition(String position)
		{
			this._position = position;
		}
		
		public boolean getIsDeleted()
		{
			return this._isDeleted;
		}
		
		public void setIsDeleted (boolean isdeleted)
		{
			this._isDeleted = isdeleted;
		}
		
		public String getProject()
		{
			return this._project;
		}
		
		public void setProject(String proj)
		{
			this._project = proj;
		}
		
}
