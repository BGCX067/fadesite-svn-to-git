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
String contentPage = "/announcements/personal.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param name="pageTitle" value="My Announcements" />
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

<div style='margin: 30px;'>
	<center>
	<h4>My Announcements</h4>
	</center>
</div>
<div class='subMenu'>
<% if (user.getUserId() > 1)
	{%>
	<a href='dispatcher?section=announcements&action=create'>Create New Announcement</a> |
	<% } %>
	<a href='dispatcher?section=announcements&action=list'>View All Announcements</a>
</div>

<% List<AnnounceEntity> announcements = AnnounceDAO.listPublicAnnounceByUserId(user.getUserId()); %>

<% if (announcements != null)
	{
	%>
	<% for (int i = 0 ; i < announcements.size() ; i++)
	   {
	   		AnnounceEntity announce = (AnnounceEntity) announcements.toArray()[i];%>
			<div style='width: 90%; margin: 30px; margin-bottom:75px;'>
				<div style='margin-left:30px;'>
					<h3 style='margin-bottom: 10px;'><%= announce.getTitle() %></h3>
				</div>
				<h5 style='font-weight:normal; margin: 0px'><%= announce.getStartDateString() + " to " + announce.getExpireDateString() %></h5>
				<hr style='margin-left: 50px; margin-right: 50px; border: 0px; border-top: solid 1px #ccc;'></hr>
				<div style='margin-top: 15px; margin-bottom: 15px;'>
					<label><%= announce.getDescription() %></label>
				</div>
				<hr style='margin-left: 50px; margin-right: 50px; border: 0px; border-top: solid 1px #ccc;'></hr>
				<div style='font-size: 13px; margin-top: 10px;'>
					<% if (announce.getIsApproved() == false)
					{%>
					[Pending Approval] &nbsp;&nbsp;&nbsp;
					<% } %>
					<i>Last Updated <label><%= announce.getUpdateDateString() %></label></i>
				 	&nbsp;&nbsp;&nbsp;&nbsp;
				 	<a href='<%= "dispatcher?section=announcements&action=edit&announceId=" + announce.getAnnounceID() %>'>Edit</a>
				 	<% 
				 	AnnounceUpdateEntity update = AnnounceUpdateDAO.getUpdateByAnnounce(announce.getAnnounceID());
				 	if (update != null)
		 			{ %>
		 				
		 				| <a href='<%= "dispatcher?section=announcements&action=viewupdate&updateId=" + update.getAnnounceUpdateID() %>'>View Pending Update</a>
		 			<%}%>
				 </div>
			</div>
			
	<% } %>
<% } %>

</body>
</html>