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
String contentPage = "/messages/view.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param name="pageTitle" value="View Message" />
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

	MessageEntity message = (MessageEntity) request.getAttribute("message");
	if (message == null)
	{
		message = new MessageEntity();
	}
%>
	

<div id='sendPMArea'>
	<h4>Private Message</h4>
	
	<div style='height: 30px;'></div>

	<% UserEntity user = UserDAO.GetUser(message.getSenderID()); %>
	<table>
		<tr height="30px">
			<td width="100px">
			<% if (request.getAttribute("mode") != null && request.getAttribute("mode").toString().compareTo("sender") == 0)
 				{%>
				<label style='color:gray;'>To</label>
			 <% }
		    	else
		    	{%>
		    	<label style='color:gray;'>From</label>
			 <% } %>
			</td>
			<td><input type='text' disabled="disabled" class='disabledTextBox' value='<%= user.getFirstname() + " " + user.getSurname() %>'></input></td>
		</tr>
		<tr height="30px">
			<td><label style='color:gray;'>Subject</label></td>
			<td><input type='text' disabled="disabled" class='disabledTextBox' value='<%= message.getTitle() %>'></input></td>
		</tr>
		<tr height="30px">
			<td valign='top' style='color:gray;'><label>Body</label></td>
			<td><textarea disabled='disabled' class='disabledTextBox'><%= message.getContents() %></textarea></td>
		</tr>
		<tr>
			<td style='text-align:right;' colspan='2'>
				<div style='height: 30px;'></div>
				<input type='button' value='Back' onclick="window.location='dispatcher?section=messages&action=list'"></input>
				
				<form action='dispatcher' method='POST' style='float:right; margin-left:5px;' name='frmDelete'>
					<input type='hidden' name='mode' value='<%= request.getAttribute("mode") %>'></input>
					<input type='hidden' name='msgId' value='<%= message.getMessageID() %>'></input>
					<input type='hidden' name='section' value='messages'></input>
					<input type='hidden' name='action' value='list'></input>
					<input type='hidden' name='msgAction' value='delete'></input>	
					<input type='button' value='Delete Message' onclick="return confirmBox()"></input>
				</form>
				
				<% if (request.getAttribute("mode") != null && request.getAttribute("mode").toString().compareTo("sendee") == 0)
  					{%>
  					<form action='dispatcher' method='POST' style='float:right; margin-left:5px;'>
						<input type='hidden' name='msgId' value='<%= message.getMessageID() %>'></input>
						<input type='hidden' name='section' value='messages'></input>
						<input type='hidden' name='action' value='list'></input>
						<input type='hidden' name='msgAction' value='unread'></input>	
						<input type='submit' value='Mark as Unread'></input>
					</form>
				<% } %>
			</td>
		</tr>
	</table>
</div>

</body>
<script type='text/javascript'>
	function confirmBox()
	{
		if (confirm("Delete Message?"))
		{
			document.frmDelete.submit();
			return true;
		}
		return false;
	}
</script>
</html>