<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.enterprise.web.helper.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<%= HTMLHelper.GetHTMLHeader() %>
</head>
<body>
	<img id="imgLoading" alt='' src='Styles/images/loading.gif' class='imageHidden'/>
	<div style='min-height: 10%;'>
	</div>
	<div id="registerBox">
		<form name="frmRegister" style='margin-left: 10%' action='dispatcher' autocomplete='off' method='POST'>
		
			<input type='hidden' name='section' value='account'/>
			<input type='hidden' name='action' value='doregister'/>
		
			<h3>Please fill in the details below:</h3>
			
			<table id="formTable">
					<tr>
						<th align="right" valign="top">Account Type:</th>
						<td>
								<input type="radio" name="usertype" value="alumni" checked="checked"/>
								<label>CSE Alumni</label>
								<div style='width:50%;'></div>
								<input type="radio" name="usertype" value="staff" disabled="disabled"/>
								<label class='disabledText'>CSE Staff</label>
						</td>		
					</tr>
					<tr>
						<th align="right">Username:</th>
						<td>
							<input type="text" name="username"/>
							<label class="reqText">*</label>
						</td>	
					</tr>
					<tr>
						<th align="right">First Name:</th>
						<td><input type="text" name="firstname"/>
						<label class="reqText">*</label></td>	
					</tr>
					<tr>
						<th align="right">Surname:</th> 
						<td><input type="text" name="surname"/>
						<label class="reqText">*</label></td>	
					</tr>	
					<tr>
						<th align="right">ID:</th> 
						<td>
							<input type="text" name="referenceId"
								OnKeyPress="EnsureNumeric()" maxLength='10'/>
							<label class="reqText">*</label>
						</td>		
					</tr>
					<tr>
						<th align="right">Email:</th> 
						<td>
							<input type="text" name="email"/>
							<label class="reqText">*</label>
						</td>
					</tr>	
					<tr>
						<th align="right">DOB:</th> 
						<td>
							<input id="datepicker" type="text" name="dob" />
							<label class="reqText">*</label>
						</td>	
					</tr>
					<tr>
						<th align="right" valign='top'>Gender:</th>
						<td>
								<input type="radio" name="gender" value="Male" checked="checked"/> Male
								<div style='width:50%;'></div>
								<input type="radio" name="gender" value="Female"/> Female
						</td>		
					</tr>
					<tr>
						<th align="right" valign="top">Password:</th> 
						<td>
							<input type="password" name="password"/>
							<label class="reqText">*</label>
						</td>
					</tr>
					<tr>
						<th align="right" valign="top">Confirm Password:</th> 
						<td>
							<input type="password" name="confirmpassword"/>
							<label class="reqText">*</label>
						</td>
					</tr>
					<tr>
						<th align="right" valign='top'>Address:</th>
						<td><input type="text" name="address" id="address"></input></td>
					</tr>
			</table>
			<div>
				<label class='exceptionText' id='txtException'>
					<%= request.getAttribute("exception") == null ?  "" : request.getAttribute("exception") %>
					<% request.setAttribute("exception", ""); %>
				</label>
			</div>
			<div style='margin-top:30px; margin-left: 28%;'>
					<input type="button" value="Back" onclick="window.location='dispatcher?section=account&action=goLogin'"/>
					<input type="button" value="Register" style="cursor:hand;"
						onclick="return CheckRegistrationForm(this);"></input>
			</div>		
		</form>
	</div>
</body>
<script type='text/javascript'>
	function CheckRegistrationForm(obj)
	{
		if (document.frmRegister.password.value != document.frmRegister.confirmpassword.value)
		{
			document.getElementById('txtException').innerHTML = 'Passwords do not match.';
			return false;
		}
		else if (Trim(document.frmRegister.username.value) == "")
		{
			document.getElementById('txtException').innerHTML = 'Username required.';
			return false;
		}
		else if (Trim(document.frmRegister.firstname.value) == "")
		{
			document.getElementById('txtException').innerHTML = 'Firstname required.';
			return false;
		}
		else if (Trim(document.frmRegister.surname.value) == "")
		{
			document.getElementById('txtException').innerHTML = 'Surname required.';
			return false;
		}
		else if (Trim(document.frmRegister.referenceId.value) == "")
		{
			document.getElementById('txtException').innerHTML = 'Reference ID required.';
			return false;
		}
		else if (Trim(document.frmRegister.email.value) == "")
		{
			document.getElementById('txtException').innerHTML = 'Email required.';
			return false;
		}
		else if (Trim(document.frmRegister.dob.value) == "")
		{
			document.getElementById('txtException').innerHTML = 'Date of Birth required.';
			return false;
		}
		else if (document.frmRegister.password.value == "")
		{
			document.getElementById('txtException').innerHTML = 'Password required.';
			return false;
		}
		
		document.getElementById('imgLoading').style.visibility='visible';
		this.value='Processing...';
		this.disabled=true;
		document.frmRegister.submit();
		$("input[type='text']").attr('disabled', true);
		$("input[type='text']").addClass('disabledTextBox');
		$("input[type='radio']").attr('disabled', true);
		$("input[type='radio']").addClass('disabledTextBox');
		$("input[type='password']").attr('disabled', true);
		$("input[type='password']").addClass('disabledTextBox');
		$("textarea").attr('disabled', true);
		$("textarea").addClass('disabledTextBox');
	}
</script>
</html>