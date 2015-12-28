package com.enterprise.web.helper;

import javax.servlet.http.Cookie;

public class HTMLHelper
{
	public static String GetHTMLHeader()
	{
		String header = "";
		
		header += "<LINK href='Styles/default.css' rel='stylesheet' type='text/css'></LINK>";
		header += "<LINK href='Styles/jquery-ui-1.7.1.custom.css' rel='stylesheet' type='text/css'></LINK>";
		header += "<SCRIPT type='text/javascript' src='Scripts/jquery-1.3.2.min.js'></SCRIPT>";
		header += "<SCRIPT type='text/javascript' src='Scripts/jquery-ui-1.7.1.custom.min.js'></SCRIPT>";
		header += "<SCRIPT type='text/javascript' src='Scripts/js-core.2.8.0.min.js'></SCRIPT>";
		
		header += "<LINK href='../Styles/default.css' rel='stylesheet' type='text/css'></LINK>";
		header += "<LINK href='../Styles/jquery-ui-1.7.1.custom.css' rel='stylesheet' type='text/css'></LINK>";
		header += "<SCRIPT type='text/javascript' src='../Scripts/jquery-1.3.2.min.js'></SCRIPT>";
		header += "<SCRIPT type='text/javascript' src='../Scripts/jquery-ui-1.7.1.custom.min.js'></SCRIPT>";
		header += "<SCRIPT type='text/javascript' src='../Scripts/js-core.2.8.0.min.js'></SCRIPT>";
		
		header += "<LINK href='Styles/dataTable.css' rel='stylesheet' type='text/css'></LINK>";
		header += "<SCRIPT type='text/javascript' src='Scripts/jquery.dataTables.js'></SCRIPT>";
		header += "<LINK href='../Styles/dataTable.css' rel='stylesheet' type='text/css'></LINK>";
		header += "<SCRIPT type='text/javascript' src='../Scripts/jquery.dataTables.js'></SCRIPT>";
		
		header += "<SCRIPT type='text/javascript' src='Scripts/jquery.markitup.js'></SCRIPT>";
		header += "<SCRIPT type='text/javascript' src='../Scripts/jquery.markitup.js'></SCRIPT>";
		header += "<SCRIPT type='text/javascript' src='Scripts/set.js'></SCRIPT>";
		header += "<SCRIPT type='text/javascript' src='../Scripts/set.js'></SCRIPT>";
		
		header += "<LINK href='Styles/style.css' rel='stylesheet' type='text/css'></LINK>";
		header += "<LINK href='../Styles/style.css' rel='stylesheet' type='text/css'></LINK>";
		
		header += "<SCRIPT type='text/javascript' src='Scripts/custom.js'></SCRIPT>";
		header += "<SCRIPT type='text/javascript' src='../Scripts/custom.js'></SCRIPT>";
		
		return header;
	}
	
	public static String GetCookieValue(Cookie[] cookies, String cookieName, String defaultValue)
	{
		for (int i=0; i<cookies.length; i++)
		{
			Cookie cookie = cookies[i];
			if (cookieName.equals(cookie.getName()))
			{
				return(cookie.getValue());
			}
		}
		
		return(defaultValue);
	}

}
