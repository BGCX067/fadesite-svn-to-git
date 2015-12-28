<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.enterprise.web.helper.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Session Expired</title>
<%= HTMLHelper.GetHTMLHeader() %>
</head>
<body>
	<div style='min-height: 33%;'>
	</div>
	<div class="infoBox">
		<h3>Your session has expired!</h3>
		<br/>
		Click <a href='dispatcher?section=account&action=goLogin'>here</a> to login.
	</div>
</body>
</html>