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
String contentPage = "/messages/send.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param name="pageTitle" value="Send Message" />
	<jsp:param name="embeddedPageName" value='<%= contentPage %>'/>
</jsp:forward>
<%
}
%>

<form name='frmSend' autocomplete='off' method='POST' action='dispatcher'>

	<input type='hidden' name='section' value='messages'/>
	<input type='hidden' name='action' value='send'/>

	<% UserEntity senduser = (UserEntity) request.getAttribute("senduser");
		if (senduser == null)
		{
			senduser = new UserEntity();
		}
		else
		{
		%>
		
		<input type="hidden" name="senduserid" value="<%= senduser.getUserId() %>"></input>
		
	<% } %>
	
	<div id='sendPMArea'>
		<h4>Create New Private Message</h4>
	
		<table>
			<tr>
				<td><label>To</label></td>
				<td><input type='text' disabled="disabled" class="disabledTextBox" value="<%= senduser.getFirstname() + " " + senduser.getSurname() %>"></input></td>
			</tr>
			<tr>
				<td><label>Subject</label></td>
				<td><input type='text' name='subject'></input></td>
			</tr>
			<tr>
				<td valign='top'><label>Body</label></td>
				<td><textarea name='body'></textarea></td>
			</tr>
			<tr>
				<td style='text-align:right;' colspan='2'>
					<% if (request.getAttribute("referrer") != null && request.getAttribute("referrer").toString().compareTo("search") == 0)
					{%>
						<input type='button' value='Cancel' onclick="window.location='<%= "dispatcher?section=search&action=user&id=" + senduser.getUserId() %>';"></input>
					<%}
					else
					{%>
					<input type='button' value='Cancel' onclick="window.location='dispatcher?section=messages&action=list';"></input>
					<%} %>
					<input type='button' value='Send' onclick="document.frmSend.submit();"></input>
				</td>
			</tr>
		</table>
	</div>
</form>

</body>
</html>
