<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.enterprise.dao.*, com.enterprise.entity.*, java.util.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
String contentPage = "/activities/list.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param name="pageTitle" value="My Activities" />
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

%>
	<div style='margin:30px;'>
		<center>
		<h4>My Activities</h4>
		</center>
	</div>
	<div class='subMenu'>
		<a href='dispatcher?section=activities&action=create'>Add an Activity</a>
	</div>
	<div style="height: 70%; overflow: auto; margin: 20px;">
	<div id="searchPanel">
	<div>
		<table id="dataTable" style='width: 100% !important; margin-top: 10px; margin-bottom: 10px;'
			cellspacing='0' cellpadding='5px'>
			<thead>
			<tr>
				<th>Position</th>
				<th>Organisation</th>
				<th>Location</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th></th>
				<th></th>
				<th></th>
			</tr>
			</thead>
			<tbody>
			
			<% List<ActivityEntity> activities = ActivityDAO.ListActivityByUser(userSession.getUserId());
			
			if (activities != null)
				{%>
				<% for (int i = 0 ; i < activities.size() ; i++)
					{
					ActivityEntity activity = activities.get(i);%>
					<tr>
						<td><%= activity.getPosition() %></td>
						<td><%= activity.getOrganisation() %></td>
						<td><%= activity.getLocation() %></td>
						<td><%= activity.getStartDateString() %></td>
						<td><%= activity.getEndDateString() %></td>
						<td><a href='<%= "dispatcher?section=activities&action=view&activityId=" + activity.getActivityID() %>'>Detailed View</a></td>
	  					<td><a href='<%= "dispatcher?section=activities&action=edit&activityId=" + activity.getActivityID() %>'>Edit</a></td>
	  					<td><a href='<%= "dispatcher?section=activities&action=delete&activityId=" + activity.getActivityID() %>'>Delete</a></td>
					</tr>
				<% } %>
			<% } %>
			</tbody>
		</table>
	</div>
</div>

<script type='text/javascript' language='javascript'>
	$(function() {
		$('#dataTable').dataTable({ "aoColumns": [
	        null,
	        null,
	        null,
	        null,
	        null,
	        { "bSearchable": false, "sWidth": "12%" },
	        { "bSearchable": false, "sWidth": "8%" },
	        { "bSearchable": false, "sWidth": "8%" }]});
	});
</script>
	
</div>
<div class='subMenu'>
	<a href="dispatcher?section=account&action=details">Back</a>
</div>

</body>
</html>