/*
 * AddCommand.java
 *
 * Created on 9 August 2003, 11:20
 */

package com.enterprise.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * This command adds a new contact entry
 * @author $author 
 */
public class StaticCommand implements Command {
	/**
	 * The helper class to delegate all function calls to
	 */
	
	/** Creates a new instance of AddCommand */
	public StaticCommand() {

	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

		String redirect = (String)request.getAttribute("redirect");

		return ("/" + redirect);
	}
	
}
