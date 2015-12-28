package com.enterprise.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.enterprise.entity.AnnounceTypeEntity;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class AnnounceTypeDAO {

	public static AnnounceTypeEntity getAnnounceType (int announceType)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM announcementtype where announcementTypeID = ?");
			query.setInt(1, announceType);
			ResultSet set = query.executeQuery();
			if (set.next())
			{
				return CreateAnnounceTypeEntity(set);
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
	
	private static AnnounceTypeEntity CreateAnnounceTypeEntity(ResultSet rs)
	{
		try {
			AnnounceTypeEntity announcetype = new AnnounceTypeEntity();
			
			announcetype.setAnnounceTypeID(rs.getInt("AnnounceTypeID"));
			announcetype.setName(rs.getString("Name"));
			
			return announcetype;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
