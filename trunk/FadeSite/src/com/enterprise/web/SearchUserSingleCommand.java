package com.enterprise.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enterprise.dao.UserDAO;



/**
 * This command adds a new contact entry
 * @author $author 
 */
public class SearchUserSingleCommand implements Command {
	/**
	 * The helper class to delegate all function calls to
	 */
	
	/** Creates a new instance of AddCommand */
	public SearchUserSingleCommand() {

	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

		HttpSession session = request.getSession();

		String strId = request.getParameter("id");

		int id = Integer.parseInt(strId);
		
		request.setAttribute("searchuser", UserDAO.GetUser(id));
		
		return "/search/user.jsp";
	}
	
}