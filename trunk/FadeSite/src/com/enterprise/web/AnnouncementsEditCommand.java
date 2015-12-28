package com.enterprise.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enterprise.dao.*;
import com.enterprise.entity.*;


/**
 * This command adds a new contact entry
 * @author $author 
 */
public class AnnouncementsEditCommand implements Command {
	/**
	 * The helper class to delegate all function calls to
	 */
	
	/** Creates a new instance of AddCommand */
	public AnnouncementsEditCommand() {

	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

		HttpSession session = request.getSession();
		
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
		
		String strAnnouncementId = request.getParameter("announceId");
		if (strAnnouncementId != null)
		{
			int announceId = Integer.parseInt(strAnnouncementId);
			
			AnnounceUpdateEntity update = AnnounceUpdateDAO.getUpdateByAnnounce(announceId);
			
			if (update == null)
			{
				AnnounceEntity announce = AnnounceDAO.getAnnounce(announceId);
				
				request.setAttribute("announce", announce);
				
				response.addCookie(new Cookie("editAnnouncement", "true"));
				
				request.setAttribute("referrer", request.getParameter("referrer"));
				
				return "/announcements/edit.jsp";
			}
			else
			{
				request.setAttribute("announce", update);
				
				response.addCookie(new Cookie("editAnnouncement", "true"));
				
				request.setAttribute("referrer", request.getParameter("referrer"));
				
				return "/announcements/update.jsp";
			}
			
		}
		
		return "/announcements/list.jsp";
	}
	
}
