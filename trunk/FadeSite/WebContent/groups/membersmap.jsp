<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.enterprise.entity.*, java.util.*, com.enterprise.dao.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAzr2EBOXUKnm_jVnk0OJI7xSosDVG8KKPE1-m51RBrvYughuyMxQ-i1QfUnH94QxWIa6N4U6MouMmBA"
            type="text/javascript"></script>
</head>
<body onload="initialize()" onunload="GUnload()">

<%
String contentPage = "/groups/membersmap.jsp";

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
<div style='margin: 30px;'>
	<a href='<%= "dispatcher?section=groups&action=members&groupId=" + group.getGroupID() %>'>Back</a>
</div>
<div id="map_canvas" style="width: 1000px; height: 600px;"></div>

  


  </body>
<script type="text/javascript">
<% List<UserGroupEntity> users = UserGroupDAO.listByGroup(group.getGroupID()); %>
    var map = null;
    var geocoder = null;

    function initialize() {
        if (GBrowserIsCompatible()) {
          map = new GMap2(document.getElementById("map_canvas"));
          geocoder = new GClientGeocoder();
          map.setUIToDefault();
          map.addControl(new GLargeMapControl());
          map.enableDoubleClickZoom();

          // Add 10 markers to the map at random locations
          var addresses = new Array( <% for (int i = 0 ; i < users.size() ; i++) { 
            	UserEntity suser = UserDAO.GetUser(users.get(i).getUserID());
              	if (suser.getLocation().compareTo("") != 0)
              	{%>
              		<% if (i > 0) {%> <%="," %> <% } %>
        		"<%= suser.getLocation() %>"
      			<%} } %>);

          
          
          for (var i = 0; i < addresses.length; i++) {
            showAddress(addresses[i]);
          }

          if (geocoder) {
              geocoder.getLatLng(
                "Australia",
                function(point) {
                    map.setCenter(point, 4);
                }
              );
            }
        }
      }

    function showAddress(address) {
      if (geocoder) {
        geocoder.getLatLng(
          address,
          function(point) {
            if (point) {
              var marker = new GMarker(point);
              map.addOverlay(marker);
            }
          }
        );
      }
    }
    </script>
</html>