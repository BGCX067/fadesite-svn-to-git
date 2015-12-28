package com.enterprise.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.enterprise.entity.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class UserTemporaryPasswordDAO
{

	public static UserTemporaryPasswordEntity GetUserTemporaryPassword(int userId)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM USERTEMPORARYPASSWORD where UserID = ? and IsDeleted = 0");
			query.setInt(1, userId);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				return CreateUserTemporaryPasswordEntity(set);
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
	
	public static UserTemporaryPasswordEntity GetUserTemporaryPasswordEntity(String password)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM USERTEMPORARYPASSWORD where Password = ? and IsDeleted = 0");
			query.setString(1, password);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				return CreateUserTemporaryPasswordEntity(set);
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
	
	public static boolean CreateUserTemporaryPassword(UserTemporaryPasswordEntity activation)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("INSERT into USERTEMPORARYPASSWORD(Password, UserID, IsDeleted, DateCreated) values (?, ?, ?, ?)");
			query.setString(1, activation.getPassword());
			query.setInt(2, activation.getUserId());
			query.setBoolean(3, activation.getIsDeleted());
			query.setDate(4, new java.sql.Date(activation.getCreatedDate().getTime()));
			
			return query.executeUpdate() > 0;
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
		
		return false;
	}
	
	public static boolean DeleteUserTemporaryPassword(int userId)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("UPDATE UserTemporaryPassword SET IsDeleted = 1 WHERE UserID = ?");
			query.setInt(1, userId);
			return query.executeUpdate() > 0;
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
		
		return false;
	}
	
	private static UserTemporaryPasswordEntity CreateUserTemporaryPasswordEntity(ResultSet rs)
	{
		try {
			UserTemporaryPasswordEntity ua = new UserTemporaryPasswordEntity();
			
			ua.setUserTemporaryPasswordId(rs.getInt("UserTemporaryPasswordID"));
			ua.setUserId(rs.getInt("UserID"));
			ua.setPassword(rs.getString("Password"));
			ua.setIsDeleted(rs.getBoolean("IsDeleted"));
			ua.setCreatedDate(rs.getDate("DateCreated"));
			return ua;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
