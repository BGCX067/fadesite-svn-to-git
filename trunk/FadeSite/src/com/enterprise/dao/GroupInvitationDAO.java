package com.enterprise.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.enterprise.entity.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class GroupInvitationDAO {
	
	public static List<GroupInvitationEntity> listInviteByInvitee(int invitee)
	{
		ArrayList<GroupInvitationEntity> inviteList = new ArrayList<GroupInvitationEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM groupinvitation where InviteeID = ? and IsDeleted = 0 AND InviteAccepted=0");
			query.setInt(1, invitee);
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				GroupInvitationEntity gi = CreateGroupInvitationEntity(set);
				if (UserGroupDAO.getUserGroup(invitee, gi.getGroupID()) == null)
				{
					inviteList.add(gi);
				}
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
	
	public static List<GroupInvitationEntity> listInviteByInviter(int inviter)
	{
		ArrayList<GroupInvitationEntity> inviteList = new ArrayList<GroupInvitationEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM groupinvitation where InviterID = ? and IsDeleted = 0");
			query.setInt(1, inviter);
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				inviteList.add(CreateGroupInvitationEntity(set));
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
	
	public static List<GroupInvitationEntity> listInviteByGroup(int group)
	{
		ArrayList<GroupInvitationEntity> inviteList = new ArrayList<GroupInvitationEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM groupinvitation where GroupID = ? and IsDeleted = 0");
			query.setInt(1, group);
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				inviteList.add(CreateGroupInvitationEntity(set));
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
	
	public static GroupInvitationEntity GetGroupInvitation (int inviteID)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM groupinvitation where GroupInviteID = ? and IsDeleted = 0");
			query.setInt(1, inviteID);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				return CreateGroupInvitationEntity(set);
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
	
	public static boolean UpdateGroupInvitation (GroupInvitationEntity invite)
	{
		boolean isSuccess = false;
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"UPDATE groupinvitation SET InviteeID = ? , InviterID = ? , GroupID = ? , isDeleted = ? , InviteAccepted = ?  "
				  + " WHERE GroupInviteID = ?");
			query.setInt(1, invite.getInviteeID());
			query.setInt(2, invite.getInviterID());
			query.setInt(3, invite.getGroupID());
			query.setBoolean(4, invite.getIsDeleted());
			query.setBoolean(5, invite.getInviteAccepted());
			query.setInt(6, invite.getGroupInviteID());

			
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
	
	public static boolean CreateGroupInvitation (GroupInvitationEntity invite)
	{
		boolean isSuccess = false;

		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"INSERT INTO groupinvitation ( InviterID , InviteeID , GroupID , isDeleted , InviteAccepted) "
				  + "values (?, ?, ?, ?, ?)");
			query.setInt(1, invite.getInviterID());
			query.setInt(2, invite.getInviteeID());
			query.setInt(3, invite.getGroupID());
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
	
		
	
	
	
	
	
	
	private static GroupInvitationEntity CreateGroupInvitationEntity(ResultSet rs)
	{
		try {
			GroupInvitationEntity invite = new GroupInvitationEntity();
			
			invite.setGroupInviteID(rs.getInt("GroupInviteID"));
			invite.setInviterID(rs.getInt("InviterID"));
			invite.setInviteeID(rs.getInt("InviteeID"));
			invite.setGroupID(rs.getInt("GroupID"));
			invite.setIsDeleted(rs.getBoolean("IsDeleted"));
			invite.setInviteAccepted(rs.getBoolean("InviteAccepted"));
			return invite;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
}
