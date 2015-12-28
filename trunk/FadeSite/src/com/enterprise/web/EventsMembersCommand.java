package com.enterprise.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enterprise.web.helper.HTMLHelper;
import com.enterprise.dao.*;
import com.enterprise.entity.*;


public class EventsMembersCommand implements Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserEntity userSession = (UserEntity) session.getAttribute("userEntity");
		String strEventId = request.getParameter("eventId");
		int eventId = Integer.parseInt(strEventId);
		
		EventEntity event = EventDAO.getEvent(eventId);
		
		request.setAttribute("event", event);
		
		return "/events/members.jsp";
		
	}

}
