package com.enterprise.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enterprise.web.helper.HTMLHelper;
import com.enterprise.dao.*;
import com.enterprise.entity.*;


/**
 * This command adds a new contact entry
 * @author $author 
 */
public class EventsAdminCommand implements Command {
	/**
	 * The helper class to delegate all function calls to
	 */
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserEntity user = (UserEntity) session.getAttribute("userEntity");

		String strEventId = request.getParameter("eventId");
		int eventId = Integer.parseInt(strEventId);
		EventEntity event = EventDAO.getEvent(eventId);
		EventUpdateEntity update = EventUpdateDAO.getUpdateByEvent(eventId);
		
		if (request.getParameter("mode") != null && request.getParameter("mode").compareTo("approve") == 0)
		{
			if (update != null)
			{
				event.setStartDate(update.getStartDate());
				event.setExpireDate(update.getExpireDate());
				event.setTitle(update.getTitle());
				event.setDescription(update.getDescription());
				event.setUpdateDate(update.getUpdateDate());
				event.setLocation(update.getLocation());
				event.setUpdateDate(update.getUpdateDate());
				event.setIsApproved(true);
				EventDAO.UpdateEvent(event);
				
				update.setIsApproved(true);
				update.setIsDeleted(true);
				
				EventUpdateDAO.updateEventUpdate(update);
			}
			else
			{
				event.setIsApproved(true);
				EventDAO.UpdateEvent(event);
			}
		}
		else if (request.getParameter("mode") != null && request.getParameter("mode").compareTo("decline") == 0)
		{
			if (update != null)
			{
				update.setIsDeleted(true);
				EventUpdateDAO.updateEventUpdate(update);
			}
			else
			{
				event.setIsDeleted(true);
				EventDAO.UpdateEvent(event);
			}
		}
		
		return "/admin/default.jsp";
	}
	
}
