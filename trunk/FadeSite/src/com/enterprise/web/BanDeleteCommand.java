package com.enterprise.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enterprise.dao.*;
import com.enterprise.entity.*;

public class BanDeleteCommand implements Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String strBanId = request.getParameter("banId");
		
		int banId = Integer.parseInt(strBanId);
		
		UserBanEntity ban = UserBanDAO.getUserBan(banId);
		ban.setIsDeleted(true);
		UserBanDAO.UpdateUserBan(ban);
		
		return "/admin/default.jsp";
	}

}
