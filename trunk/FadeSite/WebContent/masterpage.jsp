<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.enterprise.entity.*, com.enterprise.web.helper.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
String pageTitle = request.getParameter("pageTitle");
if (pageTitle == null)
{
	pageTitle = "";	
}
%>
<title><%= pageTitle %></title>
<%= HTMLHelper.GetHTMLHeader() %>
</head>
<body>
<%
UserEntity userSession = (UserEntity) session.getAttribute("userEntity");

if (userSession == null)
{
	response.sendRedirect("dispatcher?section=account&action=session");
}
%>

<div id="loginArea">
	<%
		if (userSession != null)
		{
			int numNewMsg =  com.enterprise.dao.MessageDAO.GetNewMessages(userSession.getUserId());
	 %>
		<a href='dispatcher?section=account&action=details'><%= userSession.getUsername() %></a> |
		<a href='dispatcher?section=messages&action=list'>You have <%= numNewMsg %> new messages</a> |
	<% } %>
	<a href="dispatcher?section=account&action=logout">Log out</a>
</div>

<div id="content">
	<div id="menuArea">
		<div class='menuItem'>
			<a href='dispatcher?section=announcements&action=list'>View Announcements</a>
		</div>
		<div class='menuItem'>
			<a href='dispatcher?section=events&action=list'>View Events</a>
		</div>
		<div class='menuItem'>
			<a href='dispatcher?section=groups&action=list'>View Groups</a>
		</div>
		<% if (userSession.getUserTypeId() == 3)
					{%>
		<div class='menuItem'>
			<a href='dispatcher?section=admin&action=default'>Admin Panel</a>
		</div>
		<% } %>
		<div class='menuItem'>
			<a href='dispatcher?section=search&action=default'>User List</a>
		</div>
	</div>
	
	<div id="contentArea">
		<% String embeddedPageName = request.getParameter("embeddedPageName");
			if (embeddedPageName != null)
			{
		%>
			
		<jsp:include page="<%=embeddedPageName%>">
		<jsp:param name="fromMasterPage" value="true"/>
		</jsp:include>
		
		<%}%>
	</div>
</div>

<div id="footerArea">
	&nbsp;
</div>
</body>
</html>