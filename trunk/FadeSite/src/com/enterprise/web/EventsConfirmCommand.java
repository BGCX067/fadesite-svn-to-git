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

public class EventsConfirmCommand implements Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		response.addCookie(new Cookie("eventConfirm", "true"));
		String strEventId = request.getParameter("eventId");
		int eventId = Integer.parseInt(strEventId);
		
		EventEntity event = EventDAO.getEvent(eventId);
		request.setAttribute("event", event);
		
		request.setAttribute("mode", request.getParameter("mode"));
		request.setAttribute("inviteId", request.getParameter("inviteId"));
		
		return "/events/confirm.jsp";
	}

}
