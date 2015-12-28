package com.enterprise.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.enterprise.entity.AnnounceEntity;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class AnnounceDAO {
	
	public static AnnounceEntity getLastAnnounce ()
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"SELECT * FROM ANNOUNCEMENT " +
					"where announceid IN(select max(announceid) from announcement)");
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				return CreateAnnounceEntity(set);
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
	
	
	public static AnnounceEntity getAnnounce (int announceID)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM ANNOUNCEMENT where announceid = ? and IsDeleted = 0");
			query.setInt(1, announceID);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				return CreateAnnounceEntity(set);
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
	
	public static boolean CreateAnnounce(AnnounceEntity announce)
	{
		boolean isSuccess = false;

		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"INSERT INTO Announcement( StartDate, ExpireDate, CreatedByUser, UpdatedByUser, "
				  + "CreateDate, UpdateDate, Title, Description, IsApproved, AnnounceType, IsDeleted) "
				  + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			query.setDate(1, new java.sql.Date(announce.getStartDate().getTime()));
			query.setDate(2, new java.sql.Date(announce.getExpireDate().getTime()));
			query.setInt(3, announce.getCreatedByUser());
			query.setInt(4, announce.getUpdatedByUser());
			query.setDate(5, new java.sql.Date(announce.getCreateDate().getTime()));
			query.setDate(6, new java.sql.Date(announce.getUpdateDate().getTime()));
			query.setString(7, announce.getTitle());
			query.setString(8, announce.getDescription());
			query.setBoolean(9, announce.getIsApproved());
			query.setInt(10, announce.getAnnounceType());
			query.setBoolean(11, announce.getIsDeleted());
			
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
	
	public static boolean UpdateAnnounce(AnnounceEntity announce)
	{
		boolean isSuccess = false;
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"UPDATE announcement SET StartDate = ? , ExpireDate = ? , CreatedByUser = ? , UpdatedByUser= ? , CreateDate = ? "
				  + ", UpdateDate = ? , Title = ? , Description = ? , IsApproved = ? , AnnounceType = ? , IsDeleted = ? "
				  + " WHERE AnnounceID = ?");
			query.setDate(1, new java.sql.Date(announce.getStartDate().getTime()));
			query.setDate(2, new java.sql.Date(announce.getExpireDate().getTime()));
			query.setInt(3, announce.getCreatedByUser());
			query.setInt(4, announce.getUpdatedByUser());
			query.setDate(5, new java.sql.Date(announce.getCreateDate().getTime()));
			query.setDate(6, new java.sql.Date(announce.getUpdateDate().getTime()));
			query.setString(7, announce.getTitle());
			query.setString(8, announce.getDescription());
			query.setBoolean(9, announce.getIsApproved());
			query.setInt(10, announce.getAnnounceType());
			query.setBoolean(11, announce.getIsDeleted());
			query.setInt(12, announce.getAnnounceID());
			
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

	public static List<AnnounceEntity> listAnnounce()
	{
		ArrayList<AnnounceEntity> announceList = new ArrayList<AnnounceEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM ANNOUNCEMENT where IsDeleted = 0 Order by UpdateDate DESC");
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				announceList.add(CreateAnnounceEntity(set));
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
		
		return announceList;
	}	
	
	public static List<AnnounceEntity> listPublicAnnounce()
	{
		ArrayList<AnnounceEntity> announceList = new ArrayList<AnnounceEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement
			("SELECT * FROM ANNOUNCEMENT where IsDeleted = 0 AND IsApproved = 1 AND StartDate <= ? AND ExpireDate >= ?" +
					"and AnnounceID NOT IN(Select AnnouncementID FROM eventannouncement) and " +
					"AnnounceID NOT IN (Select AnnounceID from groupannounce) Order By UpdateDate DESC");
			
			query.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			query.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
			
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				announceList.add(CreateAnnounceEntity(set));
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
		
		return announceList;
	}
	
	public static List<AnnounceEntity> listPublicAnnounceAdmin()
	{
		ArrayList<AnnounceEntity> announceList = new ArrayList<AnnounceEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement
			("SELECT * FROM ANNOUNCEMENT where IsDeleted = 0 AND IsApproved = 0 " +
					"and AnnounceID NOT IN(Select AnnouncementID FROM eventannouncement) and " +
					"AnnounceID NOT IN (Select AnnounceID from groupannounce) Order By UpdateDate DESC");
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				announceList.add(CreateAnnounceEntity(set));
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
		
		return announceList;
	}
	
	public static List<AnnounceEntity> listPublicAnnounceByUserId(int userId)
	{
		ArrayList<AnnounceEntity> announceList = new ArrayList<AnnounceEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement
			("SELECT * FROM ANNOUNCEMENT where IsDeleted = 0 AND CreatedByUser = ? " +
					"and AnnounceID NOT IN(Select AnnouncementID FROM eventannouncement) and " +
					"AnnounceID NOT IN (Select AnnounceID from groupannounce) Order By UpdateDate DESC");
			query.setInt(1, userId);
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				announceList.add(CreateAnnounceEntity(set));
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
		
		return announceList;
	}
	
	public static List<AnnounceEntity> listEventAnnouce(int EventID)
	{
		ArrayList<AnnounceEntity> announceList = new ArrayList<AnnounceEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement
			("SELECT * FROM ANNOUNCEMENT where IsDeleted = 0 and isapproved = 1 AND StartDate <= ? AND ExpireDate >= ? " +
					"and AnnounceID IN (select announcementID from eventannouncement where eventID = ?)");
			query.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			query.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
			query.setInt(3, EventID);
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				announceList.add(CreateAnnounceEntity(set));
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
		
		return announceList;
	}	
	
	
	public static List<AnnounceEntity> listEventAnnouceAdmin()
	{
		ArrayList<AnnounceEntity> announceList = new ArrayList<AnnounceEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement
			("SELECT * FROM ANNOUNCEMENT where IsDeleted = 0 and isapproved = 0 " +
					"and AnnounceID IN (select announcementID from eventannouncement)");
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				announceList.add(CreateAnnounceEntity(set));
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
		
		return announceList;
	}	
	
	
	public static List<AnnounceEntity> listGroupAnnouce(int EventID)
	{
		ArrayList<AnnounceEntity> announceList = new ArrayList<AnnounceEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement
			("SELECT * FROM ANNOUNCEMENT where IsDeleted = 0 and isapproved = 1  AND StartDate <= ? AND ExpireDate >= ?  " +
					"and AnnounceID IN (select announceID from groupannounce where groupID = ?)");
			query.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			query.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
			query.setInt(3, EventID);
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				announceList.add(CreateAnnounceEntity(set));
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
		
		return announceList;
	}	
	
	public static List<AnnounceEntity> listGroupAnnouceAdmin()
	{
		ArrayList<AnnounceEntity> announceList = new ArrayList<AnnounceEntity>();
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement
			("SELECT * FROM ANNOUNCEMENT where IsDeleted = 0 and isapproved = 0 " +
					"and AnnounceID IN (select announceID from groupannounce)");
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				announceList.add(CreateAnnounceEntity(set));
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
		
		return announceList;
	}	
	
	
	
	
	private static AnnounceEntity CreateAnnounceEntity(ResultSet rs)
	{
		try {
			AnnounceEntity announce = new AnnounceEntity();
			
			announce.setAnnounceID(rs.getInt("AnnounceID"));
			announce.setStartDate(rs.getDate("StartDate"));
			announce.setExpireDate(rs.getDate("ExpireDate"));
			announce.setCreatedByUser(rs.getInt("CreatedByUser"));
			announce.setUpdatedByUser(rs.getInt("UpdatedByUser"));
			announce.setCreateDate(rs.getDate("CreateDate"));
			announce.setUpdateDate(rs.getDate("UpdateDate"));
			announce.setTitle(rs.getString("Title"));
			announce.setDescription(rs.getString("Description"));
			announce.setIsApproved(rs.getBoolean("IsApproved"));
			announce.setAnnounceType(rs.getInt("AnnounceType"));
			announce.setIsDeleted(rs.getBoolean("IsDeleted"));
						
			return announce;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}
