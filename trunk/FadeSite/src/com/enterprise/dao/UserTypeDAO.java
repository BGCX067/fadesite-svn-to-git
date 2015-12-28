package com.enterprise.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.enterprise.entity.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class UserTypeDAO
{

	public static UserTypeEntity GetUserType(int userTypeId)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM USERTYPE where UserTypeID = ?");
			query.setInt(1, userTypeId);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				return CreateUserTypeEntity(set);
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
	
	public static UserTypeEntity GetUserType(String name)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM USERTYPE where Name = ?");
			query.setString(1, name);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				return CreateUserTypeEntity(set);
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
	
	private static UserTypeEntity CreateUserTypeEntity(ResultSet rs)
	{
		try {
			UserTypeEntity usertype = new UserTypeEntity();
			
			usertype.setUserTypeId(rs.getInt("UserTypeID"));
			usertype.setName(rs.getString("Name"));
			
			return usertype;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
