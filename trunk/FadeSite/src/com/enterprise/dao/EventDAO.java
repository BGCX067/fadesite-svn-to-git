package com.enterprise.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.enterprise.entity.EventEntity;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class EventDAO {
	
	public static EventEntity getEvent (int eventID)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM event where EventID = ? and IsDeleted = 0");
			query.setInt(1, eventID);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				return CreateEventEntity(set);
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
	
	public static List<EventEntity> listEvent()
	{
		ArrayList<EventEntity> eventList = new ArrayList<EventEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM event where IsDeleted = 0");
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				eventList.add(CreateEventEntity(set));
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
		
		return eventList;
	}	
	
	public static List<EventEntity> listPublicEvent()
	{
		ArrayList<EventEntity> eventList = new ArrayList<EventEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"SELECT * FROM event where IsDeleted = 0 and isapproved = 1 and ExpireDate >= ? AND " +
					"EventID NOT IN (Select EventID from groupevent) order by startdate asc, expiredate asc");
			query.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				eventList.add(CreateEventEntity(set));
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
		
		return eventList;
	}
	
	public static List<EventEntity> listPublicEventMembers()
	{
		ArrayList<EventEntity> eventList = new ArrayList<EventEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"SELECT * FROM event where IsDeleted = 0 and isapproved = 1 AND " +
					"EventID NOT IN (Select EventID from groupevent) order by startdate asc, expiredate asc");
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				eventList.add(CreateEventEntity(set));
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
		
		return eventList;
	}
	
	public static List<EventEntity> listPublicPastEvents()
	{
		ArrayList<EventEntity> eventList = new ArrayList<EventEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"SELECT * FROM event where IsDeleted = 0 and isapproved = 1 and ExpireDate < ? AND " +
					"EventID NOT IN (Select EventID from groupevent) order by startdate asc, expiredate asc");
			query.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				eventList.add(CreateEventEntity(set));
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
		
		return eventList;
	}
	
	public static List<EventEntity> listPublicEventAdmin()
	{
		ArrayList<EventEntity> eventList = new ArrayList<EventEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"SELECT * FROM event where IsDeleted = 0 and isapproved = 0 and " +
					"EventID NOT IN (Select EventID from groupevent) order by startdate asc");
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				eventList.add(CreateEventEntity(set));
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
		
		return eventList;
	}
	
	public static List<EventEntity> listPublicEventByUserId(int userId)
	{
		ArrayList<EventEntity> eventList = new ArrayList<EventEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"SELECT * FROM event where IsDeleted = 0 and createdbyuser = ? and " +
					"EventID NOT IN (Select EventID from groupevent) order by startdate asc");
			query.setInt(1, userId);
			
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				eventList.add(CreateEventEntity(set));
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
		
		return eventList;
	}
	
	
	public static List<EventEntity> listInvitableEvents (int inviterID, int inviteeID)
	{
		ArrayList<EventEntity> eventList = new ArrayList<EventEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"SELECT * from event where isdeleted = 0 " +
					"and EventID IN(select eventID from userevent where isdeleted = 0 and userid = ?)" +
					"and EventID NOT IN(select eventID from userevent where isdeleted = 0 and userid = ?)");
			query.setInt(1, inviterID);
			query.setInt(2, inviteeID);
			
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				eventList.add(CreateEventEntity(set));
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
		
		return eventList;
	}
	
	public static List<EventEntity> listGroupEvent(int groupID)
	{
		ArrayList<EventEntity> eventList = new ArrayList<EventEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"SELECT * FROM event where IsDeleted = 0  and ExpireDate < ? AND " +
					"EventID IN (Select EventID from groupevent where GroupID = ?) order by  startdate asc, expiredate asc");
			query.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			query.setInt(2, groupID);
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				eventList.add(CreateEventEntity(set));
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
		
		return eventList;
	}	
	
	
	public static boolean CreateEvent(EventEntity event)
	{
		boolean isSuccess = false;

		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"INSERT INTO Event ( StartDate, ExpireDate, CreatedByUser, UpdatedByUser, "
				  + "CreateDate, UpdateDate, Title, Description, IsApproved, IsDeleted, Location) "
				  + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			query.setDate(1, new java.sql.Date(event.getStartDate().getTime()));
			query.setDate(2, new java.sql.Date(event.getExpireDate().getTime()));
			query.setInt(3, event.getCreatedByUser());
			query.setInt(4, event.getUpdatedByUser());
			query.setDate(5, new java.sql.Date(event.getCreateDate().getTime()));
			query.setDate(6, new java.sql.Date(event.getUpdateDate().getTime()));
			query.setString(7, event.getTitle());
			query.setString(8, event.getDescription());
			query.setBoolean(9, event.getIsApproved());
			query.setBoolean(10, event.getIsDeleted());
			query.setString(11, event.getLocation());
			
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
	
	public static boolean UpdateEvent(EventEntity event)
	{
		boolean isSuccess = false;
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"UPDATE event SET StartDate = ? , ExpireDate = ? , CreatedByUser = ? , UpdatedByUser= ? , CreateDate = ? "
				  + ", UpdateDate = ? , Title = ? , Description = ? , IsApproved = ? , IsDeleted = ?, Location = ? "
				  + " WHERE EventID = ?");
			query.setDate(1, new java.sql.Date(event.getStartDate().getTime()));
			query.setDate(2, new java.sql.Date(event.getExpireDate().getTime()));
			query.setInt(3, event.getCreatedByUser());
			query.setInt(4, event.getUpdatedByUser());
			query.setDate(5, new java.sql.Date(event.getCreateDate().getTime()));
			query.setDate(6, new java.sql.Date(event.getUpdateDate().getTime()));
			query.setString(7, event.getTitle());
			query.setString(8, event.getDescription());
			query.setBoolean(9, event.getIsApproved());
			query.setBoolean(10, event.getIsDeleted());
			query.setInt(12, event.getEventID());
			query.setString(11, event.getLocation());
			
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
	
	
	private static EventEntity CreateEventEntity(ResultSet rs)
	{
		try {
			EventEntity event = new EventEntity();
			
			event.setEventID(rs.getInt("EventID"));
			event.setStartDate(rs.getDate("StartDate"));
			event.setExpireDate(rs.getDate("ExpireDate"));
			event.setCreatedByUser(rs.getInt("CreatedByUser"));
			event.setUpdatedByUser(rs.getInt("UpdatedByUser"));
			event.setCreateDate(rs.getDate("CreateDate"));
			event.setUpdateDate(rs.getDate("UpdateDate"));
			event.setTitle(rs.getString("Title"));
			event.setDescription(rs.getString("Description"));
			event.setIsApproved(rs.getBoolean("IsApproved"));
			event.setIsDeleted(rs.getBoolean("IsDeleted"));
			event.setLocation(rs.getString("Location"));
			return event;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
}
