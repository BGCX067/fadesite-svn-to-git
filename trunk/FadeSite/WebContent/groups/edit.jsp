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
String contentPage = "/groups/edit.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param name="pageTitle" value="Edit Group" />
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
	
	GroupEntity group = (GroupEntity) request.getAttribute("group");
%>
	

<div style='margin-left: 20px'>
	<form method='POST' autocomplete='off' name="frmGroup" action='dispatcher'>
		<input type='hidden' name='section' value='groups'></input>
		<input type='hidden' name='action' value='edit'></input>
		<input type='hidden' name='mode' value='save'></input>
		<input type='hidden' name='groupId' value='<%= group.getGroupID() %>'></input>
	
		<h3>Edit User Group</h3>
		<table id="formTable">
			<tr>
				<td width="120px">
					<label>Name</label>
				</td>
				<td>
					<input type='text' name='name' value='<%= group.getName() %>'></input>
					<label class="reqText">*</label>
				</td>
			</tr>
			<tr>
				<td valign='top'>
					<label>Description</label>
				</td>
				<td>
					<textarea name='description' style='width: 200px; height: 100px;'><%= group.getDescription() %></textarea>
					<label class="reqText">*</label>
				</td>
			</tr>
			<tr>
				<td colspan='2' align='right'>
					<div style='height:20px; margin: 10px;'>
						<label class="reqText" id="txtException"></label>
					</div>
					<input type='button' value='Back' onclick="window.location='dispatcher?section=groups&action=list'"></input>
					<input type='button' value='Save' onclick="return CheckForm()"></input>
				</td>
			</tr>
		</table>
	</form>
</div>

</body>

<script type="text/javascript">

	function CheckForm()
	{
		if (Trim(document.frmGroup.name.value) == "")
		{
			document.getElementById('txtException').innerHTML = "Name Required.";
		}
		else if (Trim(document.frmGroup.description.value) == "")
		{
			document.getElementById('txtException').innerHTML = "Description Required.";
		}
		else
		{ 
			document.frmGroup.submit();
		}

		return true;
	}

</script>

</html>