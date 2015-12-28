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
public class SearchDegreesCommand implements Command {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

		HttpSession session = request.getSession();

		String strUserId = request.getParameter("userId");
		int userId = Integer.parseInt(strUserId);
		
		request.setAttribute("user", UserDAO.GetUser(userId));
		
		return "/search/degrees.jsp";
	}
	
}

