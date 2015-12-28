package com.enterprise.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enterprise.dao.EventDAO;
import com.enterprise.dao.GroupDAO;


/**
 * This command adds a new contact entry
 * @author $author 
 */
public class AnnouncementsCreateCommand implements Command {
	/**
	 * The helper class to delegate all function calls to
	 */
	
	/** Creates a new instance of AddCommand */
	public AnnouncementsCreateCommand() {

	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

		HttpSession session = request.getSession();

		response.addCookie(new Cookie("createAnnouncement", "true"));
		
		if (request.getParameter("groupId") != null)
		{
			int groupId = Integer.parseInt(request.getParameter("groupId"));
			request.setAttribute("group", GroupDAO.GetGroup(groupId));
		}
		
		if (request.getParameter("eventId") != null)
		{
			int eventId = Integer.parseInt(request.getParameter("eventId"));
			request.setAttribute("event", EventDAO.getEvent(eventId));
		}
		
		return "/announcements/create.jsp";
	}
	
}
