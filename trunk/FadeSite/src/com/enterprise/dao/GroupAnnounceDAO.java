package com.enterprise.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.enterprise.entity.GroupAnnounceEntity;
import com.enterprise.entity.GroupEntity;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


public class GroupAnnounceDAO {
	
	public List<GroupAnnounceEntity> listByGroup(int groupid)
	{
		ArrayList<GroupAnnounceEntity> groupannounceList = new ArrayList<GroupAnnounceEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"SELECT * FROM groupannounce where IsDeleted = 0 and groupid = ?");
			query.setInt(1, groupid);
			
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				groupannounceList.add(CreateGroupAnnounceEntity(set));
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
		
		return groupannounceList;
	}
	public static boolean createGroupAnnounce(GroupAnnounceEntity groupannounce)
	{
		boolean isSuccess = false;

		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"INSERT INTO groupannounce ( AnnounceID , GroupID , isDeleted)"
				  + "values (?, ?, ?)");
			query.setInt(1, groupannounce.getAnnounceID());
			query.setInt(2, groupannounce.getGroupID());
			query.setBoolean(3, groupannounce.getIsDeleted());

			
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
	public static boolean updateGroupAnnounce(GroupAnnounceEntity groupannounce)
	{
		boolean isSuccess = false;
	
	try {
		Connection con = ConnectionManager.GetConnection();
		PreparedStatement query = (PreparedStatement) con.prepareStatement(
				"UPDATE groupannounce SET AnnounceID = ? , GroupID = ? , isDeleted= ? "
			  + " WHERE GroupAnnounceID = ?");
		query.setInt(1, groupannounce.getAnnounceID());
		query.setInt(2, groupannounce.getGroupID());
		query.setBoolean(3, groupannounce.getIsDeleted());
		query.setInt(4, groupannounce.getGroupAnnounceID());


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
	
	public static GroupAnnounceEntity getGroupAnnounce (int groupannounce)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM groupannounce where GroupAnnounceID = ? and IsDeleted = 0");
			query.setInt(1, groupannounce);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				return CreateGroupAnnounceEntity(set);
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
	
	public static GroupEntity getGroupByAnnounceId (int announceId)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM groupannounce where AnnounceId = ? and IsDeleted = 0");
			query.setInt(1, announceId);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				GroupAnnounceEntity ga = CreateGroupAnnounceEntity(set);
				return GroupDAO.GetGroup(ga.getGroupID());
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
	
	private static GroupAnnounceEntity CreateGroupAnnounceEntity(ResultSet rs)
	{
		try {
			GroupAnnounceEntity groupannounce = new GroupAnnounceEntity();
			
			groupannounce.setGroupAnnounceID(rs.getInt("GroupAnnounceID"));
			groupannounce.setAnnounceID(rs.getInt("AnnounceID"));
			groupannounce.setGroupID(rs.getInt("GroupID"));
			groupannounce.setIsDeleted(rs.getBoolean("isDeleted"));

			return groupannounce;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}
