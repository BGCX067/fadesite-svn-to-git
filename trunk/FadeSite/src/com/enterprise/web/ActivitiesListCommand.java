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
public class ActivitiesListCommand implements Command {
	/**
	 * The helper class to delegate all function calls to
	 */
	
	/** Creates a new instance of AddCommand */
	public ActivitiesListCommand() {

	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserEntity user = (UserEntity) session.getAttribute("userEntity");

		if (user != null && request.getParameter("mode") != null && request.getParameter("mode").compareTo("save") == 0)
		{
			if (HTMLHelper.GetCookieValue(request.getCookies(), "createActivity", "false").compareTo("false") != 0)
			{
				String name = request.getParameter("name");
				String position = request.getParameter("position");
				String project = request.getParameter("project");
				String organization = request.getParameter("organization");
				String location = request.getParameter("location");
				String strStartDate = request.getParameter("startdate");
				String strEndDate = request.getParameter("enddate");
				String description = request.getParameter("description");
				
				if (name == null)
				{
					name = "";
				}
				
				if (position == null)
				{
					position = "";
				}
				
				if (project == null)
				{
					project = "";
				}
				
				if (organization == null)
				{
					organization = "";
				}
				
				if (location == null)
				{
					location = "";
				}
				
				if (strStartDate == null)
				{
					strStartDate = "";
				}
				
				if (strEndDate == null)
				{
					strEndDate = "";
				}
				
				if (description == null)
				{
					description = "";
				}
				
				SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
				
				Date startDate = null;
				
				try {
					startDate = df.parse(strStartDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Date endDate = null;
				
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
				else if (startDate != null && endDate != null && endDate.before(startDate))
				{
					request.setAttribute("exception", "Date Range Invalid.");
					return "/activities/create.jsp";
				}
				
				ActivityEntity activity = new ActivityEntity();
				activity.setDescription(description);
				activity.setStartDate(startDate);
				activity.setEndDate(endDate);
				activity.setOrganisation(organization);
				activity.setPosition(position);
				activity.setLocation(location);
				activity.setUserID(user.getUserId());
				activity.setName(name);
				activity.setProject(project);
				
				ActivityDAO.CreateActivity(activity);
				response.addCookie(new Cookie("createActivity", "false"));
			}
		}
		else if (user != null && request.getParameter("mode") != null && request.getParameter("mode").compareTo("delete") == 0)
		{
			if (HTMLHelper.GetCookieValue(request.getCookies(), "deleteActivity", "false").compareTo("false") != 0)
			{
				String strActivity = request.getParameter("activityId");
				int activityID = Integer.parseInt(strActivity);
				ActivityEntity activity = ActivityDAO.getActivity(activityID);
				activity.setIsDeleted(true);
				ActivityDAO.UpdateActivity(activity);
				
				response.addCookie(new Cookie("deleteActivity", "false"));
			}
		}
		return "/activities/list.jsp";
	}
	
}
