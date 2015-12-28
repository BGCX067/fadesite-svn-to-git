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

import com.enterprise.dao.*;
import com.enterprise.entity.*;
import com.enterprise.web.helper.HTMLHelper;



/**
 * This command adds a new contact entry
 * @author $author 
 */
public class EventsListCommand implements Command {
	/**
	 * The helper class to delegate all function calls to
	 */
	
	/** Creates a new instance of AddCommand */
	public EventsListCommand() {

	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserEntity user = (UserEntity) session.getAttribute("userEntity");

		if (user != null && request.getParameter("mode") != null && request.getParameter("mode").compareTo("save") == 0)
		{
			if (HTMLHelper.GetCookieValue(request.getCookies(), "createEvent", "false").compareTo("false") != 0)
			{
				String title = request.getParameter("title");
				String description = request.getParameter("description");
				String strStartDate = request.getParameter("startdate");
				String strEndDate = request.getParameter("enddate");
				String location = request.getParameter("location");
				
				SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
				
				Date startDate = null;
				Date endDate = null;
				
				try {
					startDate = df.parse(strStartDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					endDate = df.parse(strEndDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if (endDate == null)
				{
					endDate = startDate;
				}
				else if (endDate != null && startDate != null && endDate.before(startDate))
				{
					request.setAttribute("exception", "Invalid Date Range");
					return "/events/create.jsp";
				}
				
				EventEntity event = new EventEntity();
				event.setDescription(description);
				event.setTitle(title);
				event.setLocation(location);
				event.setStartDate(startDate);
				event.setExpireDate(endDate);
				event.setCreatedByUser(user.getUserId());
				event.setUpdatedByUser(user.getUserId());
				
				if (user.getUserTypeId() == 3)
				{
					event.setIsApproved(true);
				}
				
				EventDAO.CreateEvent(event);
				
				response.addCookie(new Cookie("createEvent", "false"));
			}
		}
		else if (request.getParameter("mode") != null && (request.getParameter("mode").compareTo("edit") == 0 || request.getParameter("mode").compareTo("update") == 0))
		{
			if (HTMLHelper.GetCookieValue(request.getCookies(), "editEvent", "false").compareTo("false") != 0)
			{
				String mode = request.getParameter("mode");
				
				SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
				
				String title = request.getParameter("title");
				String description = request.getParameter("description");
				String strStartDate = request.getParameter("startdate");
				String strEndDate = request.getParameter("enddate");
				String location = request.getParameter("location");
				String strEventId = request.getParameter("eventId");
				int eventID = Integer.parseInt(strEventId);
				
				Date startDate = null;
				Date expiryDate = null;
				
				try {
					startDate = df.parse(strStartDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
				
				try {
					expiryDate = df.parse(strEndDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
				
				if (expiryDate != null && startDate != null && expiryDate.before(startDate))
				{
					if (mode.compareTo("edit") == 0)
					{
						request.setAttribute("event", EventDAO.getEvent(eventID));
						request.setAttribute("exception", "Invalid Date Range.");
						return "/announcements/edit.jsp";
					}
					else
					{
						request.setAttribute("event", EventUpdateDAO.getUpdateByEvent(eventID));
						request.setAttribute("exception", "Invalid Date Range.");
						return "/announcements/update.jsp";
					}
				}
				
				if (user.getUserTypeId() == 3)
				{
					EventEntity event = EventDAO.getEvent(eventID);
					event.setStartDate(startDate);
					event.setDescription(description);
					event.setTitle(title);
					event.setLocation(location);
					event.setExpireDate(expiryDate);
					event.setUpdatedByUser(user.getUserId());
					event.setUpdateDate(new Date());
					EventDAO.UpdateEvent(event);
				}
				else
				{
					EventUpdateEntity event = new EventUpdateEntity();
					event.setEventID(eventID);
					event.setStartDate(startDate);
					event.setDescription(description);
					event.setTitle(title);
					event.setLocation(location);
					event.setExpireDate(expiryDate);
					event.setUpdatedByUser(user.getUserId());
					event.setUpdateDate(new Date());
					
					if (mode.compareTo("edit") == 0)
					{
						EventUpdateDAO.CreateEventUpdate(event);
					}
					else
					{
						event.setEventUpdateID(EventUpdateDAO.getUpdateByEvent(eventID).getEventUpdateID());
						EventUpdateDAO.updateEventUpdate(event);
					}
				}
				
				// destroy cookie
				response.addCookie(new Cookie("editEvent", "false"));
			}
		}
		else if (request.getParameter("mode") != null && request.getParameter("mode").compareTo("leave") == 0)
		{
			if (HTMLHelper.GetCookieValue(request.getCookies(), "eventConfirm", "false").compareTo("false") != 0)
			{
				String strEventId = request.getParameter("eventId");
				int eventId = Integer.parseInt(strEventId);
				
				EventEntity event = EventDAO.getEvent(eventId);
				
				UserEventEntity ev = UserEventDAO.getUserEvent(user.getUserId(), event.getEventID());
				ev.setIsDeleted(true);
				UserEventDAO.updateUserEvent(ev);
				
				response.addCookie(new Cookie("eventConfirm", "false"));
			}
		}
		else if (request.getParameter("mode") != null && request.getParameter("mode").compareTo("join") == 0)
		{
			if (HTMLHelper.GetCookieValue(request.getCookies(), "eventConfirm", "false").compareTo("false") != 0)
			{
				String strEventId = request.getParameter("eventId");
				int eventId = Integer.parseInt(strEventId);
				
				EventEntity event = EventDAO.getEvent(eventId);
				
				UserEventEntity ue = new UserEventEntity();
				ue.setEventID(event.getEventID());
				ue.setUserID(user.getUserId());
				
				UserEventDAO.createUserEvent(ue);
				
				
				response.addCookie(new Cookie("eventConfirm", "false"));
			}
		}
		
		return "/events/list.jsp";
	}
	
}
