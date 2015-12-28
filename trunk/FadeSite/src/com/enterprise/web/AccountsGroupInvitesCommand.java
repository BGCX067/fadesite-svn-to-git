package com.enterprise.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enterprise.dao.*;
import com.enterprise.entity.*;

public class AccountsGroupInvitesCommand implements Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("mode") != null && request.getParameter("mode").compareTo("decline") == 0)
		{
			String strInviteId = request.getParameter("inviteId");
			int inviteId = Integer.parseInt(strInviteId);
			GroupInvitationEntity gi = GroupInvitationDAO.GetGroupInvitation(inviteId);
			gi.setIsDeleted(true);
			GroupInvitationDAO.UpdateGroupInvitation(gi);
		}
		
		return "/accounts/groupinvites.jsp";
	}

}
