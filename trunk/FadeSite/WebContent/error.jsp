<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.enterprise.web.helper.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FadeSite</title>
<%= HTMLHelper.GetHTMLHeader() %>
</head>
<body>
	<div style='height:33%'></div>
	<div class="infoBox">
		<h3> Error! </h3>
		
		<p>You have encountered a system error. If this problem persists, please contact the system administrator.</p>
	
		<p>Click <a href='dispatcher?section=account&action=goLogin'>here</a> to return to login.</p>
	</div>
</body>
</html>