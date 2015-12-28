<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.enterprise.entity.*, com.enterprise.dao.*, java.util.*"
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
String contentPage = "/accounts/details.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param value="Account Details" name="pageTitle"/>
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

<div id="detailsArea">
	<% boolean inEditMode = request.getAttribute("mode") != null; %>
	<form action='dispatcher' method='POST' name="frmDetails" autocomplete='off'>
		<input type='hidden' name='section' value='account'/>
		<input type='hidden' name='action' value='details'/>
		<input type='hidden' name='modeaction' value='save'/>
	
		<center>
		<h4>Account Details</h4>
		<h5 style='font-weight: normal; margin-top: 5px; '>Your personal account details.</h5>
		</center>
		
		<% List<GroupInvitationEntity> grpinvites = GroupInvitationDAO.listInviteByInvitee(user.getUserId());
		List<EventInvitationEntity> eventinvites = EventInvitationDAO.listInviteByInvitee(user.getUserId());
		%>
		
		<div class='subMenu'>
			<a href='dispatcher?section=qualifications&action=list'>My Qualifications</a> |
			<a href='dispatcher?section=activities&action=list'>My Activities</a> |
			<a href='dispatcher?section=messages&action=list'>My Messages</a> |
			<a href='dispatcher?section=account&action=groupinvites'>My Group Invites (<%= grpinvites == null || grpinvites.size() == 0 ? 0 : grpinvites.size()%>)</a> |
			<a href='dispatcher?section=account&action=eventinvites'>My Event Invites (<%= eventinvites == null || eventinvites.size() == 0 ? 0 : eventinvites.size()%>)</a>
		</div>
		
		<table id="formTable" style='margin-top: 100px;'>
			<tr>
				<td width='120px'><label>Firstname</label></td>
				<td width='250px'>
					<input type="text" <% if (!inEditMode) { %> disabled="disabled" class="disabledTextBox" <% } %>
						name='firstname'
						value='<%= user.getFirstname() %>'>
					</input>
					<% if (inEditMode) { %>
						<label class="reqText">*</label>
					<% } %>
				</td>
				<td rowspan='8'>
					<div id="map_canvas" style="margin-left: 20px; margin-top: -100px; width: 500px; height: 300px"></div>
				</td>
			</tr>
			<tr>
				<td><label>Surname</label></td>
				<td>
					<input type="text" <% if (!inEditMode) { %> disabled="disabled" class="disabledTextBox" <% } %>
						name='surname'
						value='<%= user.getSurname() %>'>
					</input>
					<% if (inEditMode) { %>
						<label class="reqText">*</label>
					<% } %>
				</td>
			</tr>
			<tr>
				<td><label>Reference ID</label></td>
				<td>
					<input type="text" <% if (!inEditMode) { %> disabled="disabled" class="disabledTextBox" <% } %>
						name='referenceId'
						value='<%= user.getReferenceId() %>'>
					</input>
					<% if (inEditMode) { %>
						<label class="reqText">*</label>
					<% } %>
				</td>
			</tr>
			<tr>
				<td><label>Email</label></td>
				<td>
					<input type="text" <% if (!inEditMode) { %> disabled="disabled" class="disabledTextBox" <% } %>
						name='email'
						value='<%= user.getEmailAddress() %>'>
					</input>
					<% if (inEditMode) { %>
						<label class="reqText">*</label>
					<% } %>
				</td>
			</tr>
			<tr>
				<td><label>Address</label></td>
				<td>
					<input type="text" <% if (!inEditMode) { %> disabled="disabled" class="disabledTextBox" <% } %>
						name='address'
						value='<%= user.getAddress() %>'>
					</input>
				</td>
			</tr>
			<tr>
				<td><label>Date of Birth</label></td>
				<td>
					<input type="text"  <% if (!inEditMode) { %> disabled="disabled" class="disabledTextBox" <% } %>
						id="datepicker" name='dob'
						value='<%= user.getDOBString() %>'>
					</input>
					<% if (inEditMode) { %>
						<label class="reqText">*</label>
					<% } %>
				</td>
			</tr>
			<tr>
				<td valign='top'><label>Gender</label></td>
				<td>
					<input type="radio"  <% if (!inEditMode) { %> disabled="disabled" <% } %> name='gender' value='Male'
					   <%= user.getGender().compareTo("Male") == 0 ? "checked='checked'" : "" %>>
					</input> <label> Male </label>
					<div style='width:50%;'></div>
					<input type="radio" <% if (!inEditMode) { %> disabled="disabled" <% } %> name='gender' value='Female'
					   <%= user.getGender().compareTo("Female") == 0 ? "checked='checked'" : "" %>>
					</input> <label> Female </label>
				</td>
			</tr>
			<tr>
				<td colspan='2' align='center'>
					<div style='margin:5px;' align='left'>
						<label style='color:red;' id="txtException">
						<% if (request.getAttribute("exceptions") != null) {%>
						<%= request.getAttribute("exceptions") %>
						<% } %>
						</label>
					</div>
					<div style='width: 100%; height: 15px;'></div>
					<% if (!inEditMode)
						{ %>
						<input type='button' value='Change Password' onclick='editPassword(this)'></input>						
						<input type='button' value='Edit Details' onclick='editForm(this)'></input>
					<% }
						else
					   {%>
					<input type='button' value='Cancel' onclick='cancelForm(this)'></input>
					<input type='button' value='Save' onclick='return CheckRegistrationForm()'></input>
					<% } %>
				</td>
			</tr>
		</table>
	</form>
</div>
<script type='text/javascript'>
	function editForm()
	{
		window.location="dispatcher?section=account&action=details&mode=edit"; 
	}

	function cancelForm()
	{
		window.location="dispatcher?section=account&action=details"; 
	}

	function editPassword()
	{
		window.location="dispatcher?section=account&action=password";
	}

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

	function CheckRegistrationForm()
	{
		if (Trim(document.frmDetails.firstname.value) == "")
		{
			document.getElementById('txtException').innerHTML = 'Firstname required.';
			return false;
		}
		else if (Trim(document.frmDetails.surname.value) == "")
		{
			document.getElementById('txtException').innerHTML = 'Surname required.';
			return false;
		}
		else if (Trim(document.frmDetails.referenceId.value) == "")
		{
			document.getElementById('txtException').innerHTML = 'Reference ID required.';
			return false;
		}
		else if (Trim(document.frmDetails.email.value) == "")
		{
			document.getElementById('txtException').innerHTML = 'Email required.';
			return false;
		}
		else if (Trim(document.frmDetails.dob.value) == "")
		{
			document.getElementById('txtException').innerHTML = 'Date of Birth required.';
			return false;
		}

		document.frmDetails.submit();
	}
</script>
</body>
</html>