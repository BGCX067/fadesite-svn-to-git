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

import com.enterprise.dao.UserDAO;
import com.enterprise.dao.UserTemporaryPasswordDAO;
import com.enterprise.entity.UserEntity;

/**
 * This is the command that will be used for logging in users.
 * If logon is successful, the command should place a list of phonebook entries
 * in the request attriubutes.
 * @author  yunki
 */
public class AccountPasswordChangeCommand implements Command {
	/**
	 * The helper class to delegate all function calls to
	 */
	/** Creates a new instance of LoginCommand */
	public AccountPasswordChangeCommand() {
	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		
		boolean isSave = request.getParameter("mode") != null && request.getParameter("mode").compareTo("save") == 0;
		boolean isLost = request.getParameter("changemode") != null && request.getParameter("changemode").compareTo("lost") == 0;
		
		UserEntity user = (UserEntity) session.getAttribute("userEntity");
		
		if (user != null)
		{
			if (isSave)
			{
				String oldPassword = request.getParameter("oldpassword");
				if (oldPassword == null)
				{
					oldPassword = "";
				}
				
				String newPassword = request.getParameter("newpassword");
				if (newPassword == null)
				{
					newPassword = "";
				}
				
				if (isLost || user.getPassword().compareTo(oldPassword) == 0)
				{
					user.setPassword(newPassword);
					UserDAO.UpdateUser(user);
					UserTemporaryPasswordDAO.DeleteUserTemporaryPassword(user.getUserId());
					return "/accounts/details.jsp";
				}
				else
				{
					request.setAttribute("exception", "Old password incorrect.");
				}
			}
		}

		return "/accounts/password.jsp";
	}
	
}
