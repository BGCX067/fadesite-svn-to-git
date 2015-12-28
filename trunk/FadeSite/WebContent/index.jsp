<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.enterprise.web.helper.*, com.enterprise.dao.*, com.enterprise.entity.*, java.util.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FadeSite</title>
<%= HTMLHelper.GetHTMLHeader() %>
</head>
<body style='background: lightblue;'>
	<div style='width: 100%;'>
		<div id='loginBox' style='float:right; margin:10px; z-index:2;'>
			<form action='dispatcher' method='POST' autocomplete='off'>
				<center><h4>Member Login</h4></center>
				<table cellpadding='0' cellspacing='0' style='width:100%; margin-top: 10px;'>
					<tr>
						<td style='text-align: right;'><label style='margin-right: 5px;'>Username:</label></td>
						<td><input type="text" name="username" style='width:100%'></input></td>
						<td></td>
					</tr>
					<tr>
						<td style='text-align: right;'><label style='margin-right: 5px;'>Password:</label></td>
						<td><input type="password" name="userpassword" style='width:100%'></input></td>
						<td style="text-align: center;">
							<input type='hidden' name='section' value='account'></input>
							<input type='hidden' name='action' value='dologin'></input>
							<input type="submit" value="Login" style='margin-left:5px;'></input>
						</td>
					</tr>
					<tr>
						<td colspan='3' style='text-align:center;'>
							<div style='margin-top: 5px; min-height: 15px; width: 100%;'>
								<label class='exceptionText'>
								<% if (request.getAttribute("Exception") != null)
									{%>
									<%= request.getAttribute("Exception") %>
								<% } %>
								<% request.setAttribute("Exception", null); %>
								</label>
							</div>
							<div style='margin-top:5px; font-size: 12px; float:left; margin-left: 10px;'>
								<a href='dispatcher?section=account&action=register'>Register New User</a>
								
							</div>
							<div style='margin-top:5px; font-size: 12px; float:right; margin-right: 10px;'>
								<a href='dispatcher?section=account&action=forgotpassword'>Forgot My Password</a>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div style='float: left; width: 80%; margin-top: -100px; margin-left: 20px;' >
			<center>
				<h1 style='margin-left: 10%;'>CSE Alumni Portal</h1>
			</center>
			<div>
				<% List<AnnounceEntity> announcements = AnnounceDAO.listPublicAnnounce(); %>
				<h3>Current Announcements (<%= announcements.size() %>)</h3>

				<% if (announcements != null)
					{
					%>
					<% for (int i = 0 ; i < announcements.size() ; i++)
					   {
					   		AnnounceEntity announce = (AnnounceEntity) announcements.get(i);%>
							<div style='width: 90%; margin: 30px; margin-bottom:75px;'>
								<div style='margin-left:30px;'>
									<h3><%= announce.getTitle() %></h3>
								</div>
								<hr style='margin-left: 50px; margin-right: 50px; border: 0px; border-top: solid 1px #fff;'></hr>
								<div style='margin-top: 15px; margin-bottom: 15px;'>
									<label><%= announce.getDescription() %></label>
								</div>
								<hr style='margin-left: 50px; margin-right: 50px; border: 0px; border-top: solid 1px #fff;'></hr>
								<div style='font-size: 13px; margin-top: 10px;'>
									<div style='float:left'>
										<% UserEntity updateUser = UserDAO.GetUser(announce.getUpdatedByUser()); %>
						<i>Last Updated <label><%= announce.getUpdateDateString() %> <%= "by " + updateUser.getFirstname() + " " + updateUser.getSurname() %></label></i>
									</div>
								 </div>
							</div>
							
					<% } %>
				<% } %>
			</div>
			<div>
				<% List<EventEntity> events = EventDAO.listPublicEvent(); %>
				<h3>Upcoming Events (<%= events.size() %>)</h3> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='dispatcher?section=search&action=pastevents'>View Past Events</a>

				<% if (events != null)
					{
					%>
					<% for (int i = 0 ; i < events.size() ; i++)
					   {
						EventEntity event = (EventEntity) events.get(i);
						String endString = "";
						
						if (event.getExpireDate().after(event.getStartDate()))
						{
							endString = " till " + event.getExpireDateString();
						}
						%>
							<div style='width: 90%; margin: 30px; margin-bottom:75px;'>
								<div style='margin-left:30px;'>
									<h3 style='margin-bottom:0px'><%= event.getTitle() %></h3>
									<h5 style='font-weight: normal; margin-top: 5px; margin-bottom:5px;'><%= event.getStartDateString() + endString + " &nbsp; - &nbsp; " + event.getLocation()%></h5>
								</div>
								<hr style='margin-left: 50px; margin-right: 50px; border: 0px; border-top: solid 1px #fff;'></hr>
								<div style='margin-top: 15px; margin-bottom: 15px;'>
									<label><%= event.getDescription() %></label>
								</div>
								<hr style='margin-left: 50px; margin-right: 50px; border: 0px; border-top: solid 1px #fff;'></hr>
							</div>
							
					<% } %>
				<% } %>
			</div>
		</div>
	</div>
</body>
</html>