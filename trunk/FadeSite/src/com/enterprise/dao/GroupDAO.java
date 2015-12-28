package com.enterprise.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.enterprise.entity.GroupEntity;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class GroupDAO {
	
	public static List<GroupEntity> GetInvitableGroups(int inviterID , int inviteeID)
	{
			ArrayList<GroupEntity> groupList = new ArrayList<GroupEntity>();
			
			try {
				Connection con = ConnectionManager.GetConnection();
				PreparedStatement query = (PreparedStatement) con.prepareStatement(
						"SELECT * FROM `group` where IsDeleted = 0 " +
						"and GroupID IN(Select GroupID from usergroup where UserID = ? and isDeleted = 0) " +
						"and GroupID NOT IN(Select GroupID from usergroup where UserID = ? and isDeleted = 0)");
				query.setInt(1, inviterID);
				query.setInt(2, inviteeID);
				ResultSet set = query.executeQuery();
				
				while (set.next())
				{
					groupList.add(CreateGroupEntity(set));
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
			
			return groupList;
		
	}
	
	public static List<GroupEntity> GetAllGroups()
	{
		ArrayList<GroupEntity> groupList = new ArrayList<GroupEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM `group` where IsDeleted = 0");
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				groupList.add(CreateGroupEntity(set));
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
		
		return groupList;
	}
	
	public static List<GroupEntity> GetUserGroups(int userid)
	{
		ArrayList<GroupEntity> groupList = new ArrayList<GroupEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"SELECT * FROM `group` where IsDeleted = 0 " +
					"and groupid IN(select groupid from usergroup where userid = ?)");
			query.setInt(1, userid);
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				groupList.add(CreateGroupEntity(set));
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
		
		return groupList;
	}
	
	public static boolean CreateGroup(GroupEntity group)
	{
		boolean isSuccess = false;

		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"INSERT INTO `Group`(Name, Description, IsDeleted) "
				  + "values (?, ?, ?)");
			query.setString(1, group.getName());
			query.setString(2, group.getDescription());
			query.setBoolean(3, group.getIsDeleted());

			
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
	
	public static boolean UpdateGroup(GroupEntity group)
	{
		boolean isSuccess = false;
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"UPDATE `group` SET Name = ? , Description = ? , IsDeleted = ?"
				  + " WHERE GroupID = ?");
			query.setString(1, group.getName());
			query.setString(2, group.getDescription());
			query.setBoolean(3, group.getIsDeleted());
			query.setInt(4, group.getGroupID());

			
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
	
	
	
	
	public static GroupEntity GetGroup(int groupid)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM `group` where groupid = ? and IsDeleted = 0");
			query.setInt(1, groupid);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				return CreateGroupEntity(set);
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
	
	
	
	
	
	private static GroupEntity CreateGroupEntity(ResultSet rs)
	{
		try {
			GroupEntity group = new GroupEntity();
			
			group.setName(rs.getString("Name"));
			group.setDescription(rs.getString("Description"));
			group.setGroupID(rs.getInt("GroupID"));
			group.setIsDeleted(rs.getBoolean("IsDeleted"));
						
			return group;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
}
