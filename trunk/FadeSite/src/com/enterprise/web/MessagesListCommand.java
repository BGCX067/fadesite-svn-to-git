package com.enterprise.web;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enterprise.dao.*;
import com.enterprise.entity.*;
import com.enterprise.web.helper.HTMLHelper;



/**
 * This command adds a new contact entry
 * @author $author 
 */
public class MessagesListCommand implements Command {
	/**
	 * The helper class to delegate all function calls to
	 */
	
	/** Creates a new instance of AddCommand */
	public MessagesListCommand() {

	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

		try
		{
			HttpSession session = request.getSession();
			UserEntity curUser = (UserEntity) session.getAttribute("userEntity");
			
			String action = request.getParameter("msgAction");
			
			
			if (action != null)
			{
				int allowId = Integer.parseInt(HTMLHelper.GetCookieValue(request.getCookies(), "msgAction", "-1"));
				response.addCookie(new Cookie("msgAction", Integer.toString(-1)));
				
				String strMsgId = request.getParameter("msgId");
				
				if (strMsgId != null && Integer.parseInt(strMsgId) == allowId)
				{
					MessageEntity message = MessageDAO.GetMessage(allowId);
					
					if (action.compareTo("unread") == 0)
					{
						message.setIsRead(false);
						MessageDAO.UpdateMessage(message);
					}
					else if (action.compareTo("delete") == 0)
					{
						String mode = request.getParameter("mode");
						
						if (mode != null)
						{
							if (mode.compareTo("sender") == 0)
							{
								message.setDeletedBySender(true);
								MessageDAO.UpdateMessage(message);
							}	
							else if (mode.compareTo("sendee") == 0)
							{
								message.setDeletedBySendee(true);
								MessageDAO.UpdateMessage(message);
							}
						}
					}
				}
			}
			
			
			List<MessageEntity> msgInbox = MessageDAO.listBySendee(curUser.getUserId());
			List<MessageEntity> msgOutbox = MessageDAO.listBySender(curUser.getUserId());
			
			request.setAttribute("msgInbox", msgInbox);
			request.setAttribute("msgOutbox", msgOutbox);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return "/messages/list.jsp";
	}
	
}
