<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.enterprise.entity.*, java.util.*, com.enterprise.dao.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
String contentPage = "/events/home.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param name="pageTitle" value="Event Home" />
	<jsp:param name="embeddedPageName" value='<%= contentPage %>'/>
</jsp:forward>
<%
}
%>

<% UserEntity user = (UserEntity) session.getAttribute("userEntity");
	if (user == null)
	{
		user = new UserEntity();
	}
	
	EventEntity event = (EventEntity) request.getAttribute("event");
%>

<div style='margin: 30px;'>
	<center>
	<h4><%= event.getTitle() %>'s Home</h4>
	</center>
</div>
<div class='subMenu'>
	<a href="<%= "dispatcher?section=announcements&action=create&eventId=" + event.getEventID() %>">Create New Announcement</a> |
	<a href="<%= "dispatcher?section=events&action=members&eventId=" + event.getEventID() %>">View Members</a>
</div>

<% List<AnnounceEntity> announcements = AnnounceDAO.listEventAnnouce(event.getEventID()); %>

<% if (announcements != null)
	{
	%>
	<% for (int i = 0 ; i < announcements.size() ; i++)
	   {
	   		AnnounceEntity announce = (AnnounceEntity) announcements.get(i);%>
			<div style='width: 90%; margin: 30px; margin-bottom:75px;'>
				<div style='margin-left:30px;'>
					<h3><%= announce.getTitle() %></h3>
				</div>
				<hr style='margin-left: 50px; margin-right: 50px; border: 0px; border-top: solid 1px #ccc;'></hr>
				<div style='margin-top: 15px; margin-bottom: 15px;'>
					<label><%= announce.getDescription() %></label>
				</div>
				<hr style='margin-left: 50px; margin-right: 50px; border: 0px; border-top: solid 1px #ccc;'></hr>
				<div style='font-size: 13px; margin-top: 10px;'>
					<% UserEntity updateUser = UserDAO.GetUser(announce.getUpdatedByUser()); %>
					<i>Last Updated <label><%= announce.getUpdateDateString() %> <%= "by " + updateUser.getFirstname() + " " + updateUser.getSurname() %></label></i>
				<% if (announce.getUpdatedByUser() == user.getUserId())
				   {%>
				   &nbsp;&nbsp;&nbsp;&nbsp;
				 	<a href='<%= "dispatcher?section=announcements&action=edit&announceId=" + announce.getAnnounceID() %>'>Edit</a>
				 	<% 
				 	AnnounceUpdateEntity update = AnnounceUpdateDAO.getUpdateByAnnounce(announce.getAnnounceID());
				 	if (update != null)
		 			{ %>
		 				
		 				| <a href='<%= "dispatcher?section=announcements&action=viewupdate&updateId=" + update.getAnnounceUpdateID() %>'>View Pending Update</a>
		 			<%}%>
				 <%} %>
				 
				 </div>
			</div>
			
	<% } %>
<% } %>


</body>
</html>