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
import com.enterprise.entity.UserEntity;
import com.enterprise.web.helper.PasswordRetrievalHelper;

/**
 * This is the command that will be used for logging in users.
 * If logon is successful, the command should place a list of phonebook entries
 * in the request attriubutes.
 * @author  yunki
 */
public class AccountPasswordRetrievalCommand implements Command {
	/**
	 * The helper class to delegate all function calls to
	 */
	/** Creates a new instance of LoginCommand */
	public AccountPasswordRetrievalCommand() {
	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		
		String email = request.getParameter("emailAddress");
		
		UserEntity user = UserDAO.GetUserByEmail(email);
		
		PasswordRetrievalHelper.SendPasswordRetrievalEmail(user.getUserId());

		return "/accounts/retrievalconfirm.jsp";
	}
	
}
