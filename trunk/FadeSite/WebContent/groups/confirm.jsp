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
String contentPage = "/groups/confirm.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param name="pageTitle" value="Groups" />
	<jsp:param name="embeddedPageName" value='<%= contentPage %>'/>
</jsp:forward>
<%
}
%>

<div style='margin:30px;'>
	<center>
	<h4>Groups</h4>
	</center>
</div>
<div style='margin:10px'>
	
	<% GroupEntity group = (GroupEntity)request.getAttribute("group"); %>
	
	<center>
	<table style='margin-bottom: 30px'>
		<tr>
			<td width="100px">Name
			</td>
			<td><%= group.getName() %>
			</td>
		</tr>
		<tr>
			<td>Description
			</td>
			<td><%= group.getDescription() %>
			</td>
		</tr>
	</table>
	
	<%
		String text = "";
	
		if (request.getAttribute("mode") != null)
		{
			if (((String)request.getAttribute("mode")).compareTo("join") == 0)
			{
				text = "Join";
			}
			else if (((String)request.getAttribute("mode")).compareTo("leave") == 0)
			{
				text = "Leave";
			}
		}%>
		
	
	<div style='margin-bottom:15px;'><%= text + " this Group?"%></div>
	<input type="button" value="Cancel" onclick="window.location='dispatcher?section=groups&action=list'"></input>
	<input type="button" value='<%= text %>' onclick="return submit()"></input>
	</center>
</div>
</body>
<%

String inviteString = request.getAttribute("inviteId") != null ? "&inviteId=" + request.getAttribute("inviteId").toString() : "";
%>

<script type="text/javascript">
	function submit()
	{
		window.location = "<%= "dispatcher?section=groups&action=list&mode=" + request.getAttribute("mode").toString() + "&groupId=" + group.getGroupID() + inviteString %>";
		return true;
			
	}

</script>
</html>