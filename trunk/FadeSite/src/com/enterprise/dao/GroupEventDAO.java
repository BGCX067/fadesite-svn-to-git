package com.enterprise.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.enterprise.entity.GroupEventEntity;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


public class GroupEventDAO {
	
	public List<GroupEventEntity> listByGroup(int groupid)
	{
		ArrayList<GroupEventEntity> groupeventList = new ArrayList<GroupEventEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"SELECT * FROM groupevent where IsDeleted = 0 and groupid = ?");
			query.setInt(1, groupid);
			
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				groupeventList.add(CreateGroupEventEntity(set));
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
		
		return groupeventList;
	}
	public static boolean createGroupEvent(GroupEventEntity groupevent)
	{
		boolean isSuccess = false;

		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"INSERT INTO groupevent ( EventID , GroupID , isDeleted)"
				  + "values (?, ?, ?)");
			query.setInt(1, groupevent.getEventID());
			query.setInt(2, groupevent.getGroupID());
			query.setBoolean(3, groupevent.getIsDeleted());

			
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
	public static boolean updateGroupEvent(GroupEventEntity groupevent)
	{
		boolean isSuccess = false;
	
	try {
		Connection con = ConnectionManager.GetConnection();
		PreparedStatement query = (PreparedStatement) con.prepareStatement(
				"UPDATE groupevent SET EventID = ? , GroupID = ? , isDeleted= ? "
			  + " WHERE GroupEventID = ?");
		query.setInt(1, groupevent.getEventID());
		query.setInt(2, groupevent.getGroupID());
		query.setBoolean(3, groupevent.getIsDeleted());
		query.setInt(4, groupevent.getGroupEventID());


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
	
	public static GroupEventEntity getGroupEvent (int groupevent)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM groupevent where GroupEventID = ? and IsDeleted = 0");
			query.setInt(1, groupevent);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				return CreateGroupEventEntity(set);
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
	
	private static GroupEventEntity CreateGroupEventEntity(ResultSet rs)
	{
		try {
			GroupEventEntity groupevent = new GroupEventEntity();
			
			groupevent.setGroupEventID(rs.getInt("GroupEventID"));
			groupevent.setEventID(rs.getInt("EventID"));
			groupevent.setGroupID(rs.getInt("GroupID"));
			groupevent.setIsDeleted(rs.getBoolean("isDeleted"));

			return groupevent;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}
