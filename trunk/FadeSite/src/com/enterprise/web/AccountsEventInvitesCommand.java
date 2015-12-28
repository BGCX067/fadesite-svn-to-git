package com.enterprise.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enterprise.dao.EventInvitationDAO;
import com.enterprise.entity.EventInvitationEntity;

public class AccountsEventInvitesCommand implements Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("mode") != null && request.getParameter("mode").compareTo("decline") == 0)
		{
			String strInviteId = request.getParameter("inviteId");
			int inviteId = Integer.parseInt(strInviteId);
			EventInvitationEntity ei = EventInvitationDAO.GetEventInvitation(inviteId);
			ei.setIsDeleted(true);
			EventInvitationDAO.UpdateEventInvitation(ei);
		}
		
		return "/accounts/eventinvites.jsp";
	}

}
