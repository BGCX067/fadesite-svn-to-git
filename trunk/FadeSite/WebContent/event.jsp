<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.enterprise.dao.*, com.enterprise.entity.*, java.util.*, com.enterprise.web.helper.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Past Events</title>
<%= HTMLHelper.GetHTMLHeader() %>
</head>
<body style='background:lightblue;'>
	<div style='margin: 30px'>
		<center><h3>Past Events</h3></center>
		<a href='dispatcher?section=account&action=goLogin'>Back</a>
	</div>
	

	<% List<EventEntity> events = EventDAO.listPublicPastEvents(); %>
	
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
		
</body>
</html>