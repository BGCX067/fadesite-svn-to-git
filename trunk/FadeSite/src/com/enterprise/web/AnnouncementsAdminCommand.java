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
public class AnnouncementsAdminCommand implements Command {
	/**
	 * The helper class to delegate all function calls to
	 */
	
	/** Creates a new instance of AddCommand */
	public AnnouncementsAdminCommand() {

	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserEntity user = (UserEntity) session.getAttribute("userEntity");

		String strAnnounceId = request.getParameter("announceId");
		int announceId = Integer.parseInt(strAnnounceId);
		AnnounceEntity announce = AnnounceDAO.getAnnounce(announceId);
		AnnounceUpdateEntity update = AnnounceUpdateDAO.getUpdateByAnnounce(announceId);
		
		if (request.getParameter("mode") != null && request.getParameter("mode").compareTo("approve") == 0)
		{
			if (update != null)
			{
				announce.setStartDate(update.getStartDate());
				announce.setExpireDate(update.getExpireDate());
				announce.setTitle(update.getTitle());
				announce.setDescription(update.getDescription());
				announce.setUpdateDate(update.getUpdateDate());
				AnnounceDAO.UpdateAnnounce(announce);
				
				update.setIsApproved(true);
				update.setIsDeleted(true);
				AnnounceUpdateDAO.UpdateAnnounceUpdate(update);
			}
			else
			{
				announce.setIsApproved(true);
				AnnounceDAO.UpdateAnnounce(announce);
			}
		}
		else if (request.getParameter("mode") != null && request.getParameter("mode").compareTo("decline") == 0)
		{
			if (update != null)
			{
				update.setIsDeleted(true);
				AnnounceUpdateDAO.UpdateAnnounceUpdate(update);
			}
			else
			{
				announce.setIsDeleted(true);
				AnnounceDAO.UpdateAnnounce(announce);
			}
		}
		
		return "/admin/default.jsp";
	}
	
}
