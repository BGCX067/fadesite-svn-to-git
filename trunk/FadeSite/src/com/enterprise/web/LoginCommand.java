/*
 * LoginCommand.java
 *
 * Created on 9 August 2003, 11:12
 */

package com.enterprise.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enterprise.dao.*;
import com.enterprise.entity.*;

/**
 * This is the command that will be used for logging in users.
 * If logon is successful, the command should place a list of phonebook entries
 * in the request attriubutes.
 * @author  yunki
 */
public class LoginCommand implements Command {
	/**
	 * The helper class to delegate all function calls to
	 */
	/** Creates a new instance of LoginCommand */
	public LoginCommand() {
	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession();
		
		String username = request.getParameter("username");
		UserEntity user = UserDAO.GetUserByUsername(username);
		String password = request.getParameter("userpassword");
		
		if (password == null)
		{
			password = "";
		}
		
		Date date = new Date();
		UserBanEntity ban = null;
		
		if (user != null)
		{
			ban = UserBanDAO.getBanByUser(user.getUserId());
		}
		
		if (ban != null && ((date.after(ban.getStartDate()) && date.before(ban.getExpireDate()))))
		{
			
		}
		else if (user != null && user.getIsActivated() && user.getPassword().compareTo(password) == 0)
		{
			session.setAttribute("userEntity", user);
			UserTemporaryPasswordDAO.DeleteUserTemporaryPassword(user.getUserId());
			return "/members/default.jsp";
		}
		else if (user != null && user.getIsActivated())
		{
			UserTemporaryPasswordEntity up = UserTemporaryPasswordDAO.GetUserTemporaryPassword(user.getUserId());
			
			if (up != null && up.getPassword().compareTo(password) == 0)
			{
				session.setAttribute("userEntity", user);
				
				AccountPasswordChangeCommand command = new AccountPasswordChangeCommand();
				
				String output = "/members/default.jsp";
				
				try {
					output = command.execute(request, response);
					request.setAttribute("changemode", "lost");
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return output;
			}
		}
		else if (user != null)
		{
			request.setAttribute("Exception", "Account Locked.");
			return "/index.jsp";
		}
		
		request.setAttribute("Exception", "Invalid username or password.");
		return "/index.jsp";
	}
	
}
