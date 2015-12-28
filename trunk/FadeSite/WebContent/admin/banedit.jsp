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
String contentPage = "/admin/banedit.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param name="pageTitle" value="Edit User Ban" />
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
	<form method='POST' autocomplete='off' name="frmBan" action="dispatcher">
	
		<% UserBanEntity ban = (UserBanEntity) request.getAttribute("ban");%>
		<% UserEntity banUser = UserDAO.GetUser(ban.getUserID()); %>
		
		<input type="hidden" name="section" value="admin"/>
		<input type="hidden" name="action" value="editban"/>
		<input type="hidden" name="mode" value="save"/>
		<input type="hidden" name="banId" value='<%= ban.getUserBanID() %>'/>
	
		<h3>Edit User Ban</h3>
		<table>
			<tr>
				<td>
					<label>User</label>
				</td>
				<td>
					<input type='text' disabled='disabled' class="disabledTextBox" value='<%= banUser.getFirstname() + " " + banUser.getSurname() %>'></input>
				</td>
			</tr>
			<tr>
				<td>
					<label>Ban Start Date</label>
				</td>
				<td>
					<input type='text' class='datepicker' name='banstart' value='<%= ban.getStartDateString() %>'></input>
					<label class="reqText">*</label>
				</td>
			</tr>
			<tr>
				<td>
					<label>Ban End Date</label>
				</td>
				<td>
					<input type='text' class='datepicker' name='banend' value='<%= ban.getExpireDateString() %>'></input>
					<label class="reqText">*</label>
				</td>
			</tr>
			<tr>
				<td valign='top'>
					<label>Reason</label>
				</td>
				<td valign="top">
					<textarea style='width: 400px; height: 150px;' name="reason"><%= ban.getDescription() %></textarea>
					<label class="reqText">*</label>
				</td>
			</tr>
			<tr>
				<td colspan='2' align='right'>
					<div style='margin:10px;'>
						<label class="reqText" id="txtException">
							<% if (request.getAttribute("exception") != null)
								{%>
							<%= request.getAttribute("exception") %>
							<% } %>
						</label>
					</div>
				<% if (request.getAttribute("referrer") != null && request.getAttribute("referrer").toString().compareTo("search") == 0)
					{%>
						<input type='button' value='Cancel' onclick="window.location='<%= "dispatcher?section=search&action=user&id=" + banUser.getUserId() %>';"></input>
					<%}
					else
					{%>
					<input type='button' value='Back' onclick="window.location='dispatcher?section=admin&action=default'"></input>
					<% } %>
					<input type='button' value='Save' onclick="return CheckForm()"></input>
				</td>
			</tr>
		</table>
	</form>
</div>

</body>
<Script type="text/javascript">
	function CheckForm()
	{
		if (document.frmBan.banstart.value == "")
		{
			document.getElementById('txtException').innerHTML = "Start Date required";
		}
		else if (document.frmBan.banend.value == "")
		{
			document.getElementById('txtException').innerHTML = "Start Date required";
		}
		else if (document.frmBan.reason.value == "")
		{
			document.getElementById('txtException').innerHTML = "Reason required";
		}
		else
		{
			document.frmBan.submit();
		}
		return true;
	}

</Script>
</html>