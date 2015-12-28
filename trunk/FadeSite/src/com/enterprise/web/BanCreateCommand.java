package com.enterprise.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enterprise.dao.*;
import com.enterprise.entity.*;
import com.enterprise.web.helper.HTMLHelper;
import com.enterprise.web.helper.MailHelper;

/**
 * This is the command that will be used for logging in users.
 * If logon is successful, the command should place a list of phonebook entries
 * in the request attriubutes.
 * @author  yunki
 */
public class BanCreateCommand implements Command {
	/**
	 * The helper class to delegate all function calls to
	 */
	/** Creates a new instance of LoginCommand */
	public BanCreateCommand() {
	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession();
		
		String strBanUserId = request.getParameter("banuserid");
		if (strBanUserId == null)
		{
			strBanUserId = "";
		}
		int banUserId = Integer.parseInt(strBanUserId);
		UserEntity user = UserDAO.GetUser(banUserId);
		
		if (request.getParameter("mode") != null && request.getParameter("mode").compareTo("saveban") == 0)
		{
			if (HTMLHelper.GetCookieValue(request.getCookies(), "createBan", "false").compareTo("false") != 0)
			{
				String reason = request.getParameter("reason");
				String strStartDate = request.getParameter("banstart");
				String strEndDate = request.getParameter("banend");
				
				SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
				
				Date startDate = null;
				Date endDate = null;
				
				try {
					startDate = df.parse(strStartDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					endDate = df.parse(strEndDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if (endDate == null)
				{
					endDate = startDate;
				}
				else if (endDate != null && startDate != null && endDate.before(startDate))
				{
					request.setAttribute("exception", "Invalid Date Range");
					request.setAttribute("banuser", user);
					return "/admin/bancreate.jsp";
				}
				
				UserBanEntity ban = UserBanDAO.getBanByUser(user.getUserId());
				if (ban == null)
				{
					ban = new UserBanEntity();
				}
				ban.setStartDate(startDate);
				ban.setExpireDate(endDate);
				ban.setUserID(user.getUserId());
				ban.setdescription(reason);
				ban.setIsDeleted(false);
				UserBanDAO.CreateUserBan(ban);
				
				response.addCookie(new Cookie("createBan", "false"));
				
				String message = "<html>";
				
				message += String.format("Ban Start: %1$s<br/>", ban.getStartDateString());
				message += String.format("Ban End: %1$s<br/>", ban.getExpireDateString());
				message += String.format("Reason: %1$s<br/>", ban.getDescription());
				
				message += "</html>";
				
				try {
					MailHelper.PostMail(user.getEmailAddress(), "FadeSite User Ban", message);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return "/admin/default.jsp";
			}
		}
		else
		{
			response.addCookie(new Cookie("createBan", "true"));
			
			request.setAttribute("banuser", user);
			request.setAttribute("referrer", request.getParameter("referrer"));
		}
		
		return "/admin/bancreate.jsp";
	}
	
}
