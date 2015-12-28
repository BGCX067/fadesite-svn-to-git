package com.enterprise.web.helper;

import javax.mail.MessagingException;

import com.enterprise.dao.UserActivationDAO;
import com.enterprise.dao.UserDAO;
import com.enterprise.dao.UserTemporaryPasswordDAO;
import com.enterprise.entity.*;

public class PasswordRetrievalHelper
{
	public static boolean SendPasswordRetrievalEmail(int UserID)
	{
		boolean isSent = false;

		UserEntity user = UserDAO.GetUser(UserID);
		UserTemporaryPasswordDAO.DeleteUserTemporaryPassword(UserID);
		
		String password = TokenGeneratorHelper.GenerateToken();
		
		UserTemporaryPasswordEntity ua = new UserTemporaryPasswordEntity();
		ua.setUserId(UserID);
		ua.setIsDeleted(false);
		ua.setPassword(password);
		UserTemporaryPasswordDAO.CreateUserTemporaryPassword(ua);
		
		String emailContent = "<html>";
		
		emailContent += String.format("Your Temporary Password: %1$s <br/> ", password);
		emailContent += String.format("<a href='http://localhost:8080/FadeSite/dispatcher?section=account&action=goLogin'>Login</a>");
		
		emailContent += "</html>";
		
		try {
			MailHelper.PostMail(new String[] { user.getEmailAddress() }, "FadeSite Password Retrieval", emailContent, "FadeSite");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isSent;
	}
}
