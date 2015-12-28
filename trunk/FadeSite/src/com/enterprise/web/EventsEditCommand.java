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
public class EventsEditCommand implements Command {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		String strEventId = request.getParameter("eventId");
		if (strEventId != null)
		{
			int eventId = Integer.parseInt(strEventId);
			
			EventUpdateEntity update = EventUpdateDAO.getUpdateByEvent(eventId);
			
			if (update == null)
			{
				EventEntity event = EventDAO.getEvent(eventId);
				
				request.setAttribute("event", event);
				
				response.addCookie(new Cookie("editEvent", "true"));
				
				request.setAttribute("referrer", request.getParameter("referrer"));
				
				return "/events/edit.jsp";
			}
			else
			{
				request.setAttribute("event", update);
				
				response.addCookie(new Cookie("editEvent", "true"));
				
				request.setAttribute("referrer", request.getParameter("referrer"));
				
				return "/events/update.jsp";
			}
			
		}
		
		return "/events/list.jsp";
	}
	
}
