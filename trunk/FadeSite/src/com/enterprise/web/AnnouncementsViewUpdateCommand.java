package com.enterprise.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enterprise.web.helper.HTMLHelper;

import com.enterprise.dao.*;
import com.enterprise.entity.*;


/**
 * This command adds a new contact entry
 * @author $author 
 */
public class AnnouncementsViewUpdateCommand implements Command {
	/**
	 * The helper class to delegate all function calls to
	 */
	
	/** Creates a new instance of AddCommand */
	public AnnouncementsViewUpdateCommand() {

	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

		HttpSession session = request.getSession();

		UserEntity user = (UserEntity) session.getAttribute("userEntity");
		
		if (user == null)
		{
			user = new UserEntity();
		}
		
		String strUpdateId = request.getParameter("updateId");
		int updateId = Integer.parseInt(strUpdateId);
		
		request.setAttribute("announce", AnnounceUpdateDAO.getAnnounceUpdate(updateId));
		
		if (request.getParameter("mode") != null && request.getParameter("mode").compareTo("admin") == 0)
		{
			return "/admin/announce.jsp";
		}
		
		return "/announcements/viewupdate.jsp";
	}
	
}

