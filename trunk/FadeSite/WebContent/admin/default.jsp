<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.enterprise.entity.*, com.enterprise.dao.*, java.util.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
String contentPage = "/admin/default.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param value="Admin Panel" name="pageTitle"/>
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
<div style='margin:30px'>
	<center>
		<h4>Admin Panel</h4>
	</center>
</div>
<div style='padding: 10px;'>

	<% ArrayList<UserBanEntity> banList = (ArrayList<UserBanEntity>) UserBanDAO.ListBansPost(); %>
	<h4>User Bans (<%= banList.size() %>)</h4>
	<% for (int i = 0 ; i < banList.size() ; i++)
	   {
		UserBanEntity ban = banList.get(i); %>
	   	<div style='margin:30px; border: 1px solid #ccc; padding: 10px; width: 45%; margin-left: 27%;'>
	   		<center>
		   	<table>
		   		<tr>
		   			<% UserEntity banUser = UserDAO.GetUser(ban.getUserID()); %>
					<td>User</td>
					<td width="50px"><div></div></td>
					<td><%= banUser.getFirstname() + " " + banUser.getSurname() %></td>
				</tr>
				<tr>
					<td>Ban Date</td>
					<td width="50px"><div></div></td>
					<td><%= ban.getStartDateString() %> till <%= ban.getExpireDateString() %></td>
				</tr>
				<tr>
					<td>Reason</td>
					<td></td>
					<td><%= ban.getDescription() %></td>
				</tr>
			</table>
			<div style='margin-top: 10px; font-size: 14px;'>
				<a href='<%= "dispatcher?section=admin&action=editban&banId=" + ban.getUserBanID() %>'>Edit</a> |
				<a href='<%= "dispatcher?section=admin&action=deleteban&banId=" + ban.getUserBanID() %>'>Delete</a>
			</div>
			</center>
		</div>
    <% }%>
    <br/>
	<% ArrayList<AnnounceEntity> anList = (ArrayList<AnnounceEntity>) AnnounceDAO.listPublicAnnounceAdmin();%>
	<h4>Pending Announcements (<%= anList.size() %>)</h4>
	<% for (int i = 0 ; i < anList.size() ; i++)
	   {
		AnnounceEntity aup = anList.get(i); %>
	   	<div style='margin:30px; border: 1px solid #ccc; padding: 10px; width: 45%; margin-left: 27%;'>
	   		<center>
		   	<table>
				<tr>
					<td>Announcement Date</td>
					<td width="50px"><div></div></td>
					<td><%= aup.getStartDateString() %> till <%= aup.getExpireDateString() %></td>
				</tr>
				<tr>
					<td>Title</td>
					<td></td>
					<td><%= aup.getTitle() %></td>
				</tr>
				<tr>
					<td>Content</td>
					<td></td>
					<td><%= aup.getDescription() %></td>
				</tr>
			</table>
			<div style='margin-top: 10px; font-size: 14px;'>
				<a href='<%= "dispatcher?section=announcements&action=admin&mode=approve&announceId=" + aup.getAnnounceID() %>'>Approve</a> |
				<a href='<%= "dispatcher?section=announcements&action=admin&mode=decline&announceId=" + aup.getAnnounceID() %>'>Decline</a>
			</div>
			</center>
		</div>
    <% }%>
    <br/>
	<% ArrayList<AnnounceUpdateEntity> aList = (ArrayList<AnnounceUpdateEntity>) AnnounceUpdateDAO.getAllAnnouncementUpdates();%>
	<h4>Pending Announcement Updates (<%= aList.size() %>)</h4>
	<% for (int i = 0 ; i < aList.size() ; i++)
	   {
	   	AnnounceUpdateEntity aup = aList.get(i);
	   	AnnounceEntity ann = AnnounceDAO.getAnnounce(aup.getAnnounceID());%>
	   	<% if (ann.getIsApproved())
	   	{%>
	   	<div style='margin:30px; border: 1px solid #ccc; padding: 10px; width: 66%; margin-left: 15%;'>
	   		<center>
		   	<table>
		   		<tr>
		   			<td></td>
					<td></td>
		   			<td><b>Proposed Update</b></td>
		   			<td></td>
		   			<td><b>Existing</b></td>
		   		</tr>
				<tr>
					<td>Announcement Date</td>
					<td width="50px"><div></div></td>
					<td><%= aup.getStartDateString() %> till <%= aup.getExpireDateString() %></td>
					<td width="100px;"><div></div></td>
					<td><%= ann.getStartDateString() %> till <%= ann.getExpireDateString() %></td>
				</tr>
				<tr>
					<td>Title</td>
					<td></td>
					<td><%= aup.getTitle() %></td>
					<td></td>
					<td><%= ann.getTitle() %></td>
				</tr>
				<tr>
					<td>Content</td>
					<td></td>
					<td><%= aup.getDescription() %></td>
					<td></td>
					<td><%= ann.getDescription() %></td>
				</tr>
			</table>
			<div style='margin-top: 10px; font-size: 14px;'>
				<a href='<%= "dispatcher?section=announcements&action=admin&mode=approve&announceId=" + ann.getAnnounceID() %>'>Approve</a> |
				<a href='<%= "dispatcher?section=announcements&action=admin&mode=decline&announceId=" + ann.getAnnounceID() %>'>Decline</a>
			</div>
			</center>
		</div>
		<%} %>
    <% }%>
    <br/>
    <% ArrayList<EventEntity> eventList = (ArrayList<EventEntity>) EventDAO.listPublicEventAdmin();%>
    <h4>Pending Events (<%= eventList.size() %>)</h4>
	<% for (int i = 0 ; i < eventList.size() ; i++)
	   {
		EventEntity event = eventList.get(i); %>
	   	<div style='margin:30px; border: 1px solid #ccc; padding: 10px; width: 45%; margin-left: 27%;'>
	   		<center>
		   	<table>
				<tr>
					<td>Event Date</td>
					<td width="50px"><div></div></td>
					<td><%= event.getStartDateString() %> till <%= event.getExpireDateString() %></td>
				</tr>
				<tr>
					<td>Title</td>
					<td></td>
					<td><%= event.getTitle() %></td>
				</tr>
				<tr>
					<td>Location</td>
					<td></td>
					<td><%= event.getLocation() %></td>
				</tr>
				<tr>
					<td>Description</td>
					<td></td>
					<td><%= event.getDescription() %></td>
				</tr>
			</table>
			<div style='margin-top: 10px; font-size: 14px;'>
				<a href='<%= "dispatcher?section=events&action=admin&mode=approve&eventId=" + event.getEventID()  %>'>Approve</a> |
				<a href='<%= "dispatcher?section=events&action=admin&mode=decline&eventId=" + event.getEventID()  %>'>Decline</a>
			</div>
			</center>
		</div>
    <% }%>
    <br/>
	<% ArrayList<EventUpdateEntity> euList = (ArrayList<EventUpdateEntity>) EventUpdateDAO.getAllEventUpdates();%>
	<h4>Pending Event Updates (<%= euList.size() %>)</h4>
	<% for (int i = 0 ; i < euList.size() ; i++)
	   {
		EventUpdateEntity eup = euList.get(i);
	   	EventEntity ev = EventDAO.getEvent(eup.getEventID());%>
	   	<% if (ev.getIsApproved())
	   	{%>
	   	<div style='margin:30px; border: 1px solid #ccc; padding: 10px; width: 66%; margin-left: 15%;'>
	   		<center>
		   	<table>
		   		<tr>
		   			<td></td>
					<td></td>
		   			<td><b>Proposed Update</b></td>
		   			<td></td>
		   			<td><b>Existing</b></td>
		   		</tr>
				<tr>
					<td>Event Date</td>
					<td width="50px"><div></div></td>
					<td><%= eup.getStartDateString() %> till <%= eup.getExpireDateString() %></td>
					<td width="100px;"><div></div></td>
					<td><%= ev.getStartDateString() %> till <%= ev.getExpireDateString() %></td>
				</tr>
				<tr>
					<td>Title</td>
					<td></td>
					<td><%= eup.getTitle() %></td>
					<td></td>
					<td><%= ev.getTitle() %></td>
				</tr>
				<tr>
					<td>Description</td>
					<td></td>
					<td><%= eup.getDescription() %></td>
					<td></td>
					<td><%= ev.getDescription() %></td>
				</tr>
				<tr>
					<td>Location</td>
					<td></td>
					<td><%= eup.getLocation() %></td>
					<td></td>
					<td><%= ev.getLocation() %></td>
				</tr>
			</table>
			<div style='margin-top: 10px; font-size: 14px;'>
				<a href='<%= "dispatcher?section=events&action=admin&mode=approve&eventId=" + ev.getEventID() %>'>Approve</a> |
				<a href='<%= "dispatcher?section=events&action=admin&mode=decline&eventId=" + ev.getEventID() %>'>Decline</a>
			</div>
			</center>
		</div>
		<%} %>
    <% }%>
	<br/>
	<% ArrayList<AnnounceEntity> ganList = (ArrayList<AnnounceEntity>) AnnounceDAO.listGroupAnnouceAdmin();%>
	<h4>Pending Group Announcements (<%= ganList.size() %>)</h4>
	<% for (int i = 0 ; i < ganList.size() ; i++)
	   {
		AnnounceEntity aup = ganList.get(i); %>
	   	<div style='margin:30px; border: 1px solid #ccc; padding: 10px; width: 45%; margin-left: 27%;'>
	   		<center>
		   	<table>
		   		<tr>
		   			<td>Group</td>
					<td width="50px"><div></div></td>
					<td><%= GroupAnnounceDAO.getGroupByAnnounceId(aup.getAnnounceID()).getName() %></td>
		   		</tr>
				<tr>
					<td>Announcement Date</td>
					<td width="50px"><div></div></td>
					<td><%= aup.getStartDateString() %> till <%= aup.getExpireDateString() %></td>
				</tr>
				<tr>
					<td>Title</td>
					<td></td>
					<td><%= aup.getTitle() %></td>
				</tr>
				<tr>
					<td>Content</td>
					<td></td>
					<td><%= aup.getDescription() %></td>
				</tr>
			</table>
			<div style='margin-top: 10px; font-size: 14px;'>
				<a href='<%= "dispatcher?section=announcements&action=admin&mode=approve&announceId=" + aup.getAnnounceID() %>'>Approve</a> |
				<a href='<%= "dispatcher?section=announcements&action=admin&mode=decline&announceId=" + aup.getAnnounceID() %>'>Decline</a>
			</div>
			</center>
		</div>
    <% }%>    
    <br/>
	<% ArrayList<AnnounceEntity> eanList = (ArrayList<AnnounceEntity>) AnnounceDAO.listEventAnnouceAdmin();%>
	<h4>Pending Event Announcements (<%= eanList.size() %>)</h4>
	<% for (int i = 0 ; i < eanList.size() ; i++)
	   {
		AnnounceEntity aup = eanList.get(i); %>
	   	<div style='margin:30px; border: 1px solid #ccc; padding: 10px; width: 45%; margin-left: 27%;'>
	   		<center>
		   	<table>
		   		<tr>
		   			<td>Event</td>
					<td width="50px"><div></div></td>
					<td><%= EventAnnounceDAO.getEventByAnnounceId(aup.getAnnounceID()).getTitle() %></td>
		   		</tr>
				<tr>
					<td>Announcement Date</td>
					<td width="50px"><div></div></td>
					<td><%= aup.getStartDateString() %> till <%= aup.getExpireDateString() %></td>
				</tr>
				<tr>
					<td>Title</td>
					<td></td>
					<td><%= aup.getTitle() %></td>
				</tr>
				<tr>
					<td>Content</td>
					<td></td>
					<td><%= aup.getDescription() %></td>
				</tr>
			</table>
			<div style='margin-top: 10px; font-size: 14px;'>
				<a href='<%= "dispatcher?section=announcements&action=admin&mode=approve&announceId=" + aup.getAnnounceID() %>'>Approve</a> |
				<a href='<%= "dispatcher?section=announcements&action=admin&mode=decline&announceId=" + aup.getAnnounceID() %>'>Decline</a>
			</div>
			</center>
		</div>
    <% }%>    
    

</div>
<div style='height:30px'></div>
</body>
</html>