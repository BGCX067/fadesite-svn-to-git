<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.enterprise.entity.*, java.util.*, com.enterprise.dao.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="http://maps.google.com/maps?file=api&amp;v=2.x&amp;key=ABQIAAAAzr2EBOXUKnm_jVnk0OJI7xSosDVG8KKPE1-m51RBrvYughuyMxQ-i1QfUnH94QxWIa6N4U6MouMmBA" type="text/javascript"></script>
</head>
<body onload="initialize()" onunload="GUnload()">
<%
String contentPage = "/search/groupinvite.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param value="Send Group Invite" name="pageTitle"/>
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
	
	UserEntity searchUser = (UserEntity) request.getAttribute("searchuser"); 

	if (searchUser == null)
	{
		searchUser = new UserEntity();
	}
%>

<center>
	<h3>Send Group Invite</h3>
</center>
<form method='POST' autocomplete='false' action="<%= "dispatcher?section=search&action=invite&mode=save&userId=" + searchUser.getUserId() %>" style='height: 300px;'>
	<div class="profileBox" style='width: 50%; margin-left:25%; height:100px; align: center;'>
		<table style='float:left; margin-top: 30px;'>
			<tr>
				<td width='120px'><label>User</label></td>
				<td>
					<input type="text" disabled="disabled" class="disabledTextBox"
						value='<%= searchUser.getFirstname() + " " + searchUser.getSurname() %>'>
					</input>
				</td>
			</tr>
			<tr>
				<td><label>Event</label></td>
				<td>
					<% List<GroupEntity> groups = GroupDAO.GetInvitableGroups(user.getUserId(), searchUser.getUserId()); %>
					<select name="groupId">
						<% for (int i = 0 ; i < groups.size() ; i++)
						{%>
							<option value='<%= groups.get(i).getGroupID() %>'><%= groups.get(i).getName() %></option>
						
						<%} %>
					</select>
				</td>
			</tr>
		</table>
	</div>
	<div class='profileBox' style='margin-top: 20px !important; text-align:center; width: 33% !important; margin-left: 33%;' align='center'>
		
		<input type='button' value='Back' onclick="<%= "window.location='dispatcher?section=search&action=user&id=" + searchUser.getUserId() + "'" %>"></input>
		<% if (groups != null && groups.size() > 0)
			{%>
		<input type="submit" value="Invite"></input>
		<% } %>
	</div>
</form>
</body>
</html>