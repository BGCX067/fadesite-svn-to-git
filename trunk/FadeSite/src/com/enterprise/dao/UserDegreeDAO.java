package com.enterprise.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

import com.enterprise.entity.UserEntity;
import com.enterprise.entity.UserDegreeEntity;
import com.enterprise.entity.DegreeEntity;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class UserDegreeDAO {
	

	
	public static List<UserEntity> ListClassmates(int degreeid, Date startDate, Date endDate)
	{
		
		ArrayList<UserEntity> degreeList = new ArrayList<UserEntity>();
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"SELECT distinct(u.userid),u.firstname,u.surname,u.username,u.dateofbirth,u.referenceid,u.location,u.gender,u.password,u.emailaddress,u.isactivated,u.isdeleted,u.usertypeid,u.address" +
					" FROM user u join userdegree ud on u.userid = ud.userid " +
					"join degree d on d.degreeid = ud.degreeid " +
					"where ud.degreeid = ? and ud.GraduatingDate between ? and ?");
			query.setInt(1, degreeid);
			query.setDate(2, new java.sql.Date(startDate.getTime()));
			query.setDate(3, new java.sql.Date(endDate.getTime()));
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				degreeList.add(CreateUserEntity(set));
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
		
		return degreeList;
	
	}
	
	public static List<UserDegreeEntity> ListDegreeByUser(int userid)
	{
		
		ArrayList<UserDegreeEntity> degreeList = new ArrayList<UserDegreeEntity>();
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"SELECT * FROM userdegree where userid = ? AND IsDeleted = 0");
			query.setInt(1, userid);
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				degreeList.add(CreateUserDegreeEntity(set));
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
		
		return degreeList;
	
	}
	
	public static List<UserEntity> ListUserByDegree (int degreeid)
	{
		
		ArrayList<UserEntity> userList = new ArrayList<UserEntity>();
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"SELECT * FROM user where userid IN(select userid from userdegree where degreeid = ?)");
			query.setInt(1, degreeid);
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
	public static UserDegreeEntity getUserDegree (int userdegree)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM userdegree where UserDegreeID = ? AND IsDeleted = 0");
			query.setInt(1, userdegree);
			ResultSet set = query.executeQuery();
			if (set.next())
			{
				return CreateUserDegreeEntity(set);
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
	
	public static boolean CreateUserDegree(UserDegreeEntity userdegree)
	{
		boolean isSuccess = false;

		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"INSERT INTO Userdegree(UserID, DegreeID, GraduatingDate, IsDeleted)"
				  + "values (?, ?, ?, ?)");
			query.setInt(1, userdegree.getUserID());
			query.setInt(2, userdegree.getDegreeID());
			query.setDate(3, new java.sql.Date(userdegree.getGraduatingDate().getTime()));
			query.setBoolean(4, userdegree.getIsDeleted());
	
			
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
	
	public static boolean UpdateUserDegree(UserDegreeEntity userdegree)
	{
		boolean isSuccess = false;
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"UPDATE Userdegree SET UserID = ? , DegreeID = ? , GraduatingDate = ? , IsDeleted = ? WHERE UserDegreeID = ?");
			query.setInt(1, userdegree.getUserID());
			query.setInt(2, userdegree.getDegreeID());
			query.setDate(3, new java.sql.Date(userdegree.getGraduatingDate().getTime()));
			query.setBoolean(4, userdegree.getIsDeleted());
			query.setInt(5, userdegree.getUserDegreeID());
	
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
	

	
	
	private static UserDegreeEntity CreateUserDegreeEntity(ResultSet rs)
	{
		try {
			UserDegreeEntity userdegree = new UserDegreeEntity();
			
			userdegree.setUserDegreeID(rs.getInt("UserDegreeID"));
			userdegree.setUserID(rs.getInt("UserID"));
			userdegree.setDegreeID(rs.getInt("DegreeID"));
			userdegree.setGraduatingDate(rs.getDate("GraduatingDate"));
			userdegree.setIsDeleted(rs.getBoolean("IsDeleted"));

			
			return userdegree;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
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

	


