package com.enterprise.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.enterprise.entity.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class UserBanDAO {
	
	public static List<UserBanEntity> ListBansPost()
	{
		java.util.Date currentDate = (java.util.Date) new java.util.Date();
		
		ArrayList<UserBanEntity> banList = new ArrayList<UserBanEntity>();
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"SELECT * FROM userban where IsDeleted = 0 and (ExpireDate >= ?) order by StartDate DESC");
			query.setDate(1, new java.sql.Date(currentDate.getTime()));
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				banList.add(CreateUserBanEntity(set));
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
		
		return banList;
	
	}
	public static UserBanEntity getUserBan (int userban)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM userban where UserBanID = ? AND IsDeleted = 0");
			query.setInt(1, userban);
			ResultSet set = query.executeQuery();
			if (set.next())
			{
				return CreateUserBanEntity(set);
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
	
	public static boolean CreateUserBan(UserBanEntity userban)
	{
		boolean isSuccess = false;

		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"INSERT INTO Userban(UserID, StartDate, ExpireDate, Description, IsDeleted)"
				  + "values (?, ?, ?, ?, ?)");
			query.setInt(1, userban.getUserID());
			query.setDate(2, new java.sql.Date(userban.getStartDate().getTime()));
			query.setDate(3, new java.sql.Date(userban.getExpireDate().getTime()));
			query.setString(4,  userban.getDescription());
			query.setBoolean(5, userban.getIsDeleted());
	
			
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
	
	public static boolean UpdateUserBan(UserBanEntity userban)
	{
		boolean isSuccess = false;
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"UPDATE Userban SET UserID = ? , StartDate = ? , ExpireDate = ? , Description = ? , IsDeleted = ? WHERE UserBanID = ?");
			query.setInt(1, userban.getUserID());
			query.setDate(2, new java.sql.Date(userban.getStartDate().getTime()));
			query.setDate(3, new java.sql.Date(userban.getExpireDate().getTime()));
			query.setString(4,  userban.getDescription());
			query.setBoolean(5, userban.getIsDeleted());
			query.setInt(6, userban.getUserBanID());
	
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
	
	public static UserBanEntity getBanByUser (int user)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM userban where UserID = ? AND IsDeleted = 0");
			query.setInt(1, user);
			ResultSet set = query.executeQuery();
			if (set.next())
			{
				return CreateUserBanEntity(set);
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
	
	
	
	private static UserBanEntity CreateUserBanEntity(ResultSet rs)
	{
		try {
			UserBanEntity userban = new UserBanEntity();
			
			userban.setUserBanID(rs.getInt("UserBanID"));
			userban.setUserID(rs.getInt("UserID"));
			userban.setStartDate(rs.getDate("StartDate"));
			userban.setExpireDate(rs.getDate("ExpireDate"));
			userban.setdescription(rs.getString("Description"));
			userban.setIsDeleted(rs.getBoolean("IsDeleted"));
			
			return userban;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
