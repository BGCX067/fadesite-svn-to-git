package com.enterprise.web;

import java.io.IOException;

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
public class SearchEventInviteCommand implements Command {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserEntity userSession = (UserEntity) session.getAttribute("userEntity");
		
		String strUserId = request.getParameter("userId");
		int userId = Integer.parseInt(strUserId);
		
		if (request.getParameter("mode") != null && request.getParameter("mode").compareTo("save") == 0)
		{
			if (HTMLHelper.GetCookieValue(request.getCookies(), "createInvite", "false").compareTo("false") != 0)
			{
				String strEventId = request.getParameter("eventId");
				int eventId = Integer.parseInt(strEventId);
				
				EventInvitationEntity ei = new EventInvitationEntity();
				ei.setEventID(eventId);
				ei.setInviteeID(userId);
				ei.setInviterID(userSession.getUserId());
				
				EventInvitationDAO.CreateEventInvitation(ei);
				response.addCookie(new Cookie("createInvite", "false"));
				request.setAttribute("searchuser", UserDAO.GetUser(userId));
				return "/search/user.jsp";
			}
		}
		
		request.setAttribute("searchuser", UserDAO.GetUser(userId));
		
		response.addCookie(new Cookie("createInvite", "true"));
		
		return "/search/eventinvite.jsp";
	}
	
}

