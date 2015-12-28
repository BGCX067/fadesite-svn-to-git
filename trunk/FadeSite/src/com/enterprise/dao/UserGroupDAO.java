package com.enterprise.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.enterprise.entity.AnnounceUpdateEntity;
import com.enterprise.entity.UserGroupEntity;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class UserGroupDAO {
	
	public static List<UserGroupEntity> listByUser(int user)
	{
		ArrayList<UserGroupEntity> usergroupList = new ArrayList<UserGroupEntity>();
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM usergroup where userID = ? and IsDeleted = 0");
			query.setInt(1, user);
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				usergroupList.add(CreateUserGroupEntity(set));
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
		
		return usergroupList;
	}
	
	public static List<UserGroupEntity> listByGroup(int group)
	{
		ArrayList<UserGroupEntity> usergroupList = new ArrayList<UserGroupEntity>();
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM usergroup where groupID = ? and IsDeleted = 0");
			query.setInt(1, group);
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				usergroupList.add(CreateUserGroupEntity(set));
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
		
		return usergroupList;
	}
	
	public static boolean createUserGroup(UserGroupEntity usergroup)
	{
		boolean isSuccess = false;

		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"INSERT INTO usergroup( UserID , GroupID, isDeleted) "
				  + "values (?, ?, ?)");
			query.setInt(1, usergroup.getUserID());
			query.setInt(2, usergroup.getGroupID());
			query.setBoolean(3, usergroup.getIsDeleted());

			
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
	
	public static int getGroupMemberCount(int groupId)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"Select Count(*) as numMembers from `usergroup` "
					+ " WHERE GroupID = ? AND IsDeleted = 0");
			query.setInt(1, groupId);
			
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
	
	public static boolean updateUserGroup(UserGroupEntity usergroup)
	{
		boolean isSuccess = false;
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"UPDATE usergroup SET UserID = ? , GroupID = ? , isDeleted = ?  "
					+ " WHERE UserGroupID = ?");
			query.setInt(1, usergroup.getUserID());
			query.setInt(2, usergroup.getGroupID());
			query.setBoolean(3, usergroup.getIsDeleted());
			query.setInt(4, usergroup.getUserGroupID());
	
			
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
	
	
	public static UserGroupEntity getUserGroupByUserGroupId(int usergroup)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM usergroup where UserGroupID = ? and IsDeleted = 0");
			query.setInt(1, usergroup);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				return CreateUserGroupEntity(set);
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
	
	public static UserGroupEntity getUserGroup(int userId, int groupId)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM usergroup where UserID = ? and GroupID = ? and IsDeleted = 0");
			query.setInt(1, userId);
			query.setInt(2, groupId);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				return CreateUserGroupEntity(set);
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

	private static UserGroupEntity CreateUserGroupEntity(ResultSet rs)
	{
		try {
			UserGroupEntity usergroup = new UserGroupEntity();
			
			usergroup.setUserGroupID(rs.getInt("UserGroupID"));
			usergroup.setUserID(rs.getInt("UserID"));
			usergroup.setGroupID(rs.getInt("GroupID"));
			usergroup.setIsDeleted(rs.getBoolean("isDeleted"));

			return usergroup;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
}
