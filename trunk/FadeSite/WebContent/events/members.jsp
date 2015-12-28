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
String contentPage = "/events/members.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param value="Event Participants" name="pageTitle"/>
	<jsp:param name="embeddedPageName" value='<%= contentPage %>'/>
</jsp:forward>
<%
}
%>

<% UserEntity userSession = (UserEntity) session.getAttribute("userEntity"); 

	if (userSession == null)
	{
		userSession = new UserEntity();
	}
	
	EventEntity event = (EventEntity) request.getAttribute("event");
%>

<div style='margin:30px;'>
	<center>
		<h4><%= event.getTitle() %>'s Participants</h4>
	</center>
</div>
<div id="searchPanel">
	<div>
		<table id="dataTable" style='width: 100% !important; margin-top: 10px; margin-bottom: 10px;'
			cellspacing='0' cellpadding='5px'>
			<thead>
			<tr>
				<th></th>
				<th>Firstname</th>
				<th>Surname</th>
				<th>Email Address</th>
				<th>Reference ID</th>
				<th></th>
			</tr>
			</thead>
			<tbody>
			
			<% List<UserEntity> users = UserEventDAO.listUserByEvent(event.getEventID()); %>
			
			<% if (users != null)
				{%>
				<% for (int i = 0 ; i < users.size() ; i++)
					{
					UserEntity user = users.get(i);
						%>
					<tr>
						<td><%= user.getUserId() %></td>
						<td><%= user.getFirstname() %></td>
						<td><%= user.getSurname() %></td>
						<td><%= user.getEmailAddress() %></td>
						<td><%= user.getReferenceId() %></td>
						<td><a href='<%= "dispatcher?section=search&action=user&id=" + user.getUserId() %>'>View User Profile</a></td>
					</tr>
				<% } %>
			<% } %>
			</tbody>
		</table>
	</div>
</div>

<div style='margin: 30px;'>
	<a href='<%= "dispatcher?section=events&action=home&eventId=" + event.getEventID() %>'>Back</a>
</div>

<script type='text/javascript' language='javascript'>
	$(function() {
		$('#dataTable').dataTable({ "aoColumns": [
	        { "bSearchable": false, "bVisible": false },
	        null,
	        null,
	        null,
	        null,
	        { "bSearchable": false, "sWidth": "15%" }]});
	});
</script>

</body>
</html>