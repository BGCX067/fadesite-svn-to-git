<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="java.util.*, com.enterprise.entity.*, com.enterprise.dao.*"
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
String contentPage = "/events/confirm.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param name="pageTitle" value="Events" />
	<jsp:param name="embeddedPageName" value='<%= contentPage %>'/>
</jsp:forward>
<%
}
%>

<div style='margin:30px;'>
	<center>
	<h4>Events</h4>
	</center>
</div>
<div style='margin:10px'>
	
	<% EventEntity event = (EventEntity)request.getAttribute("event"); %>
	
	<div style='width: 80%; margin-left: 10%'>
	<table style='margin-bottom: 30px; float: left; margin-top: 100px;'>
		<tr>
			<td width="100px">Title
			</td>
			<td><%= event.getTitle() %>
			</td>
		</tr>
		<tr>
			<td>Date
			</td>
			<td><%= event.getStartDateString() %> <% if (event.getExpireDate().after(event.getStartDate())) { %> <%= "till " + event.getExpireDateString() %> <% } %>
			</td>
		</tr>
		<tr>
			<td>Location
			</td>
			<td><%= event.getLocation() %>
			</td>
		</tr>
		<tr>
			<td>Description
			</td>
			<td><%= event.getDescription() %>
			</td>
		</tr>
	</table>
	<div style='float:left'>
		<div id="map_canvas" style="margin-left: 20px; margin-top: 10px; width: 500px; height: 300px"></div>
	</div>
	</div>
	<%
		String text = "";
	
		if (request.getAttribute("mode") != null)
		{
			if (((String)request.getAttribute("mode")).compareTo("join") == 0)
			{
				text = "Join";
			}
			else if (((String)request.getAttribute("mode")).compareTo("leave") == 0)
			{
				text = "Leave";
			}
		}%>
		<div style='height: 350px;'></div>
	<div style='margin-bottom:15px; text-align:center; width: 100%; ' align='center'>
	<center>
	<%= text + " this Event?"%><br/>
	<input type="button" value="Cancel" onclick="window.location='dispatcher?section=events&action=list'"></input>
	<input type="button" value='<%= text %>' onclick="return submit()"></input>
	</center>
	</div>
</div>
</body>
<%

String inviteString = request.getAttribute("inviteId") != null ? "&inviteId=" + request.getAttribute("inviteId").toString() : "";
%>

<script type="text/javascript">
var map = null;
var geocoder = null;

function initialize() {
  if (GBrowserIsCompatible()) {
    map = new GMap2(document.getElementById("map_canvas"));
    map.setCenter(new GLatLng(37.4419, -122.1419), 13);
    geocoder = new GClientGeocoder();
    showAddress("<%= event.getLocation() %>");
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

function submit()
{
	window.location = "<%= "dispatcher?section=events&action=list&mode=" + request.getAttribute("mode").toString() + "&eventId=" + event.getEventID() %>";
	return true;
		
}

</script>
</html>