<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.enterprise.entity.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
String contentPage = "/accounts/password.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param value="Change Password" name="pageTitle"/>
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
	<% boolean inEditMode = request.getAttribute("mode") != null && request.getParameter("mode").compareTo("edit") == 0; %>
	<form name="frmPassword" action='dispatcher?section=account&action=password&mode=save' method='POST' onsubmit='return CheckPassword()'>
		<center>
		<h4>Change Account Password</h4>
		<h5 style='font-weight: normal; margin-top: 5px; '>Your personal account details.</h5>
		</center>
		<div style='width: 100%;'></div>
		
		<table id="formTable" style='margin-top: 20px;'>
			<tr <% if (request.getAttribute("changemode") != null && request.getAttribute("changemode").toString().compareTo("lost") == 0) { %> <%= " style='display:none' " %>   <% } %>>
				<td width='120px'><label>Old Password</label></td>
				<td width='150px'>
					<input type='password' name='oldpassword' <% if (request.getAttribute("changemode") != null && request.getAttribute("changemode").toString().compareTo("lost") == 0) { %> <%= "value='boo'" %>   <% } %>></input>
				</td>
				<td><label class='reqText'>*</label></td>
			</tr>
			<tr>
				<td><label>New Password</label></td>
				<td>
					<input type='password' name='newpassword'></input>
				</td>
				<td><label class='reqText'>*</label></td>
			</tr>
			<tr>
				<td><label>Confirm Password</label></td>
				<td>
					<input type='password' name='confirmpassword'></input>
				</td>
				<td><label class='reqText'>*</label></td>
			</tr>
			<tr>
				<td colspan='2' align='center'>
					<div style='width: 100%; height: 25px; margin-top: 10px;'>
						<label class='reqText' id='txtException'>
							<% if (request.getAttribute("exception") != null)
								{%>
								<%= request.getAttribute("exception") %>	
							<%} %>
						</label>
					</div>
					<% if (request.getAttribute("changemode") != null && request.getAttribute("changemode").toString().compareTo("lost") == 0) { %> 
						<input type='hidden' name='changemode' value='lost'/>
					<% } %>
					<input type='button' value='Cancel' onclick='cancelForm(this)'></input>
					<input type='submit' value='Save'></input>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
<script type='text/javascript'>
	function CheckPassword()
	{
		if (document.frmPassword.oldpassword.value == "")
		{
			document.getElementById('txtException').innerHTML = 'Old Password required.';
			return false;
		}
		else if (document.frmPassword.newpassword.value == "")
		{
			document.getElementById('txtException').innerHTML = 'New Password required.';
			return false;

		}
		else if (document.frmPassword.confirmpassword.value != document.frmPassword.newpassword.value)
		{
			document.getElementById('txtException').innerHTML = 'Passwords do not match..';
			return false;
		} 
		return true;
	}	

	function cancelForm(obj)
	{
		window.location='dispatcher?section=account&action=details';
	}
</script>
</html>