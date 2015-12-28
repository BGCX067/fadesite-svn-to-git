package com.enterprise.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enterprise.dao.*;
import com.enterprise.entity.*;



/**
 * This command adds a new contact entry
 * @author $author 
 */
public class MessagesViewCommand implements Command {
	/**
	 * The helper class to delegate all function calls to
	 */
	
	/** Creates a new instance of AddCommand */
	public MessagesViewCommand() {

	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		UserEntity curUser = (UserEntity) session.getAttribute("userEntity");
		
		String StrMsgId = request.getParameter("msgid");
		int msgId = Integer.parseInt(StrMsgId);
		
		MessageEntity msg = MessageDAO.GetMessage(msgId);
		
		if (request.getParameter("mode") != null && request.getParameter("mode").compareTo("sendee") == 0)
		{
			msg.setIsRead(true);
		}
		MessageDAO.UpdateMessage(msg);
		
		request.setAttribute("mode", request.getParameter("mode"));
		
		request.setAttribute("message", msg);
		
		response.addCookie(new Cookie("msgAction", Integer.toString(msg.getMessageID())));
		
		return "/messages/view.jsp";
	}
	
}