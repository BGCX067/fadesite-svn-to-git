package com.enterprise.dao;




import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.*;

public class ConnectionManager {

	private static String conURL = "jdbc:mysql://localhost:3306/fadesite";
	private static String conUsername = "root";
	private static String conPassword = "password";
	
	private static Connection connection;
	
	public static Connection GetConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		if (connection == null)
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = (Connection) DriverManager.getConnection(conURL, conUsername, conPassword);
		}

		return connection;
	}
}
