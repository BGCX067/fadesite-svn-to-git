<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.enterprise.web.helper.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Password Retrieval</title>
<%= HTMLHelper.GetHTMLHeader() %>
</head>
<body>
	<div style='min-height: 33%;'>
	</div>
	<div class="infoBox">
		<p>Please enter your email address and a temporary password will be sent to your email.</p>
		
		<form name='frmRetrieve' method='POST' action='dispatcher?section=account&action=retrieve' autocomplete='off'>
			<input type="text" style='width: 250px; margin-right:3px;' name="emailAddress" id="email"></input><label class="reqText">*</label>
			<input type="button" value="Retrieve" style="margin-left:10px;" onclick="SendForm()"/>
		</form>
		<label class="reqText" id="txtMsg" style='margin-top:10px;'></label>
		<p>Click <a href='dispatcher?section=account&action=goLogin'>here</a> to return to login.</p>
	</div>
</body>
<script type="text/javascript">
	function SendForm()
	{
		if (document.getElementById('email').value == "")
		{
			document.getElementById('txtMsg').innerHTML = "Email required.";
			return false;
		}
		document.frmRetrieve.submit();
	}
</script>
</html>