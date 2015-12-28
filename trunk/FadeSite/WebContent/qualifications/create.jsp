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
String contentPage = "/qualifications/create.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param name="pageTitle" value="Register Qualification" />
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
%>
	

<div style='margin-left: 20px'>
	<form method='POST' autocomplete='off' name="frmQualification" action='dispatcher'>
		<input type='hidden' name='section' value='qualifications'></input>
		<input type='hidden' name='action' value='list'></input>
		<input type='hidden' name='mode' value='save'></input>
	
		<h3>Register User Qualification</h3>
		<table id="formTable">
			<tr>
				<td width="120px">
					<label>Degree</label>
				</td>
				<td>
					<select name='degree'>
					<%
					List<DegreeEntity> degrees = DegreeDAO.ListDegrees();
					for (int i = 0 ; i < degrees.size() ; i++)
					{%>
						<option value="<%= degrees.get(i).getDegreeID() %>"><%= degrees.get(i).getDegreeName() %></option>
					<%} %>
					</select>
					<label class="reqText">*</label>
				</td>
			</tr>
			<tr>
				<td>
					<label>Date Graduated</label>
				</td>
				<td>
					<input type='text' name='graduated' class='datepicker'></input>
					<label class="reqText">*</label>
				</td>
			</tr>
			
			<tr>
				<td colspan='2' align='right'>
					<div style='height:20px; margin: 10px;'>
						<label class="reqText" id="txtException">
						<% if (request.getAttribute("exception") != null)
								{%>
							<%= request.getAttribute("exception") %>
							<% } %>
						</label>
					</div>
					<input type='button' value='Back' onclick="window.location='dispatcher?section=qualifications&action=list'"></input>
					<input type='button' value='Register' onclick="return CheckForm()"></input>
				</td>
			</tr>
		</table>
	</form>
</div>

</body>

<script type="text/javascript">

	function CheckForm()
	{
		if (Trim(document.frmQualification.degree.value) == "")
		{
			document.getElementById('txtException').innerHTML = "Degree Required.";
		}
		else if (document.frmQualification.graduated.value == "")
		{
			document.getElementById('txtException').innerHTML = "Date Graduated Required.";
		}
		else
		{ 
			document.frmQualification.submit();
		}

		return true;
	}

</script>

</html>