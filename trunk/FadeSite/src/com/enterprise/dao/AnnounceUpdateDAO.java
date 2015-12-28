package com.enterprise.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.enterprise.entity.AnnounceEntity;
import com.enterprise.entity.AnnounceUpdateEntity;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class AnnounceUpdateDAO {

	public static AnnounceUpdateEntity getAnnounceUpdate (int announceupdateID)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM ANNOUNCEMENTUPDATE where announceupdateid = ? and IsDeleted = 0");
			query.setInt(1, announceupdateID);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				return CreateAnnounceUpdateEntity(set);
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
	
	public static ArrayList<AnnounceUpdateEntity> getAllAnnouncementUpdates()
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM ANNOUNCEMENTUPDATE where IsDeleted = 0");
			ResultSet set = query.executeQuery();
			
			ArrayList<AnnounceUpdateEntity> list = new ArrayList<AnnounceUpdateEntity>();
			
			while (set.next())
			{
				AnnounceUpdateEntity update = CreateAnnounceUpdateEntity(set);
				if (AnnounceDAO.getAnnounce(update.getAnnounceID()).getIsApproved())
				{
					list.add(update);
				}
			}
			
			return list;
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
	
	public static AnnounceUpdateEntity getUpdateByAnnounce (int announceID)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM ANNOUNCEMENTUPDATE where announceid = ? and IsDeleted = 0");
			query.setInt(1, announceID);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				return CreateAnnounceUpdateEntity(set);
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
	
	public static boolean CreateAnnounceUpdate(AnnounceUpdateEntity announce)
	{
		boolean isSuccess = false;

		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"INSERT INTO Announcementupdate( StartDate, ExpireDate, UpdateUser, "
				  + " UpdateDate, Title, Description, IsApproved, AnnouncementType, IsDeleted, AnnounceID) "
				  + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			query.setDate(1, new java.sql.Date(announce.getStartDate().getTime()));
			query.setDate(2, new java.sql.Date(announce.getExpireDate().getTime()));
			query.setInt(3, announce.getUpdatedByUser());
			query.setDate(4, new java.sql.Date(announce.getUpdateDate().getTime()));
			query.setString(5, announce.getTitle());
			query.setString(6, announce.getDescription());
			query.setBoolean(7, announce.getIsApproved());
			query.setInt(8, announce.getAnnounceType());
			query.setBoolean(9, announce.getIsDeleted());
			query.setInt(10, announce.getAnnounceID());
			
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
	
	public static boolean UpdateAnnounceUpdate(AnnounceUpdateEntity announce)
	{
		boolean isSuccess = false;
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"UPDATE announcementupdate SET StartDate = ? , ExpireDate = ? , UpdateUser= ?  "
				  + ", UpdateDate = ? , Title = ? , Description = ? , IsApproved = ? , AnnouncementType = ? , IsDeleted = ? , AnnounceID = ? "
				  + " WHERE AnnounceUpdateID = ?");
			query.setDate(1, new java.sql.Date(announce.getStartDate().getTime()));
			query.setDate(2, new java.sql.Date(announce.getExpireDate().getTime()));
			query.setInt(3, announce.getUpdatedByUser());
			query.setDate(4, new java.sql.Date(announce.getUpdateDate().getTime()));
			query.setString(5, announce.getTitle());
			query.setString(6, announce.getDescription());
			query.setBoolean(7, announce.getIsApproved());
			query.setInt(8, announce.getAnnounceType());
			query.setBoolean(9, announce.getIsDeleted());
			query.setInt(10, announce.getAnnounceID());
			query.setInt(11, announce.getAnnounceUpdateID());
			
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
	
	
	private static AnnounceUpdateEntity CreateAnnounceUpdateEntity(ResultSet rs)
	{
		try {
			AnnounceUpdateEntity announce = new AnnounceUpdateEntity();
			
			announce.setAnnounceID(rs.getInt("AnnounceID"));
			announce.setStartDate(rs.getDate("StartDate"));
			announce.setExpireDate(rs.getDate("ExpireDate"));
			announce.setUpdatedByUser(rs.getInt("UpdateUser"));
			announce.setUpdateDate(rs.getDate("UpdateDate"));
			announce.setTitle(rs.getString("Title"));
			announce.setDescription(rs.getString("Description"));
			announce.setIsApproved(rs.getBoolean("IsApproved"));
			announce.setAnnounceType(rs.getInt("AnnouncementType"));
			announce.setIsDeleted(rs.getBoolean("IsDeleted"));
			announce.setAnnounceUpdateID(rs.getInt("AnnounceUpdateID"));
						
			return announce;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}
