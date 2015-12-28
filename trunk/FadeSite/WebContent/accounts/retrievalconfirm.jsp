<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.enterprise.web.helper.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Password Retrieval</title>
<%= HTMLHelper.GetHTMLHeader() %>
</head>
<body>
	<div style="height:33%"></div>
	<div class="infoBox">
		<p>Temporary Password Sent.</p>
		<p>An email with a temporary password has been dispatched to your email address.</p>
		<p>Click <a href='dispatcher?section=account&action=goLogin'>here</a> to return to login.</p>
	</div>
</body>
</html>