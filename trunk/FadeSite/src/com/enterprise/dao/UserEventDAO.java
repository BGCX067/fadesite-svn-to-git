package com.enterprise.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.enterprise.entity.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


public class UserEventDAO {
	
	public static List<UserEntity> listUserByEvent(int eventid)
	{
		ArrayList<UserEntity> userList = new ArrayList<UserEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"SELECT * FROM user where isdeleted = 0 and userid IN(select userid from userevent where IsDeleted = 0 and eventid = ?)");
			query.setInt(1, eventid);
			
			ResultSet set = query.executeQuery();
			while (set.next())
			{
				userList.add(CreateUserEntity(set));
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
		
		return userList;
	}
	
	public static List<EventEntity> listEventByUser(int userid)
	{
		ArrayList<EventEntity> eventList = new ArrayList<EventEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"SELECT * FROM event where isdeleted = 0  " +
					"and EventID IN(select EventID from userevent where IsDeleted = 0 and UserID = ?) order by startdate asc, expiredate asc");
			query.setInt(1, userid);
			
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
	public static boolean createUserEvent(UserEventEntity userevent)
	{
		boolean isSuccess = false;

		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"INSERT INTO userevent ( EventID , UserID , isDeleted)"
				  + "values (?, ?, ?)");
			query.setInt(1, userevent.getEventID());
			query.setInt(2, userevent.getUserID());
			query.setBoolean(3, userevent.getIsDeleted());

			
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
	public static boolean updateUserEvent(UserEventEntity userevent)
	{
		boolean isSuccess = false;
	
	try {
		Connection con = ConnectionManager.GetConnection();
		PreparedStatement query = (PreparedStatement) con.prepareStatement(
				"UPDATE userevent SET EventID = ? , UserID = ? , IsDeleted= ? "
			  + " WHERE UserEventID = ?");
		query.setInt(1, userevent.getEventID());
		query.setInt(2, userevent.getUserID());
		query.setBoolean(3, userevent.getIsDeleted());
		query.setInt(4, userevent.getUserEventID());


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
	
	public static UserEventEntity getUserEvent (int userevent)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM userevent where UserEventID = ? and IsDeleted = 0");
			query.setInt(1, userevent);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				return CreateUserEventEntity(set);
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
	
	public static UserEventEntity getUserEvent (int userId, int eventId)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM userevent where UserID = ? and EventID = ? and IsDeleted = 0");
			query.setInt(1, userId);
			query.setInt(2, eventId);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				return CreateUserEventEntity(set);
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
	
	public static int getEventMemberCount(int eventId)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"Select Count(*) as numMembers from `userevent` "
					+ " WHERE EventID = ? AND IsDeleted = 0");
			query.setInt(1, eventId);
			
			ResultSet rs = query.executeQuery();
			if (rs.next())
			{
				return rs.getInt("numMembers");
			}
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
		
		return 0;
	}
	
	private static UserEventEntity CreateUserEventEntity(ResultSet rs)
	{
		try {
			UserEventEntity userevent = new UserEventEntity();
			
			userevent.setUserEventID(rs.getInt("UserEventID"));
			userevent.setEventID(rs.getInt("EventID"));
			userevent.setUserID(rs.getInt("UserID"));
			userevent.setIsDeleted(rs.getBoolean("isDeleted"));

			return userevent;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
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

	private static UserEntity CreateUserEntity(ResultSet rs)
	{
		try {
			UserEntity user = new UserEntity();
			
			user.setUsername(rs.getString("Username"));
			user.setEmailAddress(rs.getString("EmailAddress"));
			user.setUserId(rs.getInt("UserID"));
			user.setDOB(rs.getDate("DateOfBirth"));
			user.setReferenceId(rs.getLong("ReferenceID"));
			user.setLocation(rs.getString("Location"));
			user.setGender(rs.getString("Gender"));
			user.setPassword(rs.getString("Password"));
			user.setIsActivated(rs.getBoolean("IsActivated"));
			user.setIsDeleted(rs.getBoolean("IsDeleted"));
			user.setFirstname(rs.getString("Firstname"));
			user.setSurname(rs.getString("Surname"));
			user.setAddress(rs.getString("Address"));
			user.setUserTypeId(rs.getInt("UserTypeID"));
			
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
}
