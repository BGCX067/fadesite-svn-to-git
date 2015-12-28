package com.enterprise.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.enterprise.entity.AnnounceUpdateEntity;
import com.enterprise.entity.EventUpdateEntity;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class EventUpdateDAO {
	
	public static EventUpdateEntity getEventUpdate (int eventupdateID)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM eventupdate where eventupdateid = ? and IsDeleted = 0");
			query.setInt(1, eventupdateID);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				return CreateEventUpdateEntity(set);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static ArrayList<EventUpdateEntity> getAllEventUpdates()
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM EVENTUPDATE where IsDeleted = 0");
			ResultSet set = query.executeQuery();
			
			ArrayList<EventUpdateEntity> list = new ArrayList<EventUpdateEntity>();
			
			while (set.next())
			{
				EventUpdateEntity update = CreateEventUpdateEntity(set);
				if (EventDAO.getEvent(update.getEventID()).getIsApproved())
				{
					list.add(update);
				}
			}
			
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static EventUpdateEntity getUpdateByEvent (int EventID)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM Eventupdate where Eventid = ? and IsDeleted = 0");
			query.setInt(1, EventID);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				return CreateEventUpdateEntity(set);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static boolean CreateEventUpdate(EventUpdateEntity Event)
	{
		boolean isSuccess = false;

		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"INSERT INTO eventupdate( StartDate, ExpireDate, UpdatedByUser, "
				  + " UpdateDate, Title, Description, IsApproved, Location, IsDeleted, EventID) "
				  + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			query.setDate(1, new java.sql.Date(Event.getStartDate().getTime()));
			query.setDate(2, new java.sql.Date(Event.getExpireDate().getTime()));
			query.setInt(3, Event.getUpdatedByUser());
			query.setDate(4, new java.sql.Date(Event.getUpdateDate().getTime()));
			query.setString(5, Event.getTitle());
			query.setString(6, Event.getDescription());
			query.setBoolean(7, Event.getIsApproved());
			query.setString(8, Event.getLocation());
			query.setBoolean(9, Event.getIsDeleted());
			query.setInt(10, Event.getEventID());
			
			isSuccess = query.executeUpdate() > 0;
			query.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isSuccess;
	}
	
	public static boolean updateEventUpdate(EventUpdateEntity Event)
	{
		boolean isSuccess = false;
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"UPDATE eventupdate SET StartDate = ? , ExpireDate = ? , UpdatedByUser = ?  "
				  + ", UpdateDate = ? , Title = ? , Description = ? , IsApproved = ? , Location = ? , IsDeleted = ? , EventID = ? "
				  + " WHERE EventUpdateID = ?");
			query.setDate(1, new java.sql.Date(Event.getStartDate().getTime()));
			query.setDate(2, new java.sql.Date(Event.getExpireDate().getTime()));
			query.setInt(3, Event.getUpdatedByUser());
			query.setDate(4, new java.sql.Date(Event.getUpdateDate().getTime()));
			query.setString(5, Event.getTitle());
			query.setString(6, Event.getDescription());
			query.setBoolean(7, Event.getIsApproved());
			query.setString(8, Event.getLocation());
			query.setBoolean(9, Event.getIsDeleted());
			query.setInt(10, Event.getEventID());
			query.setInt(11, Event.getEventUpdateID());
			
			isSuccess = query.executeUpdate() > 0;
			query.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isSuccess;
	}
	private static EventUpdateEntity CreateEventUpdateEntity(ResultSet rs)
	{
		try {
			EventUpdateEntity Event = new EventUpdateEntity();
			
			Event.setEventUpdateID(rs.getInt("EventUpdateID"));
			Event.setEventID(rs.getInt("EventID"));
			Event.setStartDate(rs.getDate("StartDate"));
			Event.setExpireDate(rs.getDate("ExpireDate"));
			Event.setUpdatedByUser(rs.getInt("UpdatedByUser"));
			Event.setUpdateDate(rs.getDate("UpdateDate"));
			Event.setTitle(rs.getString("Title"));
			Event.setDescription(rs.getString("Description"));
			Event.setIsApproved(rs.getBoolean("IsApproved"));
			Event.setLocation(rs.getString("Location"));
			Event.setIsDeleted(rs.getBoolean("IsDeleted"));
						
			return Event;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}
