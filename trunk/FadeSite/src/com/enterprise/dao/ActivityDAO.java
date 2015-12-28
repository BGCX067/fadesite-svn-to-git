package com.enterprise.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.enterprise.entity.ActivityEntity;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ActivityDAO {

	public static boolean CreateActivity(ActivityEntity activity)
	{
		boolean isSuccess = false;

		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"INSERT INTO activity(UserID, StartDate, EndDate, Name, Location, "
				  + "Organisation, Description, Position, IsDeleted, Project) "
				  + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			query.setInt(1, activity.getUserID());
			query.setDate(2, new java.sql.Date(activity.getStartDate().getTime()));
			query.setDate(3, new java.sql.Date(activity.getEndDate().getTime()));
			query.setString(4,  activity.getName());
			query.setString(5, activity.getLocation());
			query.setString(6, activity.getOrganisation());
			query.setString(7, activity.getDescription());
			query.setString(8, activity.getPosition());
			query.setBoolean(9, activity.getIsDeleted());
			query.setString(10, activity.getProject());
			
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
	public static ActivityEntity getActivity (int activityID)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM activity where activityid = ? and IsDeleted = 0");
			query.setInt(1, activityID);
			ResultSet set = query.executeQuery();
			if (set.next())
			{
				return CreateActivityEntity(set);
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
	
	public static List<ActivityEntity> ListActivityByUser(int userID)
	{
		ArrayList<ActivityEntity> activityList = new ArrayList<ActivityEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM activity where IsDeleted = 0 and UserID = ? Order by EndDate DESC, StartDate DESC");
			query.setInt(1, userID);
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				activityList.add(CreateActivityEntity(set));
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
		
		return activityList;
	}
	
	public static boolean UpdateActivity(ActivityEntity activity)
	{
		boolean isSuccess = false;
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"UPDATE activity SET UserID = ? , StartDate = ? , EndDate = ? , Name = ? , Location = ? "
				  + ", Organisation = ? , Description = ? , Position = ? , IsDeleted = ?, Project = ?"
				  + " WHERE activityID = ?");
			query.setInt(1, activity.getUserID());
			query.setDate(2, new java.sql.Date(activity.getStartDate().getTime()));
			query.setDate(3, new java.sql.Date(activity.getEndDate().getTime()));
			query.setString(4, activity.getName());
			query.setString(5, activity.getLocation());
			query.setString(6, activity.getOrganisation());
			query.setString(7, activity.getDescription());
			query.setString(8, activity.getPosition());
			query.setBoolean(9, activity.getIsDeleted());
			query.setString(10, activity.getProject());
			query.setInt(11, activity.getActivityID());

			
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
	private static ActivityEntity CreateActivityEntity(ResultSet rs)
	{
		try {
			ActivityEntity activity = new ActivityEntity();
			
			activity.setActivityID(rs.getInt("ActivityID"));
			activity.setUserID(rs.getInt("UserID"));
			activity.setStartDate(rs.getDate("StartDate"));
			activity.setEndDate(rs.getDate("EndDate"));
			activity.setName(rs.getString("Name"));
			activity.setLocation(rs.getString("Location"));
			activity.setOrganisation(rs.getString("Organisation"));
			activity.setDescription(rs.getString("Description"));
			activity.setPosition(rs.getString("Position"));
			activity.setIsDeleted(rs.getBoolean("IsDeleted"));
			activity.setProject(rs.getString("Project"));
			
						
			return activity;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
}
