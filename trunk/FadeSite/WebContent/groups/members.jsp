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
String contentPage = "/groups/members.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param value="Group Members" name="pageTitle"/>
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
	
	GroupEntity group = (GroupEntity) request.getAttribute("group");
%>

<div style='margin:30px;'>
	<center>
		<h4><%= group.getName() %>'s Members</h4>
	</center>
</div>
<div style='margin-left:30px;'>
<a href="<%= "dispatcher?section=groups&action=membersmap&groupId=" + group.getGroupID() %>">Members Map</a>
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
			
			<% List<UserGroupEntity> users = UserGroupDAO.listByGroup(group.getGroupID()); %>
			
			<% if (users != null)
				{%>
				<% for (int i = 0 ; i < users.size() ; i++)
					{
						UserEntity suser = UserDAO.GetUser(users.get(i).getUserID());
						%>
					<tr>
						<td><%= suser.getUserId() %></td>
						<td><%= suser.getFirstname() %></td>
						<td><%= suser.getSurname() %></td>
						<td><%= suser.getEmailAddress() %></td>
						<td><%= suser.getReferenceId() %></td>
						<td><a href='<%= "dispatcher?section=search&action=user&id=" + suser.getUserId() %>'>View User Profile</a></td>
					</tr>
				<% } %>
			<% } %>
			</tbody>
		</table>
	</div>
</div>

<div style='margin: 30px;'>
	<a href='<%= "dispatcher?section=groups&action=home&groupId=" + group.getGroupID() %>'>Back</a>
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