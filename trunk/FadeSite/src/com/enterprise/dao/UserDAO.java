package com.enterprise.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.enterprise.entity.UserEntity;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class UserDAO
{

	public static UserEntity GetUserByUsername(String username)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM USER where username = ? and IsDeleted = 0");
			query.setString(1, username);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				return CreateUserEntity(set);
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
	
	public static UserEntity GetUser(int userid)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM USER where userid = ? and IsDeleted = 0");
			query.setInt(1, userid);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				return CreateUserEntity(set);
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
	
	public static UserEntity GetUserByEmail(String email)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM USER where EmailAddress = ? and IsDeleted = 0");
			query.setString(1, email);
			ResultSet set = query.executeQuery();

			if (set.next())
			{
				return CreateUserEntity(set);
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
	
	public static boolean CreateUser(UserEntity user)
	{
		boolean isSuccess = false;

		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"INSERT INTO User(Firstname, Surname, Username, DateOfBirth, ReferenceID, "
				  + "Location, Gender, Password, EmailAddress, IsActivated, IsDeleted, UserTypeID, Address) "
				  + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			query.setString(1, user.getFirstname());
			query.setString(2, user.getSurname());
			query.setString(3, user.getUsername());
			query.setDate(4,  new java.sql.Date(user.getDOB().getTime()));
			query.setLong(5, user.getReferenceId());
			query.setString(6, user.getLocation());
			query.setString(7, user.getGender());
			query.setString(8, user.getPassword());
			query.setString(9, user.getEmailAddress());
			query.setBoolean(10, user.getIsActivated());
			query.setBoolean(11, user.getIsDeleted());
			query.setInt(12, user.getUserTypeId());
			query.setString(13, user.getAddress());
			
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
	
	public static boolean UpdateUser(UserEntity user)
	{
		boolean isSuccess = false;
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"UPDATE User SET Firstname = ? , Surname = ? , Username = ? , DateOfBirth = ? , ReferenceID = ? "
				  + ", Location = ? , Gender = ? , Password = ? , EmailAddress = ? , IsActivated = ? , IsDeleted = ? "
				  + ", UserTypeID = ? , Address = ? WHERE UserID = ?");
			query.setString(1, user.getFirstname());
			query.setString(2, user.getSurname());
			query.setString(3, user.getUsername());
			query.setDate(4, new java.sql.Date(user.getDOB().getTime()));
			query.setLong(5, user.getReferenceId());
			query.setString(6, user.getLocation());
			query.setString(7, user.getGender());
			query.setString(8, user.getPassword());
			query.setString(9, user.getEmailAddress());
			query.setBoolean(10, user.getIsActivated());
			query.setBoolean(11, user.getIsDeleted());
			query.setInt(12, user.getUserTypeId());
			query.setString(13, user.getAddress());
			query.setInt(14, user.getUserId());
			
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
	
	public static List<UserEntity> GetAllUsers()
	{
		ArrayList<UserEntity> userList = new ArrayList<UserEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM USER where IsDeleted = 0");
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				userList.add(CreateUserEntity(set));
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
		
		return userList;
	}
	
	public static List<UserEntity> GetAllAlumni()
	{
		ArrayList<UserEntity> userList = new ArrayList<UserEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM USER where IsDeleted = 0 and UserTypeId = 0 and IsActivated = 1");
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				userList.add(CreateUserEntity(set));
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
		
		return userList;
	}
	
	public static List<UserEntity> GetAllActiveAlumni()
	{
		ArrayList<UserEntity> userList = new ArrayList<UserEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement)
				con.prepareStatement("SELECT * FROM USER where IsDeleted = 0 AND UserTypeID = 1 AND IsActivated = 1");
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				userList.add(CreateUserEntity(set));
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
		
		return userList;
	}
	
	private static UserEntity CreateUserEntity(ResultSet rs)
	{
		try {
			UserEntity user = new UserEntity();
			
			user.setUsername(rs.getString("Username"));
			user.setEmailAddress(rs.getString("EmailAddress"));
			user.setUserId(rs.getInt("UserID"));
			user.setDOB(rs.getDate("DateOfBirth"));
			user.setReferenceId(rs.getLong("ReferenceID"));
			user.setLocation(rs.getString("Location"));
			user.setGender(rs.getString("Gender"));
			user.setPassword(rs.getString("Password"));
			user.setIsActivated(rs.getBoolean("IsActivated"));
			user.setIsDeleted(rs.getBoolean("IsDeleted"));
			user.setFirstname(rs.getString("Firstname"));
			user.setSurname(rs.getString("Surname"));
			user.setAddress(rs.getString("Address"));
			user.setUserTypeId(rs.getInt("UserTypeID"));
			
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
}
