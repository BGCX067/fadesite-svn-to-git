package com.enterprise.web;

import java.io.IOException;

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
public class MessagesSendCommand implements Command {
	/**
	 * The helper class to delegate all function calls to
	 */
	
	/** Creates a new instance of AddCommand */
	public MessagesSendCommand() {

	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		if (HTMLHelper.GetCookieValue(request.getCookies(), "sendMsg", "false").compareTo("false") != 0)
		{
			UserEntity curUser = (UserEntity) session.getAttribute("userEntity");
			
			String StrId = request.getParameter("senduserid");
			if (StrId == null)
			{
				StrId = "";
			}
	
			UserEntity sendUser = null;
			
			try
			{
				int sendUserId = Integer.parseInt(StrId);
				sendUser = UserDAO.GetUser(sendUserId);
			}
			catch (NumberFormatException e)
			{
				e.printStackTrace();
			}
			
			String body = request.getParameter("body");
			String subject = request.getParameter("subject");
			
			MessageEntity msg = new MessageEntity();
			msg.setTitle(subject);
			msg.setContents(body);
			msg.setIsRead(false);
			msg.setDeletedBySendee(false);
			msg.setDeletedBySender(false);
			msg.setIsDeleted(false);
			msg.setSendeeID(sendUser.getUserId());
			msg.setSenderID(curUser.getUserId());
			
			MessageDAO.CreateMessage(msg);
			
			response.addCookie(new Cookie("sendMsg", "false"));
		}
		
		MessagesListCommand cmd = new MessagesListCommand();
		
		return cmd.execute(request, response);
	}
	
}