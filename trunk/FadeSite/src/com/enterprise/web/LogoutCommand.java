/*
 * LogoutCommand.java
 *
 * Created on 11 August 2003, 12:37
 */

package com.enterprise.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author $author
 */
public class LogoutCommand implements Command {
	
	/** Creates a new instance of LogoutCommand */
	public LogoutCommand() {
	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "/logout.jsp";
	}
	
}
