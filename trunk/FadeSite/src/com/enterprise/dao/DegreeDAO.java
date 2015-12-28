package com.enterprise.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.enterprise.entity.DegreeEntity;
import com.enterprise.entity.UserEntity;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DegreeDAO {
	
	public static List<DegreeEntity> ListDegrees()
	{
		ArrayList<DegreeEntity>	degreeList = new ArrayList<DegreeEntity>();
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM degree");
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				degreeList.add(CreateDegreeEntity(set));
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
	
	public static DegreeEntity GetDegree (int degreeid)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM degree where degreeid = ?");
			query.setInt(1, degreeid);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				return CreateDegreeEntity(set);
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
	
	private static DegreeEntity CreateDegreeEntity(ResultSet rs)
	{
		try {
			DegreeEntity degree = new DegreeEntity();
			
			degree.setDegreeID(rs.getInt("DegreeID"));
			degree.setDegreeName(rs.getString("DegreeName"));
		
			
			return degree;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}
