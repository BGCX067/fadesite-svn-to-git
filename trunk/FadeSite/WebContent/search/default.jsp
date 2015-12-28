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
String contentPage = "/search/default.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param value="Search Panel" name="pageTitle"/>
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
		<h4>User List</h4>
	</center>
</div>


<form autocomplete='off' method='POST' name='frmSearch'
	action='dispatcher?section=search&action=default' onsubmit='return checkFields()'>
	<div style='font-size:14px;'>
	<center>
	Search for alumni with the following degree:
	<% List<DegreeEntity> degrees = DegreeDAO.ListDegrees(); %>
	<select name='sdegree'>
	<% for (int i = 0 ; i < degrees.size() ; i++) 
	{ %>
		<option value='<%= degrees.get(i).getDegreeID() %>'><%= degrees.get(i).getDegreeName()%></option>
	<% } %>
	</select><br/>
	Who graduated after
	<input type='text' class='datepicker' name='sstartdate'></input>
	<label class='reqText'>*</label>
	&nbsp;and before
	<input type='text' class='datepicker' name='senddate'></input>
	<label class='reqText'>*</label>&nbsp;
	<input type='submit' value='Search'></input>
	</center>
	</div>
	<script type='text/javascript'>
		function checkFields()
		{
			if (document.frmSearch.sstartdate.value == "")
			{
				alert('One or more fields missing');
				return false;
			}
			else if (document.frmSearch.senddate.value == "")
			{
				alert('One or more fields missing');
				return false;
			}
			return true;
		}	
	</script>
</form>


<div id="searchPanel">
	<div>
		<table id="dataTable" style='width: 100% !important; margin-top: 10px; margin-bottom: 10px;'
			cellspacing='0' cellpadding='5px'>
			<thead>
			<tr>
				<th></th>
				<th>Firstname</th>
				<th>Surname</th>
				<th>Email Address</th>
				<th>Reference ID</th>
				<th></th>
			</tr>
			</thead>
			<tbody>
			
			<% ArrayList<UserEntity> users = (ArrayList) request.getAttribute("userList"); %>
			
			<% if (users != null)
				{%>
				<% for (int i = 0 ; i < users.size() ; i++)
					{%>
					<tr>
						<td><%= users.get(i).getUserId() %></td>
						<td><%= users.get(i).getFirstname() %></td>
						<td><%= users.get(i).getSurname() %></td>
						<td><%= users.get(i).getEmailAddress() %></td>
						<td><%= users.get(i).getReferenceId() %></td>
						<td><a href='<%= "dispatcher?section=search&action=user&id=" + users.get(i).getUserId() %>'>View User Profile</a></td>
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
	        { "bSearchable": false, "bVisible": false },
	        null,
	        null,
	        null,
	        null,
	        { "bSearchable": false, "sWidth": "15%" }]});
	});
</script>

</body>
</html>