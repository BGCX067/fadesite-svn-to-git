/*
 * LoginCommand.java
 *
 * Created on 9 August 2003, 11:12
 */

package com.enterprise.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enterprise.dao.UserActivationDAO;
import com.enterprise.dao.UserDAO;
import com.enterprise.entity.*;

/**
 * This is the command that will be used for logging in users.
 * If logon is successful, the command should place a list of phonebook entries
 * in the request attriubutes.
 * @author  yunki
 */
public class AccountActivationCommand implements Command {
	/**
	 * The helper class to delegate all function calls to
	 */
	/** Creates a new instance of LoginCommand */
	public AccountActivationCommand() {
	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
	
		HttpSession session = request.getSession();

		try
		{
			String token = request.getParameter("token");
			
			UserActivationEntity ua = UserActivationDAO.GetUserActivation(token);
			
			int userId = ua.getUserId();
			
			UserEntity user = UserDAO.GetUser(userId);
			
			user.setIsActivated(true);
			
			if (UserDAO.UpdateUser(user))
			{
				UserActivationDAO.DeleteUserActivation(userId);
				return "/accounts/success.jsp";
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return "/accounts/activationerror.jsp";
	}
	
}
