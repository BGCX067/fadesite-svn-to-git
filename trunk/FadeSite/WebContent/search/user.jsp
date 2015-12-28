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
String contentPage = "/search/user.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param value="User Profile" name="pageTitle"/>
	<jsp:param name="embeddedPageName" value='<%= contentPage %>'/>
</jsp:forward>
<%
}
%>

<% UserEntity user = (UserEntity) request.getAttribute("searchuser"); 

	if (user == null)
	{
		user = new UserEntity();
	}
	
	UserEntity userSession = (UserEntity) session.getAttribute("userEntity");
	if (userSession == null)
	{
		userSession = new UserEntity();
	}
%>

<center>
	<h3>User Profile</h3>
</center>
<div class="profileBox" style='width: 90%; margin-left:5%; height:350px;'>
	<table style='float:left; margin-top: 50px;'>
		<tr>
			<td width='120px'><label>Firstname</label></td>
			<td>
				<input type="text" disabled="disabled" class="disabledTextBox"
					value='<%= user.getFirstname() %>'>
				</input>
			</td>
		</tr>
		<tr>
			<td><label>Surname</label></td>
			<td>
				<input type="text" disabled="disabled" class="disabledTextBox"
					value='<%= user.getSurname() %>'>
				</input>
			</td>
		</tr>
		<tr>
			<td><label>User Type</label></td>
			<td>
			<% String userType = "";
				if (user.getUserTypeId() == 1)
				{
					userType = "Alumni";
				}
				else if (user.getUserTypeId() == 2)
				{
					userType = "CSE Staff";
				}
				else
				{
					userType = "Liaison";
				}
				%>
				<input type="text" disabled="disabled" class="disabledTextBox"
					value='<%= userType %>'>
				</input>
			</td>
		</tr>
		<tr>
			<td><label>Reference ID</label></td>
			<td>
				<input type="text" disabled="disabled" class="disabledTextBox"
					value='<%= user.getReferenceId() %>'>
				</input>
			</td>
		</tr>
		<tr>
			<td><label>Email</label></td>
			<td>
				<input type="text" disabled="disabled" class="disabledTextBox"
					value='<%= user.getEmailAddress() %>'>
				</input>
			</td>
		</tr>
		<tr>
			<td><label>Address</label></td>
			<td>
				<input type="text" disabled="disabled" class="disabledTextBox"
					value='<%= user.getAddress() %>'>
				</input>
			</td>
		</tr>
		<tr>
			<td><label>Date of Birth</label></td>
			<td>
				<input type="text" disabled="disabled" class="disabledTextBox" id="datePicker"
					value='<%= user.getDOBString() %>'>
				</input>
			</td>
		</tr>
		<tr>
			<td valign='top'><label>Gender</label></td>
			<td>
				<input type="radio" disabled="disabled" 
				   <%= user.getGender().compareTo("Male") == 0 ? "checked='checked'" : "" %>>
				</input> <label> Male </label>
				<div style='width:50%;'></div>
				<input type="radio" disabled="disabled" 
				   <%= user.getGender().compareTo("Female") == 0 ? "checked='checked'" : "" %>>
				</input> <label> Female </label>
			</td>
		</tr>
		<tr>
			<td colspan='2'>
				<div style='margin-left: 20px; margin-top:30px;'>
				<% List<ActivityEntity> list = ActivityDAO.ListActivityByUser(user.getUserId()); %>
				<% if (list != null && list.size() > 0)
				   {
				   	 %>
				   	 
				   	 <a href="<%= "dispatcher?section=search&action=activities&userId=" + user.getUserId() %>">View User's Activities</a>
				<% }%>
				<% List<UserDegreeEntity> degrees = UserDegreeDAO.ListDegreeByUser(user.getUserId()); %>
				<% if (degrees != null && degrees.size() > 0)
				   {
				   	 %>
				   	 
				   	 <a href="<%= "dispatcher?section=search&action=degrees&userId=" + user.getUserId() %>">View User's Degrees</a>
				<% }%>
				</div>
			</td>
		</tr>
	</table>
	<div style='float:left;'>
		<div id="map_canvas" style="margin-left: 15px; width: 480px; height: 300px"></div>
	</div>
</div>
<div class='profileBox' style='margin-top: 20px !important; text-align:center;' align='center'>
	<input type="button" value="Send User a Private Message" onclick="<%= "window.location='dispatcher?section=messages&action=create&senduserid=" + user.getUserId() + "&referrer=search" + "'"%>"></input>
	<input type="button" value="Invite User to Group" onclick="<%="window.location='dispatcher?section=search&action=invite&userId=" + user.getUserId() + "'"%>"/>
	<input type="button" value="Invite User to Event" onclick="<%="window.location='dispatcher?section=search&action=eventinvite&userId=" + user.getUserId() + "'"%>"/>
	<% if (userSession.getUserTypeId() == 3)
					{%>
	<% 
	UserBanEntity ban = UserBanDAO.getBanByUser(user.getUserId());
	if (ban != null)
		{%>
		<input type="button" value="Ban User" onclick="<%="window.location='dispatcher?section=admin&action=editban&banId=" + ban.getUserBanID() + "&referrer=search" + "'"%>"></input>
		<%}
	else
	{%>
	<input type="button" value="Ban User" onclick="<%= "window.location='dispatcher?section=admin&action=bancreate&banuserid=" + user.getUserId() + "&referrer=search" + "'"%>"></input>
	<% } %>
	<% } %>
</div>
<div class='profileBox' style='margin-top: 20px; !important; text-align:center; margin-bottom: 30px;' align='center'>
	<input type='button' value='Back' onclick="window.location='dispatcher?section=search&action=default'"></input>
</div>
</body>
<script type='text/javascript'>
	var map = null;
	var geocoder = null;

	function initialize() {
	  if (GBrowserIsCompatible()) {
	    map = new GMap2(document.getElementById("map_canvas"));
	    map.setCenter(new GLatLng(37.4419, -122.1419), 13);
	    geocoder = new GClientGeocoder();
	    showAddress('<%= user.getAddress() %>');
	  }
	}

	function showAddress(address) {
	  if (geocoder) {
	    geocoder.getLatLng(
	      address,
	      function(point) {
	        if (!point) {
//	          alert(address + " not found");
				showAddress('UNSW, Sydney, Australia');
	        } else {
	          map.setCenter(point, 13);
	          var marker = new GMarker(point);
	          map.addOverlay(marker);
	          marker.openInfoWindowHtml(address);
	        }
	      }
	    );
	  }
	}
</script>
</html>