<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.enterprise.web.helper.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logging Out</title>
<%= HTMLHelper.GetHTMLHeader() %>
</head>
<body onload="redirect()">
	<div style='min-height: 33%;'>
	</div>
	<div class="infoBox">
		<h3>You have logged out.</h3>
		
		<br/>
		Redirecting you to login page.
		Click <a href='dispatcher?section=account&action=goLogin'>here</a> to go there immediately.
	</div>
</body>
<script type="text/javascript">
	function redirect()
	{
		setTimeout("window.location='dispatcher?section=account&action=goLogin'", 5000);
	}
</script>
</html>