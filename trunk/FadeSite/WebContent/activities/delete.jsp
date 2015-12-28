<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.enterprise.dao.*, com.enterprise.entity.*, java.util.*"
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
String contentPage = "/activities/delete.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param name="pageTitle" value="Delete Activity" />
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

  <% ActivityEntity activity = (ActivityEntity)request.getAttribute("activity");%>

	<div style='margin:30px;'>
		<center>
		<h4>Delete Activity</h4>
		</center>
	</div>
	<div style="margin: 20px; height: 60%">
  	
  	<table style='float:left; margin-top: 100px;'>
  		<tr>
  			<td width="120px">Name:</td>
  			<td><%= activity.getName() %></td>
  		</tr>
  		<tr>
  			<td>Position:</td>
  			<td><%= activity.getPosition() %></td>
  		</tr>
  		<tr>
  			<td>Project</td>
  			<td><%= ""  %></td>
  		</tr>
  		<tr>
  			<td>Organisation:</td>
  			<td><%= activity.getOrganisation() %></td>
  		</tr>
  		<tr>
  			<td>Location:</td>
  			<td><%= activity.getLocation() %></td>
  		</tr>
  		<tr>
  			<td>Period:</td>
  			<td><%= activity.getStartDate() %>  <% if (activity.getEndDate().compareTo(activity.getStartDate()) != 0) { %><%= " to " + activity.getEndDate() %> <% } %></td>
  		</tr>
  		<tr>
  			<td>Description:</td>
  			<td><%= activity.getDescription() %></td>
  		</tr>
  </table>
  <div style='float:left'>
  	<div id="map_canvas" style="margin-left: 20px; margin-top: 0px; width: 500px; height: 300px"></div>
  </div>
	
	
</div>
<div class='subMenu' style='width: 90%; float: none;'>
	<center>
		<form method='POST' action='dispatcher'>
			<label>Confirm Deleting this Activity?</label><br/><br/>
			<input type='hidden' name='section' value='activities'></input>
			<input type='hidden' name='action' value='list'></input>
			<input type='hidden' name='mode' value='delete'></input>
			<input type='hidden' name='activityId' value='<%= activity.getActivityID() %>'></input>
			<input type='button' value='Cancel' onclick="window.location='dispatcher?section=activities&action=list'"></input>
			<input type='submit' value='Confirm'></input>
		</form>
	</center>
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
    showAddress('<%= activity.getLocation() %>');
  }
}

function showAddress(address) {
  if (geocoder) {
    geocoder.getLatLng(
      address,
      function(point) {
        if (!point) {
          //alert(address + " not found");
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