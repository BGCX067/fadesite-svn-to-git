<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.enterprise.entity.*, com.enterprise.dao.*"
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
String contentPage = "/events/view.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param name="pageTitle" value="View Event" />
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
	
	EventEntity event = (EventEntity) request.getAttribute("event");
	if (event == null)
	{
		event = new EventEntity();
	}
%>
	

<div style='margin-left: 20px'>
		<h3>View Event</h3>
		<table id="formTable" style='float:left; margin-top: 50px;'>
			<tr>
				<td width="120px">
					<label>Title</label>
				</td>
				<td>
					<input type='text' name='title' class='disabledTextBox' disabled="disabled" value='<%= event.getTitle() %>'> </input>
				</td>
			</tr>
			<tr>
				<td>
					<label>Start Date</label>
				</td>
				<td>
					<input type='text' name='startdate' class='disabledTextBox' disabled="disabled" value='<%= event.getStartDateString() %>'></input>
				</td>
			</tr>
			<tr>
				<td>
					<label>End Date</label>
				</td>
				<td>
					<input type='text' name='enddate' class='disabledTextBox' disabled="disabled" value='<%= event.getExpireDateString() %>'></input>
				</td>
			</tr>
			<tr>
				<td>
					<label>Location</label>
				</td>
				<td>
					<input type='text' name='location' class='disabledTextBox' value='<%= event.getLocation() %>' disabled="disabled"></input>
				</td>
			</tr>
			<tr>
				<td valign='top'>
					<label>Description</label>
				</td>
				<td>
					<textarea name='description' disabled='disabled' class='disabledTextBox'><%= event.getDescription() %></textarea>
				</td>
			</tr>
			<tr>
				<td colspan='2' align='right'>
					<div style='margin-right: 100px;'>
						<div style='height:20px; margin: 10px;'>
							<label class="reqText" id="txtException">
							<% if (request.getAttribute("exception") != null)
								{%>
							<%= request.getAttribute("exception") %>
							<% } %>
							</label>
						</div>
						<input type='button' value='Back' onclick="window.location='dispatcher?section=events&action=list'"></input>
					</div>
				</td>
			</tr>
		</table>
		<div style='float:left'>
			<div id="map_canvas" style="margin-left: 20px; margin-top: 10px; width: 500px; height: 300px"></div>
		</div>
</div>

</body>

<script type="text/javascript">

var map = null;
var geocoder = null;

function initialize() {
  if (GBrowserIsCompatible()) {
    map = new GMap2(document.getElementById("map_canvas"));
    map.setCenter(new GLatLng(37.4419, -122.1419), 13);
    geocoder = new GClientGeocoder();
    showAddress('UNSW, Sydney, Australia');
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