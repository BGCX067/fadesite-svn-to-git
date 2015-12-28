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
String contentPage = "/accounts/eventinvites.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param value="Event Invites" name="pageTitle"/>
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
%>

<div style='margin:30px'>
	<center>
		<h4>Event Invites</h4>
	</center>
</div>
<div id="searchPanel1"  style='width: 90%; margin-left: 30px;'>
	<center>
		<h5>My Invites Received</h5>
	</center>
	<div>
		<table id="dataTable" style='width: 100% !important; margin-top: 10px; margin-bottom: 10px;'
			cellspacing='0' cellpadding='5px'>
			<thead>
			<tr>
				<th>Sender</th>
				<th>Event</th>
				<th>Location</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Event Members</th>
				<th></th>
				<th></th>
			</tr>
			</thead>
			<tbody>
			
			<% List<EventInvitationEntity> invites = EventInvitationDAO.listInviteByInvitee(user.getUserId()); %>
			
			<% if (invites != null)
				{%>
				<% for (int i = 0 ; i < invites.size() ; i++)
					{
					UserEntity sender = UserDAO.GetUser(invites.get(i).getInviterID());
					EventEntity event = EventDAO.getEvent(invites.get(i).getEventID());%>
					<tr>
						<td><%= sender.getFirstname() + " " + sender.getSurname() %></td>
						<td><%= event.getTitle() %></td>
						<td><%= event.getLocation() %></td>
						<td><%= event.getStartDateString() %></td>
						<td><%= event.getExpireDateString() %></td>
						<td><%= UserEventDAO.getEventMemberCount(event.getEventID()) %></td>
						<td><a href='<%= "dispatcher?section=events&action=confirm&mode=join&eventId=" + event.getEventID() + "&inviteId=" + invites.get(i).getEventInviteID() %>'>Accept</a></td>
						<td><a href='<%= "dispatcher?section=account&action=eventinvites&mode=decline&inviteId=" + invites.get(i).getEventInviteID()  %>'>Decline</a></td>
					</tr>
				<% } %>
			<% } %>
			</tbody>
		</table>
	</div>
</div>

<script type='text/javascript' language='javascript'>
	$(function() {
		$('#searchPanel1 #dataTable').dataTable({ "aoColumns": [
	        null,
	        null,
	        null,
	        null,
	        null,
	        null,	        
	        { "bSearchable": false, "sWidth": "15%" },
	        { "bSearchable": false, "sWidth": "15%" }]});
	});
</script>

<div id="searchPanel12"  style='width: 90%; margin-left: 30px;'>
	<center>
		<h5>Invites Sent</h5>
	</center>
	<div>
		<table id="dataTable" style='width: 100% !important; margin-top: 10px; margin-bottom: 10px;'
			cellspacing='0' cellpadding='5px'>
			<thead>
			<tr>
				<th>Sender</th>
				<th>Event</th>
				<th>Location</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Event Members</th>
			</tr>
			</thead>
			<tbody>
			
			<% invites = EventInvitationDAO.listInviteByInviter(user.getUserId()); %>
			
			<% if (invites != null)
				{%>
				<% for (int i = 0 ; i < invites.size() ; i++)
					{
					UserEntity sendee = UserDAO.GetUser(invites.get(i).getInviteeID());
					EventEntity event = EventDAO.getEvent(invites.get(i).getEventID());%>
					<tr>
						<td><%= sendee.getFirstname() + " " + sendee.getSurname() %></td>
						<td><%= event.getTitle() %></td>
						<td><%= event.getLocation() %></td>
						<td><%= event.getStartDateString() %></td>
						<td><%= event.getExpireDateString() %></td>
						<td><%= UserEventDAO.getEventMemberCount(event.getEventID()) %></td>
					</tr>
				<% } %>
			<% } %>
			</tbody>
		</table>
	</div>
</div>

<script type='text/javascript' language='javascript'>
	$(function() {
		$('#searchPanel2 #dataTable').dataTable({ "aoColumns": [
null,
null,
null,
	        null,
	        null,
	        null]});
	});
</script>

</body>
</html>