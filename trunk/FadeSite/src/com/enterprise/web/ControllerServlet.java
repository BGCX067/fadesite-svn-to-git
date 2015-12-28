package com.enterprise.web;

import java.io.*;

import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.enterprise.dao.MessageDAO;
import com.enterprise.entity.UserEntity;

/**
 * Controller Servlet that accepts all client requests and performs the
 * lookup required to process the request. This class uses the command
 * design pattern to find the required <i>Command</i> class that will
 * process the request.
 * 
 * @author  $author
 * @version $Revision
 */
public class ControllerServlet extends HttpServlet {
	
	private Map commands;
	
	/** Initializes the servlet.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		commands = new HashMap();
		commands.put("static", new StaticCommand());
		commands.put("login", new LoginCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("messageslist", new MessagesListCommand());
		commands.put("messagescreate", new MessagesCreateCommand());
		commands.put("messagessend", new MessagesSendCommand());
		commands.put("messagesview", new MessagesViewCommand());
		commands.put("accountdetails", new AccountDetailsCommand());
		commands.put("register", new AccountRegisterCommand());
		commands.put("activate", new AccountActivationCommand());
		commands.put("retrieve", new AccountPasswordRetrievalCommand());
		commands.put("updatepassword", new AccountPasswordChangeCommand());
		commands.put("usegroupinvite", new AccountsUseGroupInviteCommand());
		commands.put("groupinvites", new AccountsGroupInvitesCommand());
		commands.put("useeventinvite", new AccountsUseEventInviteCommand());
		commands.put("eventinvites", new AccountsEventInvitesCommand());
		commands.put("groupslist", new GroupsListCommand());
		commands.put("groupscreate", new GroupsCreateCommand());
		commands.put("groupspersonal", new GroupsPersonalCommand());
		commands.put("groupsconfirm", new GroupsConfirmCommand());
		commands.put("groupsmembers", new GroupsMembersCommand());
		commands.put("groupsedit", new GroupsEditCommand());
		commands.put("groupshome", new GroupsHomeCommand());
		commands.put("groupsmembersmap", new GroupsMembersMapCommand());
		commands.put("announcementslist", new AnnouncementsListCommand());
		commands.put("announcementscreate", new AnnouncementsCreateCommand());
		commands.put("announcementsedit", new AnnouncementsEditCommand());
		commands.put("announcementspersonal", new AnnouncementsPersonalCommand());
		commands.put("announcementsviewupdate", new AnnouncementsViewUpdateCommand());
		commands.put("announcementsadmin", new AnnouncementsAdminCommand());
		commands.put("eventslist", new EventsListCommand());
		commands.put("eventscreate", new EventsCreateCommand());
		commands.put("eventspersonal", new EventsPersonalCommand());
		commands.put("eventsadmin", new EventsAdminCommand());
		commands.put("eventsview", new EventsViewCommand());
		commands.put("eventsviewupdate", new EventsViewUpdateCommand());
		commands.put("eventsedit", new EventsEditCommand());
		commands.put("eventsconfirm", new EventsConfirmCommand());
		commands.put("eventshome", new EventsHomeCommand());
		commands.put("eventsmembers", new EventsMembersCommand());
		commands.put("eventsmanage", new EventsManageCommand());
		commands.put("editban", new BanEditCommand());
		commands.put("deleteban", new BanDeleteCommand());
		commands.put("activitieslist", new ActivitiesListCommand());
		commands.put("activitiescreate", new ActivitiesCreateCommand());
		commands.put("activitiesview", new ActivitiesViewCommand());
		commands.put("activitiesdelete", new ActivitiesDeleteCommand());
		commands.put("activitiesedit", new ActivitiesEditCommand());
		commands.put("qualificationslist", new QualificationListCommand());
		commands.put("qualificationscreate", new QualificationCreateCommand());		
		commands.put("searchuser", new SearchUserCommand());
		commands.put("searchusersingle", new SearchUserSingleCommand());
		commands.put("searchactivity", new SearchActivityCommand());
		commands.put("searchactivities", new SearchActivitiesCommand());
		commands.put("searchinvite", new SearchInviteCommand());
		commands.put("searcheventinvite", new SearchEventInviteCommand());
		commands.put("searchdegrees", new SearchDegreesCommand());
		commands.put("bancreate", new BanCreateCommand());
		commands.put("PAGE_NOT_FOUND", new ErrorCommand());
	}

	
	/** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 * @param request servlet request
	 * @param response servlet response
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		Command cmd = resolveCommand(request, response);
		String next = cmd.execute(request, response);
		if (next.indexOf('.') < 0) {
			cmd = (Command) commands.get(next);
			next = cmd.execute(request, response);
			response.addHeader("Cache-Control","no-cache");
			response.addHeader("Expires","-1");
			response.addHeader("Pragma","no-cache");
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(next);
		dispatcher.forward(request, response);
	}
	
	private Command resolveCommand(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Command cmd = null;
		
		// Default start page
		if (request.getRequestURI().endsWith("/FadeSite/"))
		{
			request.setAttribute("redirect", "login.jsp");
			cmd = (Command) commands.get("static");
		}
		else if (request.getRequestURI().endsWith(".css"))
		{
			request.setAttribute("redirect", request.getRequestURI().replace("/FadeSite", ""));
			cmd = (Command) commands.get("static");
		}
		else
		{
			String section = request.getParameter("section");
			String action = request.getParameter("action");
			
			if (section == null)
			{
				section = "";
			}
			
			if (action == null)
			{
				action = "";
			}
			
			if (section.compareTo("account") == 0)
			{
				if (action.compareTo("register") == 0)
				{
				    request.setAttribute("redirect", "accounts/register.jsp");
					cmd = (Command) commands.get("static");
				}
				else if (action.compareTo("doregister") == 0)
				{
					cmd = (Command) commands.get("register");
				}				
				else if (action.compareTo("dologin") == 0)
				{
					cmd = (Command) commands.get("login");
				}
				else if (action.compareTo("logout") == 0)
				{
					cmd = (Command) commands.get("logout");
				}
				else if (action.compareTo("details") == 0)
				{
					cmd = (Command) commands.get("accountdetails");
				}
				else if (action.compareTo("activate") == 0)
				{
					cmd = (Command) commands.get("activate");
				}
				else if (action.compareTo("retrieve") == 0)
				{
					cmd = (Command) commands.get("retrieve");
				}
				else if (action.compareTo("goLogin") == 0)
				{
					request.setAttribute("redirect", "index.jsp");
					cmd = (Command) commands.get("static");
				}
				else if (action.compareTo("forgotpassword") == 0)
				{
					request.setAttribute("redirect", "accounts/forgotpassword.jsp");
					cmd = (Command) commands.get("static");
				}
				else if (action.compareTo("session") == 0)
				{
					request.setAttribute("redirect", "session.jsp");
					cmd = (Command) commands.get("static");
				}
				else if (action.compareTo("password") == 0)
				{
					cmd = (Command) commands.get("updatepassword");
				}
				else if (action.compareTo("usegroupinvite") == 0)
				{
					cmd = (Command) commands.get("usegroupinvite");
				}
				else if (action.compareTo("groupinvites") == 0)
				{
					cmd = (Command) commands.get("groupinvites");
				}
				else if (action.compareTo("useeventinvite") == 0)
				{
					cmd = (Command) commands.get("useeventinvite");
				}
				else if (action.compareTo("eventinvites") == 0)
				{
					cmd = (Command) commands.get("eventinvites");
				}
			}
			else if (section.compareTo("events") == 0 || section.compareTo("groups") == 0 ||
					 section.compareTo("activities") == 0 || section.compareTo("qualifications") == 0 ||
					 section.compareTo("announcements") == 0 || section.compareTo("messages") == 0)
			{
				cmd = (Command) commands.get(section + action);
			}
			else if (section.compareTo("admin") == 0)
			{
				if (action.compareTo("default") == 0)
				{
					request.setAttribute("redirect", "admin/default.jsp");
					cmd = (Command) commands.get("static");
				}
				else if (action.compareTo("bancreate") == 0)
				{
					cmd = (Command) commands.get("bancreate");
				}
				else if (action.compareTo("editban") == 0)
				{
					cmd = (Command) commands.get("editban");
				}
				else if (action.compareTo("deleteban") == 0)
				{
					cmd = (Command) commands.get("deleteban");
				}
			}
			else if (section.compareTo("search") == 0)
			{
				if (action.compareTo("default") == 0)
				{
					cmd = (Command) commands.get("searchuser");
				}
				else if (action.compareTo("user") == 0)
				{
					cmd = (Command) commands.get("searchusersingle");
				}
				else if (action.compareTo("activity") == 0)
				{
					cmd = (Command) commands.get("searchactivity");
				}
				else if (action.compareTo("activities") == 0)
				{
					cmd = (Command) commands.get("searchactivities");
				}
				else if (action.compareTo("invite") == 0)
				{
					cmd = (Command) commands.get("searchinvite");
				}
				else if (action.compareTo("eventinvite") == 0)
				{
					cmd = (Command) commands.get("searcheventinvite");
				}
				else if (action.compareTo("degrees") == 0)
				{
					cmd = (Command) commands.get("searchdegrees");
				}
				else if (action.compareTo("pastevents") == 0)
				{
					request.setAttribute("redirect", "event.jsp");
					cmd = (Command) commands.get("static");
				}
			}
			
			
			if (cmd == null)
			{
				cmd = (Command) commands.get(section);
			}
			
			if (cmd == null) {
				cmd = (Command) commands.get("PAGE_NOT_FOUND");
			}
		}
		
		return cmd;
	}
	
	/** Handles the HTTP <code>GET</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		processRequest(request, response);
	}
	
	/** Handles the HTTP <code>POST</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		processRequest(request, response);
	}
	
	/** Returns a short description of the servlet.
	 */
	public String getServletInfo() {
		return "This servlet implements a command pattern for a phonebook application";
	}
	
}
