package com.enterprise.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enterprise.dao.UserDAO;



/**
 * This command adds a new contact entry
 * @author $author 
 */
public class MessagesCreateCommand implements Command {
	/**
	 * The helper class to delegate all function calls to
	 */
	
	/** Creates a new instance of AddCommand */
	public MessagesCreateCommand() {

	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		response.addCookie(new Cookie("sendMsg", "true"));
		
		String StrId = request.getParameter("senduserid");
		if (StrId == null)
		{
			StrId = "";
		}

		try
		{
			int sendUserId = Integer.parseInt(StrId);
			request.setAttribute("senduser", UserDAO.GetUser(sendUserId));
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
		
		request.setAttribute("referrer", request.getParameter("referrer"));
		
		return "/messages/send.jsp";
	}
	
}