package com.enterprise.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enterprise.dao.*;
import com.enterprise.entity.*;



/**
 * This command adds a new contact entry
 * @author $author 
 */
public class SearchActivityCommand implements Command {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

		HttpSession session = request.getSession();

		String strActivityId = request.getParameter("activityId");
		int activityId = Integer.parseInt(strActivityId);
		
		ActivityEntity activity = ActivityDAO.getActivity(activityId);
		
		request.setAttribute("activity", activity);
		request.setAttribute("user", UserDAO.GetUser(activity.getUserID()));
		
		return "/search/activity.jsp";
	}
	
}

