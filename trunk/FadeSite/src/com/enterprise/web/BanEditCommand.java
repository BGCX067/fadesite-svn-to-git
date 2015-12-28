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

import com.enterprise.dao.*;
import com.enterprise.entity.*;
import com.enterprise.web.helper.HTMLHelper;

public class BanEditCommand implements Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String strBanId = request.getParameter("banId");
		
		int banId = Integer.parseInt(strBanId);
		
		UserBanEntity ban = UserBanDAO.getUserBan(banId);
		
		request.setAttribute("referrer", request.getParameter("referrer"));
		
		if (request.getParameter("mode") != null && request.getParameter("mode").compareTo("save") == 0)
		{
			if (HTMLHelper.GetCookieValue(request.getCookies(), "editBan", "false").compareTo("false") != 0)
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
					request.setAttribute("ban", ban);
					return "/admin/banedit.jsp";
				}
				
				ban.setStartDate(startDate);
				ban.setExpireDate(endDate);
				ban.setdescription(reason);
				UserBanDAO.UpdateUserBan(ban);
				
				response.addCookie(new Cookie("banUser", "false"));
				
				return "/admin/default.jsp";
			}
		}
		
		request.setAttribute("ban", ban);
		
		response.addCookie(new Cookie("editBan", "true"));
		
		return "/admin/banedit.jsp";
	}

}
