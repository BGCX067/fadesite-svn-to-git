<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="java.util.*, com.enterprise.entity.*, com.enterprise.dao.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
String contentPage = "/messages/list.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param name="pageTitle" value="Message Inbox" />
	<jsp:param name="embeddedPageName" value='<%= contentPage %>'/>
</jsp:forward>
<%
}
%>

<div style="padding-left: 20px; padding-right: 20px; padding-top: 10px;">
	<h4>Message Inbox</h4>
	<% List<MessageEntity> messageList = (List<MessageEntity>) request.getAttribute("msgInbox"); %>	
	<div id="inbox" style='margin-top:10px;'>	
		<table id="dataTable" style='width: 100% !important;'
				cellspacing='0' cellpadding='5px'>
			<thead>
				<tr>
					<th></th>
					<th>Sender</th>
					<th>Subject</th>
					<th>Status</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<% if (messageList != null)
					{
						for (int i = 0 ; i < messageList.size() ; i++)
						{
					%>
			
				<tr>
					<td></td>
					<td><% UserEntity user = UserDAO.GetUser(messageList.get(i).getSenderID()); %>
						<%= user.getFirstname() + " " + user.getSurname() %></td>
					<td><%= messageList.get(i).getTitle() %></td>
					<td><%= messageList.get(i).getIsRead() == true ? "Read" : "Unread" %></td>
					<td><a href='<%="dispatcher?section=messages&action=view&mode=sendee&msgid=" + messageList.get(i).getMessageID() %>'>Open</a></td>
				</tr>
				
				<% 		}
					}%>
			</tbody>
		</table>
	</div>
</div>
<script type='text/javascript' language='javascript'>
	$(function() {
		$('#inbox #dataTable').dataTable({ "aoColumns": [
	        { "bSearchable": false, "bVisible": false },
	        null,
	        null,
	        null,
	        { "bSearchable": false, "sWidth": "15%" }]});
	});
</script>

<div style="padding-left: 20px; padding-right: 20px; margin-top: 100px;">
	<h4>Message Outbox</h4>
	<% List<MessageEntity> sentMessages = (List<MessageEntity>) request.getAttribute("msgOutbox"); %>	
	<div id="outbox" style='margin-top:10px;'>	
		<table id="dataTable" style='width: 100% !important;'
				cellspacing='0' cellpadding='5px'>
			<thead>
				<tr>
					<th></th>
					<th>Recipient</th>
					<th>Subject</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<% if (sentMessages != null)
					{
						for (int i = 0 ; i < sentMessages.size() ; i++)
						{
					%>
			
				<tr>
					<td></td>
					<td><% UserEntity user = UserDAO.GetUser(sentMessages.get(i).getSendeeID()); %>
						<%= user.getFirstname() + " " + user.getSurname() %></td>
					<td><%= sentMessages.get(i).getTitle() %></td>
					<td><a href='<%="dispatcher?section=messages&action=view&mode=sender&msgid=" + sentMessages.get(i).getMessageID() %>'>Open</a></td>
				</tr>
				
				<% 		}
					}%>
			</tbody>
		</table>
	</div>
</div>
<div style='height: 50px;'>
</div>
<script type='text/javascript' language='javascript'>
	$(function() {
		$('#outbox #dataTable').dataTable({ "aoColumns": [
	        { "bSearchable": false, "bVisible": false },
	        null,
	        null,
	        { "bSearchable": false, "sWidth": "15%" }]});
	});
</script>

</body>
</html>