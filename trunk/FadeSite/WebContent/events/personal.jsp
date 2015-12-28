<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.enterprise.entity.*, java.util.*, com.enterprise.dao.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<% UserEntity user = (UserEntity) session.getAttribute("userEntity");
	if (user == null)
	{
		user = new UserEntity();
	}
%>
<%
String contentPage = "/events/personal.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param name="pageTitle" value="My Events" />
	<jsp:param name="embeddedPageName" value='<%= contentPage %>'/>
</jsp:forward>
<%
}
%>
<div style='margin:30px;'>
	<center>
	<h4>My Events</h4>
	</center>
</div>
<div class='subMenu'>
<% if (user.getUserTypeId() > 1) 
	{%>
	<a href='dispatcher?section=events&action=create'>Create New Event</a> |
	<a href='dispatcher?section=events&action=manage'>Manage My Events</a> |
	<% } %>
	<a href='dispatcher?section=events&action=list'>View All Events</a>
</div>
<% List<EventEntity> events = UserEventDAO.listEventByUser(user.getUserId()); %>

<% if (events != null)
	{
	%>
	<% for (int i = 0 ; i < events.size() ; i++)
	   {
		EventEntity event = (EventEntity) events.get(i);
		String endString = "";
		
		if (event.getExpireDate().after(event.getStartDate()))
		{
			endString = " till " + event.getExpireDateString();
		}
		%>
			<div style='width: 90%; margin: 30px; margin-bottom:75px;'>
				<div style='margin-left:30px;'>
					<h3 style='margin-bottom:0px'><%= event.getTitle() %></h3>
					<h5 style='font-weight: normal; margin-top: 5px; margin-bottom:5px;'><%= event.getStartDateString() + endString + " &nbsp; - &nbsp; " + event.getLocation()%></h5>
				</div>
				<hr style='margin-left: 50px; margin-right: 50px; border: 0px; border-top: solid 1px #ccc;'></hr>
				<div style='margin-top: 15px; margin-bottom: 15px;'>
					<label><%= event.getDescription() %></label>
				</div>
				<hr style='margin-left: 50px; margin-right: 50px; border: 0px; border-top: solid 1px #ccc;'></hr>
				<div style='font-size: 13px; margin-top: 10px;'>
					<% if (event.getIsApproved() == false)
					{%>
					[Pending Approval] &nbsp;&nbsp;&nbsp;
					<% } %>
					<i>Last Updated <label><%= event.getUpdateDateString() %></label></i> &nbsp;|&nbsp;
					<a href='<%= "dispatcher?section=events&action=view&eventId=" + event.getEventID() %>'>Detailed View</a> |
					<% if (user.getUserId() > 1)
						{%>
				 	<a href='<%= "dispatcher?section=events&action=edit&eventId=" + event.getEventID() %>'>Edit</a> |
				 	<% } %>
				 	<% 
				 	EventUpdateEntity update = EventUpdateDAO.getUpdateByEvent(event.getEventID());
				 	if (update != null && user.getUserId() > 1)
		 			{ %>
		 				
		 				<a href='<%= "dispatcher?section=events&action=viewupdate&updateId=" + update.getEventUpdateID() %>'>View Pending Update</a> |
		 			<%}%>
		 			<% if (UserEventDAO.getUserEvent(user.getUserId(), event.getEventID()) != null)
					 {%>
					<a href='<%= "dispatcher?section=events&action=home&eventId=" +  event.getEventID() %>'>Event Home</a> |
					<a href='<%= "dispatcher?section=events&action=members&eventId=" +  event.getEventID() %>'>View Participants</a> |
					<a href='<%= "dispatcher?section=events&action=confirm&mode=leave&eventId=" + event.getEventID() %>'>Leave Event</a>
				   <%}
				 	else
				 	{%>
				 	<a href="<%= "dispatcher?section=events&action=confirm&mode=join&eventId=" +event.getEventID() %>">Join Event</a>
				   <%} %>
				 </div>
			</div>
			
	<% } %>
<% } %>
<div style='height:30px'></div>
</body>
</html>