package com.enterprise.web.helper;

import javax.mail.MessagingException;

import com.enterprise.dao.UserActivationDAO;
import com.enterprise.dao.UserDAO;
import com.enterprise.entity.*;

public class RegistrationHelper
{
	public static boolean SendActivationEmail(int UserID)
	{
		boolean isSent = false;

		UserEntity user = UserDAO.GetUser(UserID);
		UserActivationDAO.DeleteUserActivation(UserID);
		
		String token = TokenGeneratorHelper.GenerateToken();
		
		while (UserActivationDAO.GetUserActivation(token) != null)
		{
			token = TokenGeneratorHelper.GenerateToken();
		}
		
		UserActivationEntity ua = new UserActivationEntity();
		ua.setUserId(UserID);
		ua.setIsDeleted(false);
		ua.setToken(token);
		UserActivationDAO.CreateUserActivation(ua);
		
		String emailContent = "<html>";
		
		emailContent += String.format("<a href='http://localhost:8080/FadeSite/dispatcher?section=account&action=activate&token=%1$s'>Activation Link</a>", token);
		
		emailContent += "</html>";
		
		try {
			MailHelper.PostMail(new String[] { user.getEmailAddress() }, "FadeSite Activation", emailContent, "FadeSite");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isSent;
	}
}
