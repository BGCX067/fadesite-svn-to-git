package com.enterprise.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.enterprise.entity.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class EventInvitationDAO {
	
	public static List<EventInvitationEntity> listInviteByInvitee(int invitee)
	{
		ArrayList<EventInvitationEntity> inviteList = new ArrayList<EventInvitationEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM eventinvitation where InviteeID = ? and IsDeleted = 0");
			query.setInt(1, invitee);
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				inviteList.add(CreateEventInvitationEntity(set));
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
		
		return inviteList;
	}	
	
	public static List<EventInvitationEntity> listInviteByInviter(int inviter)
	{
		ArrayList<EventInvitationEntity> inviteList = new ArrayList<EventInvitationEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM eventinvitation where InviterID = ? and IsDeleted = 0");
			query.setInt(1, inviter);
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				inviteList.add(CreateEventInvitationEntity(set));
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
		
		return inviteList;
	}	
	
	public static List<EventInvitationEntity> listInviteByEvent(int event)
	{
		ArrayList<EventInvitationEntity> inviteList = new ArrayList<EventInvitationEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM eventinvitation where EventID = ? and IsDeleted = 0");
			query.setInt(1, event);
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				inviteList.add(CreateEventInvitationEntity(set));
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
		
		return inviteList;
	}	
	
	public static EventInvitationEntity GetEventInvitation (int inviteID)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM eventinvitation where eventInviteID = ? and IsDeleted = 0");
			query.setInt(1, inviteID);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				return CreateEventInvitationEntity(set);
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
	
	public static boolean UpdateEventInvitation (EventInvitationEntity invite)
	{
		boolean isSuccess = false;
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"UPDATE eventinvitation SET InviteeID = ? , InviterID = ? , EventID = ? , isDeleted = ? , InviteAccepted = ?  "
				  + " WHERE EventInviteID = ?");
			query.setInt(1, invite.getInviteeID());
			query.setInt(2, invite.getInviterID());
			query.setInt(3, invite.getEventID());
			query.setBoolean(4, invite.getIsDeleted());
			query.setBoolean(5, invite.getInviteAccepted());
			query.setInt(6, invite.getEventInviteID());

			
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
	
	public static boolean CreateEventInvitation (EventInvitationEntity invite)
	{
		boolean isSuccess = false;

		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"INSERT INTO eventinvitation ( InviterID , InviteeID , EventID , isDeleted , InviteAccepted) "
				  + "values (?, ?, ?, ?, ?)");
			query.setInt(1, invite.getInviterID());
			query.setInt(2, invite.getInviteeID());
			query.setInt(3, invite.getEventID());
			query.setBoolean(4, invite.getIsDeleted());
			query.setBoolean(5, invite.getInviteAccepted());

			
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
	
		
	
	
	
	
	
	
	private static EventInvitationEntity CreateEventInvitationEntity(ResultSet rs)
	{
		try {
			EventInvitationEntity invite = new EventInvitationEntity();
			
			invite.setEventInviteID(rs.getInt("EventInviteID"));
			invite.setInviterID(rs.getInt("InviterID"));
			invite.setInviteeID(rs.getInt("InviteeID"));
			invite.setEventID(rs.getInt("EventID"));
			invite.setIsDeleted(rs.getBoolean("isDeleted"));
			invite.setInviteAccepted(rs.getBoolean("InviteAccepted"));
			return invite;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
}
