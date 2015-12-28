<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.enterprise.entity.*, com.enterprise.dao.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
String contentPage = "/announcements/viewupdate.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param name="pageTitle" value="Pending Announcement Update" />
	<jsp:param name="embeddedPageName" value='<%= contentPage %>'/>
</jsp:forward>
<%
}
%>


<%
	UserEntity sessionUser = (UserEntity) session.getAttribute("userEntity");
	if (sessionUser == null)
	{
		sessionUser = new UserEntity();
	}
	
	AnnounceUpdateEntity announce = (AnnounceUpdateEntity) request.getAttribute("announce");
	if (announce == null)
	{
		announce = new AnnounceUpdateEntity();
	}
%>

<div style='margin: 30px;'>
	<center>
	<h4>Pending Announcement Update</h4>
	</center>
</div>
<div class='subMenu'>
	<a href='dispatcher?section=announcements&action=create'>Create New Announcement</a> |
	<a href='dispatcher?section=announcements&action=list'>View All Announcements</a> |
	<a href='dispatcher?section=announcements&action=personal'>View My Announcements</a>
</div>	

<div style='margin-left: 20px; margin-top: 30px;'>
	<table id="formTable">
		<tr>
			<td width="120px">
				<label>Title</label>
			</td>
			<td>
				<input type='text' name='title' disabled="disabled" class="disabledTextBox" value='<%= announce.getTitle() %>'></input>
			</td>
		</tr>
		<tr>
			<td>
				<label>Start Date</label>
			</td>
			<td>
				<input type='text'  disabled="disabled" class="disabledTextBox" name='startdate' value='<%= announce.getStartDateString() %>'></input>
			</td>
		</tr>
		<tr>
			<td>
				<label>Expiry Date</label>
			</td>
			<td>
				<input type='text' disabled="disabled" class="disabledTextBox" name='expirydate' value='<%= announce.getExpireDateString() %>'></input>
			</td>
		</tr>
		<tr>
			<td valign='top'>
				<div style='margin-top:15px;'>
					<label>Content</label>
				</div>
			</td>
			<td>
				<div style='margin-top:15px;'>
					<div style='width:300px; height: 200px; background: #eee; padding: 10px; border: solid 1px #bbb'>
						<label style='width: 400px; height: 250px;'><%= announce.getDescription() %></label>
					</div>
				</div>
			</td>
		</tr>
	</table>
	<div style='height: 20px;'></div>
</div>

</body>

</html>