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
String contentPage = "/accounts/groupinvites.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param value="Group Invites" name="pageTitle"/>
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
		<h4>Group Invites</h4>
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
				<th>Group</th>
				<th>Group Members</th>
				<th></th>
				<th></th>
			</tr>
			</thead>
			<tbody>
			
			<% List<GroupInvitationEntity> invites = GroupInvitationDAO.listInviteByInvitee(user.getUserId()); %>
			
			<% if (invites != null)
				{%>
				<% for (int i = 0 ; i < invites.size() ; i++)
					{
					UserEntity sender = UserDAO.GetUser(invites.get(i).getInviterID());
					GroupEntity group = GroupDAO.GetGroup(invites.get(i).getGroupID());%>
					<tr>
						<td><%= sender.getFirstname() + " " + sender.getSurname() %></td>
						<td><%= group.getName() %></td>
						<td><%= UserGroupDAO.getGroupMemberCount(group.getGroupID()) %></td>
						<td><a href='<%= "dispatcher?section=groups&action=confirm&mode=join&groupId=" + group.getGroupID() + "&inviteId=" + invites.get(i).getGroupInviteID() %>'>Accept</a></td>
						<td><a href='<%= "dispatcher?section=account&action=groupinvites&mode=decline&inviteId=" + invites.get(i).getGroupInviteID()  %>'>Decline</a></td>
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
	        { "bSearchable": false, "sWidth": "15%" },
	        { "bSearchable": false, "sWidth": "15%" }]});
	});
</script>

<div id="searchPanel2" style='width: 90%; margin-left: 30px;'>
	<center>
		<h5>Invites Sent</h5>
	</center>
	<div>
		<table id="dataTable" style='width: 100% !important; margin-top: 10px; margin-bottom: 10px;'
			cellspacing='0' cellpadding='5px'>
			<thead>
			<tr>
				<th>Invitee</th>
				<th>Group</th>
				<th>Group Members</th>
			</tr>
			</thead>
			<tbody>
			
			<% invites = GroupInvitationDAO.listInviteByInviter(user.getUserId()); %>
			
			<% if (invites != null)
				{%>
				<% for (int i = 0 ; i < invites.size() ; i++)
					{
					UserEntity sendee = UserDAO.GetUser(invites.get(i).getInviteeID());
					GroupEntity group = GroupDAO.GetGroup(invites.get(i).getGroupID());%>
					<tr>
						<td><%= sendee.getFirstname() + " " + sendee.getSurname() %></td>
						<td><%= group.getName() %></td>
						<td><%= UserGroupDAO.getGroupMemberCount(group.getGroupID()) %></td>
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
	        null]});
	});
</script>

</body>
</html>