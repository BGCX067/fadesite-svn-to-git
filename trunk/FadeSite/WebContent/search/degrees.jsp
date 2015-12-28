<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.enterprise.entity.*, java.util.*, com.enterprise.dao.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
String contentPage = "/search/degrees.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param name="pageTitle" value="View Qualifications" />
	<jsp:param name="embeddedPageName" value='<%= contentPage %>'/>
</jsp:forward>
<%
}
%>

<% UserEntity user = (UserEntity) request.getAttribute("user"); 

	if (user == null)
	{
		user = new UserEntity();
	}
%>
<div style="height: 80%;">
	<div style='margin:30px;'>
		<center>
		<h4><%= user.getFirstname() + " " + user.getSurname() %>'s Qualifications</h4>
		</center>
	</div>
	<div id="searchPanel">
	<div>
		<table id="dataTable" style='width: 100% !important; margin-top: 10px; margin-bottom: 10px;'
			cellspacing='0' cellpadding='5px'>
			<thead>
			<tr>
				<th>Degree</th>
				<th>Graduated</th>
			</tr>
			</thead>
			<tbody>
			
			<% List<UserDegreeEntity> degrees = UserDegreeDAO.ListDegreeByUser(user.getUserId()); %>
			
			<% if (degrees != null)
				{%>
				<% for (int i = 0 ; i < degrees.size() ; i++)
					{%>
					<tr>
						<td><%= DegreeDAO.GetDegree(degrees.get(i).getDegreeID()).getDegreeName() %></td>
						<td><%= degrees.get(i).getGraduatingDate() %></td>
					</tr>
				<% } %>
			<% } %>
			</tbody>
		</table>
	</div>
</div>

<script type='text/javascript' language='javascript'>
	$(function() {
		$('#dataTable').dataTable({ "aoColumns": [
	        null,
	        null]});
	});
</script>
</div>
<div class='subMenu'>
<a href="<%= "dispatcher?section=search&action=user&id=" + user.getUserId() %>">Back</a>
</div>
</body>
</html>