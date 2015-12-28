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
String contentPage = "/announcements/update.jsp";

if (request.getParameter("fromMasterPage") == null)
{
%>
<jsp:forward page="../masterpage.jsp">
	<jsp:param name="pageTitle" value="Edit Announcement" />
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
	
	AnnounceUpdateEntity announce = (AnnounceUpdateEntity) request.getAttribute("announce");
	if (announce == null)
	{
		announce = new AnnounceUpdateEntity();
	}
%>
	

<div style='margin-left: 20px'>
	<form method='POST' autocomplete='off' name="frmAnnouncement" action='dispatcher'>
		<input type='hidden' name='section' value='announcements'></input>
		<input type='hidden' name='action' value='list'></input>
		<input type='hidden' name='mode' value='update'></input>
		<input type='hidden' name='announceId' value='<%= announce.getAnnounceID() %>'></input>
		<h3>Edit Announcement</h3>
		<table id="formTable">
			<tr>
				<td width="120px">
					<label>Title</label>
				</td>
				<td>
					<input type='text' name='title' value='<%= announce.getTitle() %>'></input>
					<label class="reqText">*</label>
				</td>
			</tr>
			<tr>
				<td>
					<label>Start Date</label>
				</td>
				<td>
					<input type='text' class="datepicker" name='startdate' value='<%= announce.getStartDateString() %>'></input>
					<label class="reqText">*</label>
				</td>
			</tr>
			<tr>
				<td>
					<label>Expiry Date</label>
				</td>
				<td>
					<input type='text' class="datepicker" name='expirydate' value='<%= announce.getExpireDateString() %>'></input>
					<label class="reqText">*</label>
				</td>
			</tr>
			<tr>
				<td valign='top'>
					<div style='margin-top:15px;'>
						<label>Content</label>
					</div>
				</td>
				<td>
					<div style='margin-top:15px;'>
						<textarea name='content' class="html"><%= announce.getDescription() %></textarea>
					</div>
					<input type="button" value="Preview" onclick="return previewHTML()"></input>
				</td>
			</tr>
			<tr>
				<td colspan='2'>
					<div style='margin-left: 10px; margin-top: 5px; background: white;'>
						<label style='background: white; color: #777'><i>&nbsp; Preview &nbsp;&nbsp;&nbsp;</i></label>
					</div>
					<div style='margin-bottom:5px; border: 1px solid #ccc; min-height: 20px; padding: 20px; margin-top: -10px;'>
						<label id="txtPreview"></label>
					</div>
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
					<% if (request.getAttribute("referrer") != null)
						{%>
					<input type='button' value='Back' onclick="window.location='dispatcher?section=announcements&action=personal'"></input>
					<% }
					else
					  {%>
					<input type='button' value='Back' onclick="window.location='dispatcher?section=announcements&action=list'"></input>
					<% } %>
					<input type='button' value='Save' onclick="return CheckForm()"></input>
					<% if (request.getAttribute("group") != null)
						{
						GroupEntity group = (GroupEntity) request.getAttribute("group");
						%>
						<input type='hidden' name='groupId' value='<%= group.getGroupID() %>'></input>
						<%} %>
						<% if (request.getAttribute("event") != null)
						{
							EventEntity event = (EventEntity) request.getAttribute("event");
						%>
						<input type='hidden' name='eventId' value='<%= event.getEventID() %>'></input>
						<%} %>
				</td>
			</tr>
		</table>
	</form>
	<div style='height: 20px;'></div>
</div>

</body>

<script type="text/javascript">
	var mySettings = {
	    nameSpace:       "html", // Useful to prevent multi-instances CSS conflict
	    onShiftEnter:    {keepDefault:false, replaceWith:'<br />\n'},
	    onCtrlEnter:     {keepDefault:false, openWith:'\n<p>', closeWith:'</p>\n'},
	    onTab:           {keepDefault:false, openWith:'     '},
	    markupSet:  [
	        {name:'Paragraph', openWith:'<p(!( class="[![Class]!]")!)>', closeWith:'</p>'  },
	        {separator:'---------------' },
	        {name:'Bold', key:'B', openWith:'<strong>', closeWith:'</strong>' },
	        {name:'Italic', key:'I', openWith:'<em>', closeWith:'</em>'  },
	        {name:'Stroke through', key:'S', openWith:'<del>', closeWith:'</del>' },
	        {separator:'---------------' },
	        {name:'Ul', openWith:'<ul>\n', closeWith:'</ul>\n' },
	        {name:'Ol', openWith:'<ol>\n', closeWith:'</ol>\n' },
	        {name:'Li', openWith:'<li>', closeWith:'</li>' },
	        {separator:'---------------' },
	        {name:'Picture', key:'P', replaceWith:'<img src="[![Source:!:http://]!]" alt="[![Alternative text]!]" />' },
	        {name:'Link', key:'L', openWith:'<a href="[![Link:!:http://]!]"(!( title="[![Title]!]")!)>', closeWith:'</a>', placeHolder:'Your text to link...' },
	        {separator:'---------------' },
	        {name:'Clean', replaceWith:function(h) { return h.selection.replace(/<(.*?)>/g, "") } }
	        //,
	        //{name:'Preview', call:'preview', className:'preview' }
	    ]
	};

	$(document).ready(function()	{
	   $('.html').markItUp(mySettings);
	});


	function previewHTML()
	{
		document.getElementById('txtPreview').innerHTML = document.frmAnnouncement.content.value;
		
		return true;
	}

	function CheckForm()
	{
		if (Trim(document.frmAnnouncement.title.value) == "")
		{
			document.getElementById('txtException').innerHTML = "Title Required.";
		}
		else if (Trim(document.frmAnnouncement.startdate.value) == "")
		{
			document.getElementById('txtException').innerHTML = "Start Date Required.";
		}
		else if (Trim(document.frmAnnouncement.expirydate.value) == "")
		{
			document.getElementById('txtException').innerHTML = "Expiry Date Required.";
		}
		else
		{ 
			document.frmAnnouncement.submit();
		}

		return true;
	}

</script>

</html>