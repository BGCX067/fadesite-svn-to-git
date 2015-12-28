package com.enterprise.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.enterprise.entity.UserActivationEntity;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class UserActivationDAO
{

	public static UserActivationEntity GetUserActivation(int userId)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM USERACTIVATION where UserID = ? and IsDeleted = 0");
			query.setInt(1, userId);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				return CreateUserActivationEntity(set);
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
	
	public static UserActivationEntity GetUserActivation(String token)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM USERACTIVATION where Token = ? and IsDeleted = 0");
			query.setString(1, token);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				return CreateUserActivationEntity(set);
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
	
	public static boolean CreateUserActivation(UserActivationEntity activation)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("INSERT into USERACTIVATION(Token, UserID, IsDeleted) values (?, ?, ?)");
			query.setString(1, activation.getToken());
			query.setInt(2, activation.getUserId());
			query.setBoolean(3, activation.getIsDeleted());
			
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
	
	public static boolean DeleteUserActivation(int userId)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("UPDATE USERACTIVATION SET IsDeleted = 1 WHERE UserID = ?");
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
	
	private static UserActivationEntity CreateUserActivationEntity(ResultSet rs)
	{
		try {
			UserActivationEntity ua = new UserActivationEntity();
			
			ua.setUserActivationId(rs.getInt("UserActivationID"));
			ua.setUserId(rs.getInt("UserID"));
			ua.setToken(rs.getString("Token"));
			ua.setIsDeleted(rs.getBoolean("IsDeleted"));
			
			return ua;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
