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


/**
 * This command adds a new contact entry
 * @author $author 
 */
public class GroupsEditCommand implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserEntity userSession = (UserEntity) session.getAttribute("userEntity");
		
		if (userSession == null)
		{
			userSession = new UserEntity();
		}
		
		String strGroupId = request.getParameter("groupId");
		int groupId = Integer.parseInt(strGroupId);
		
		GroupEntity group = GroupDAO.GetGroup(groupId);
		
		if (request.getParameter("mode") != null && request.getParameter("mode").compareTo("save") == 0)
		{
			if (HTMLHelper.GetCookieValue(request.getCookies(), "editGroup", "false").compareTo("false") != 0)
			{
				String name = request.getParameter("name");
				String description = request.getParameter("description");
				
				group.setDescription(description);
				group.setName(name);
				
				GroupDAO.UpdateGroup(group);
				
				response.addCookie(new Cookie("editGroup", "false"));
				return "/groups/list.jsp";
			}
		}
		
		response.addCookie(new Cookie("editGroup", "true"));
		request.setAttribute("group", group);
		
		return "/groups/edit.jsp";
	}
	
}
