package com.enterprise.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.enterprise.entity.MessageEntity;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class MessageDAO
{

	public static List<MessageEntity> listBySender(int SenderID)
	{
		List<MessageEntity> list = new ArrayList<MessageEntity>();
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM message where senderID = ? and IsDeleted = 0 and DeletedBySender = 0");
			query.setInt(1, SenderID);
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				MessageEntity message = CreateMessageEntity(set);
				list.add(message);
			}
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
		
		return list;
	}
	
	public static List<MessageEntity> listBySendee(int SendeeID)
	{
		List<MessageEntity> list = new ArrayList<MessageEntity>();
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM message where sendeeID = ? and IsDeleted = 0 and DeletedBySendee = 0");
			query.setInt(1, SendeeID);
			ResultSet set = query.executeQuery();
			
			while (set.next())
			{
				MessageEntity message = CreateMessageEntity(set);
				list.add(message);
			}
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
		
		return list;
	}
	
	public static MessageEntity GetMessage (int messageID)
	{
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement("SELECT * FROM MESSAGE where messageID = ? and IsDeleted = 0");
			query.setInt(1, messageID);
			ResultSet set = query.executeQuery();
			
			if (set.next())
			{
				return CreateMessageEntity(set);
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
	
	public static boolean CreateMessage(MessageEntity message)
	{
		boolean isSuccess = false;

		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"INSERT INTO Message(SendeeID, SenderID, Contents, IsRead, IsDeleted, DeletedBySender, DeletedBySendee, Title)"
				  + "values (?, ?, ?, ?, ?, ?, ?, ?)");
			query.setInt(1, message.getSendeeID());
			query.setInt(2, message.getSenderID());
			query.setString(3,  message.getContents());
			query.setBoolean(4, message.getIsRead());
			query.setBoolean(5, message.getIsDeleted());
			query.setBoolean(6, message.getDeletedBySender());
			query.setBoolean(7, message.getDeletedBySendee());
			query.setString(8, message.getTitle());
			
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
	
	public static boolean UpdateMessage (MessageEntity message)
	{
		boolean isSuccess = false;
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"UPDATE Message SET SendeeID = ? , SenderID = ? , Contents = ? , IsRead = ? , IsDeleted = ? , "
				  + "DeletedBySender = ? , DeletedBySendee = ? , Title = ? WHERE MessageID = ?");
			query.setInt(1, message.getSendeeID());
			query.setInt(2, message.getSenderID());
			query.setString(3, message.getContents());
			query.setBoolean(4, message.getIsRead());
			query.setBoolean(5, message.getIsDeleted());
			query.setBoolean(6, message.getDeletedBySender());
			query.setBoolean(7, message.getDeletedBySendee());
			query.setString(8, message.getTitle());
			query.setInt(9, message.getMessageID());
			
			
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
	
	public static int GetNewMessages(int UserID)
	{
		int numMessages = 0;
		
		try {
			Connection con = ConnectionManager.GetConnection();
			PreparedStatement query = (PreparedStatement) con.prepareStatement(
					"SELECT COUNT(*) as NewMessages FROM message where sendeeID = ? and IsDeleted = 0 and IsRead=0");
			query.setInt(1, UserID);
			
			ResultSet rs = query.executeQuery();
			
			if (rs.next())
			{
				numMessages = rs.getInt("NewMessages");
			}
			
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
		
		
		return numMessages;
	}
	
	private static MessageEntity CreateMessageEntity(ResultSet rs)
	{
		try {
			MessageEntity message = new MessageEntity();
			
			message.setMessageID(rs.getInt("MessageID"));
			message.setSendeeID(rs.getInt("SendeeID"));
			message.setSenderID(rs.getInt("SenderID"));
			message.setContents(rs.getString("Contents"));
			message.setTitle(rs.getString("Title"));
			message.setIsRead(rs.getBoolean("IsRead"));
			message.setIsDeleted(rs.getBoolean("IsDeleted"));
			message.setDeletedBySendee(rs.getBoolean("DeletedBySendee"));
			message.setDeletedBySender(rs.getBoolean("DeletedBySender"));

			
			return message;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
}
