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


public class GroupsMembersCommand implements Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserEntity userSession = (UserEntity) session.getAttribute("userEntity");
		
		String strGroupId = request.getParameter("groupId");
		int groupId = Integer.parseInt(strGroupId);
		
		request.setAttribute("group", GroupDAO.GetGroup(groupId));
		
		return "/groups/members.jsp";
		
	}

}
