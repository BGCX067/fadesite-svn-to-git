/*
 * ErrorCommand.java
 *
 * Created on 9 August 2003, 23:04
 */

package com.enterprise.web;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author  yunki
 */
public class ErrorCommand implements Command {
	
	/** Creates a new instance of ErrorCommand */
	public ErrorCommand() {
	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/error.jsp";
	}
	
}
