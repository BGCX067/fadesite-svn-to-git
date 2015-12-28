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
String contentPage = "/activities/create.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param name="pageTitle" value="Create Activity" />
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
%>
	

<div style='margin-left: 20px'>
	<form method='POST' autocomplete='off' name="frmActivity" action='dispatcher'>
		<input type='hidden' name='section' value='activities'></input>
		<input type='hidden' name='action' value='list'></input>
		<input type='hidden' name='mode' value='save'></input>
	
		<h3>Create User Activity</h3>
		<table id="formTable" style='float:left; margin-top: 30px;'>
			<tr>
				<td width="120px">
					<label>Name</label>
				</td>
				<td>
					<input type='text' name='name'></input>
				</td>
			</tr>
			<tr>
				<td>
					<label>Position</label>
				</td>
				<td>
					<input type='text' name='position'></input>
					<label class="reqText">*</label>
				</td>
			</tr>
			<tr>
				<td>
					<label>Project</label>
				</td>
				<td>
					<input type='text' name='project'></input>
				</td>
			</tr>
			<tr>
				<td>
					<label>Organization</label>
				</td>
				<td>
					<input type='text' name='organization'></input>
					<label class="reqText">*</label>
				</td>
			</tr>
			<tr>
				<td>
					<label>Location</label>
				</td>
				<td>
					<input type='text' name='location'></input>
					<label class="reqText">*</label>
					<input type='button' value='Preview' style='margin-left: 10px;'
						onclick="return showAddress(document.frmActivity.location.value)"></input>
				</td>
			</tr>
			<tr>
				<td>
					<label>Start Date</label>
				</td>
				<td>
					<input type='text' class='datepicker' name='startdate'></input>
					<label class="reqText">*</label>
				</td>
			</tr>
			<tr>
				<td>
					<label>End Date</label>
				</td>
				<td>
					<input type='text' class='datepicker' name='enddate'></input>
				</td>
			</tr>
			<tr>
				<td valign='top'>
					<label>Description</label>
				</td>
				<td>
					<textarea name='description' style='width: 200px; height: 100px;'></textarea>
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
						<input type='button' value='Back' onclick="window.location='dispatcher?section=activities&action=list'"></input>
						<input type='button' value='Create' onclick="return CheckForm()"></input>
					</div>
				</td>
			</tr>
		</table>
		<div style='float:left'>
			<div id="map_canvas" style="margin-left: 20px; margin-top: 10px; width: 500px; height: 300px"></div>
		</div>
	</form>
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

	function CheckForm()
	{
		if (Trim(document.frmActivity.position.value) == "")
		{
			document.getElementById('txtException').innerHTML = "Position Required.";
		}
		else if (Trim(document.frmActivity.organization.value) == "")
		{
			document.getElementById('txtException').innerHTML = "Organization Required.";
		}
		else if (Trim(document.frmActivity.location.value) == "")
		{
			document.getElementById('txtException').innerHTML = "Location Required.";
		}
		else if (document.frmActivity.startdate.value == "")
		{
			document.getElementById('txtException').innerHTML = "Start Date Required.";
		}
		else
		{ 
			document.frmActivity.submit();
		}

		return true;
	}

</script>

</html>