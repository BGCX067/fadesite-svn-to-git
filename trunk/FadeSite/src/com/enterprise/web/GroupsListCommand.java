package com.enterprise.web;

import java.io.IOException;

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
public class GroupsListCommand implements Command {
	/**
	 * The helper class to delegate all function calls to
	 */
	
	/** Creates a new instance of AddCommand */
	public GroupsListCommand() {

	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserEntity userSession = (UserEntity) session.getAttribute("userEntity");
		
		if (userSession == null)
		{
			userSession = new UserEntity();
		}
		
		if (request.getParameter("mode") != null && request.getParameter("mode").compareTo("save") == 0)
		{
			if (HTMLHelper.GetCookieValue(request.getCookies(), "createGroup", "false").compareTo("false") != 0)
			{
				GroupEntity group = new GroupEntity();
				
				String name = request.getParameter("name");
				String description = request.getParameter("description");
				
				group.setDescription(description);
				group.setName(name);
				
				GroupDAO.CreateGroup(group);
				
				response.addCookie(new Cookie("createGroup", "false"));
			}
		}
		else if (request.getParameter("mode") != null && request.getParameter("mode").compareTo("leave") == 0)
		{
			if (HTMLHelper.GetCookieValue(request.getCookies(), "groupConfirm", "false").compareTo("false") != 0)
			{
				String strGroupId = request.getParameter("groupId");
				int groupId = Integer.parseInt(strGroupId);
				
				GroupEntity group = GroupDAO.GetGroup(groupId);
				
				UserGroupEntity ug = UserGroupDAO.getUserGroup(userSession.getUserId(), group.getGroupID());
				ug.setIsDeleted(true);
				UserGroupDAO.updateUserGroup(ug);
				
				response.addCookie(new Cookie("groupConfirm", "false"));
			}
		}
		else if (request.getParameter("mode") != null && request.getParameter("mode").compareTo("join") == 0)
		{
			if (HTMLHelper.GetCookieValue(request.getCookies(), "groupConfirm", "false").compareTo("false") != 0)
			{
				String strGroupId = request.getParameter("groupId");
				int groupId = Integer.parseInt(strGroupId);
				
				GroupEntity group = GroupDAO.GetGroup(groupId);
				
				UserGroupEntity ug = new UserGroupEntity();
				ug.setGroupID(group.getGroupID());
				ug.setUserID(userSession.getUserId());
				
				UserGroupDAO.createUserGroup(ug);
				
				String strInviteId = request.getParameter("requestId");
				if (strInviteId != null)
				{
					int inviteId = Integer.parseInt(strInviteId);
					GroupInvitationEntity gi = GroupInvitationDAO.GetGroupInvitation(inviteId);
					gi.setInviteAccepted(true);
					GroupInvitationDAO.UpdateGroupInvitation(gi);
				}
				
				response.addCookie(new Cookie("groupConfirm", "false"));
			}
		}
		
		request.setAttribute("groups", GroupDAO.GetAllGroups());
		if (userSession != null)
		{
			request.setAttribute("userGroups", GroupDAO.GetUserGroups(userSession.getUserId()));
		}
		
		return "/groups/list.jsp";
	}
	
}
