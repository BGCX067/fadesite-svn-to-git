<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="java.util.*, com.enterprise.entity.*, com.enterprise.dao.*"
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
String contentPage = "/groups/personal.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param name="pageTitle" value="My Groups" />
	<jsp:param name="embeddedPageName" value='<%= contentPage %>'/>
</jsp:forward>
<%
}
%>

<div style='margin:30px;'>
	<center>
	<h4>My Groups</h4>
	</center>
</div>
<div class='subMenu'>
<% if (user.getUserTypeId() == 3)
					{%>
	<a href='dispatcher?section=groups&action=create'>Create New Group</a> |
	<% } %>
	<a href='dispatcher?section=groups&action=list'>View All Groups</a>
</div>
<div style='margin:10px'>
	<% List<UserGroupEntity> userGroups = UserGroupDAO.listByUser(user.getUserId());%>
	<%
		for (int i = 0 ; i < userGroups.size() ; i++)
		{
			GroupEntity group = GroupDAO.GetGroup(userGroups.get(i).getGroupID());
			%>
		<div style='margin: 50px; border: 1px solid #ccc; padding: 10px; width: 45%;'>
			<h5 style='margin-bottom: 10px; margin-top:0px;'><%= group.getName() %></h5>
			<div style='font-size:14px'><%= group.getDescription() %></div>
			<div style='font-size:14px'>Members : <%= UserGroupDAO.getGroupMemberCount(group.getGroupID()) %></div>
			<div style='font-size: 14px; margin-top: 10px;'>
				<a href='<%= "dispatcher?section=groups&action=home&groupId=" + group.getGroupID() %>'>Group Home</a> |
				<a href='<%= "dispatcher?section=groups&action=members&groupId=" + group.getGroupID() %>'>View Members</a> |
				<% if (user.getUserTypeId() == 3)
					{%>
				<a href='<%= "dispatcher?section=groups&action=edit&groupId=" + group.getGroupID() %>'>Edit Details</a> |
				<% } %>
				<a href='<%= "dispatcher?section=groups&action=confirm&mode=leave&groupId=" + group.getGroupID() %>'>Leave Group</a>
			</div>
		</div>
			<%
		}
	%>
</div>
</body>
</html>