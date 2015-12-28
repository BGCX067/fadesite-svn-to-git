package com.enterprise.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.enterprise.entity.EventAnnounceEntity;
import com.enterprise.entity.EventEntity;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


public class EventAnnounceDAO {
	
	public List<EventAnnounceEntity> listByEvent(int eventid)
	{
		ArrayList<EventAnnounceEntity> eventannounceList = new ArrayList<EventAnnounceEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"SELECT * FROM eventannouncement where IsDeleted = 0 and eventid = ?");
			query.setInt(1, eventid);
			
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				eventannounceList.add(CreateEventAnnounceEntity(set));
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
		
		return eventannounceList;
	}
	public static boolean createEventAnnounce(EventAnnounceEntity eventannounce)
	{
		boolean isSuccess = false;

		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"INSERT INTO eventannouncement ( AnnouncementID , EventID , isDeleted)"
				  + "values (?, ?, ?)");
			query.setInt(1, eventannounce.getAnnounceID());
			query.setInt(2, eventannounce.getEventID());
			query.setBoolean(3, eventannounce.getIsDeleted());

			
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
	public static boolean updateEventAnnounce(EventAnnounceEntity eventannounce)
	{
		boolean isSuccess = false;
	
	try {
		Connection con = ConnectionManager.GetConnection();
		PreparedStatement query = (PreparedStatement) con.prepareStatement(
				"UPDATE eventannouncement SET AnnouncementID = ? , EventID = ? , isDeleted= ? "
			  + " WHERE EventAnnounceID = ?");
		query.setInt(1, eventannounce.getAnnounceID());
		query.setInt(2, eventannounce.getEventID());
		query.setBoolean(3, eventannounce.getIsDeleted());
		query.setInt(4, eventannounce.getEventAnnounceID());


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
	
	public static EventAnnounceEntity getEventAnnounce (int eventannounce)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM eventannouncement where EventAnnounceID = ? and IsDeleted = 0");
			query.setInt(1, eventannounce);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				return CreateEventAnnounceEntity(set);
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
	
	public static EventEntity getEventByAnnounceId (int announceId)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM eventannouncement where AnnouncementID = ? and IsDeleted = 0");
			query.setInt(1, announceId);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				EventAnnounceEntity ea = CreateEventAnnounceEntity(set);
				return EventDAO.getEvent(ea.getEventID());
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
	
	private static EventAnnounceEntity CreateEventAnnounceEntity(ResultSet rs)
	{
		try {
			EventAnnounceEntity eventannounce = new EventAnnounceEntity();
			
			eventannounce.setEventAnnounceID(rs.getInt("EventAnnouncementID"));
			eventannounce.setAnnounceID(rs.getInt("AnnouncementID"));
			eventannounce.setEventID(rs.getInt("EventID"));
			eventannounce.setIsDeleted(rs.getBoolean("isDeleted"));

			return eventannounce;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}
