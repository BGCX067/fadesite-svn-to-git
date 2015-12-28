/*
 * LoginCommand.java
 *
 * Created on 9 August 2003, 11:12
 */

package com.enterprise.web;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enterprise.dao.UserDAO;
import com.enterprise.entity.UserEntity;
import com.enterprise.web.helper.MailHelper;
import com.enterprise.web.helper.RegistrationHelper;
import com.enterprise.web.helper.TokenGeneratorHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.*;

/**
 * This is the command that will be used for logging in users.
 * If logon is successful, the command should place a list of phonebook entries
 * in the request attriubutes.
 * @author  yunki
 */
public class AccountRegisterCommand implements Command {
	/**
	 * The helper class to delegate all function calls to
	 */
	/** Creates a new instance of LoginCommand */
	public AccountRegisterCommand() {
	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
		
		String exceptions = "";
		
		String username = request.getParameter("username");
		String firstname = request.getParameter("firstname");
		String surname = request.getParameter("surname");
		long refid = -1;
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		String password = request.getParameter("password");
		Date dob = null;
		
		String strUserTypeId = request.getParameter("usertype");
		if (strUserTypeId == null)
		{
			strUserTypeId = "";
		}
		
		int usertypeid = strUserTypeId.compareTo("alumni") == 0 ? 1 : 2;
		
		try {
			dob = df.parse(request.getParameter("dob"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		
		try {
			refid = Long.parseLong(request.getParameter("referenceId"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (UserDAO.GetUserByUsername(username) != null)
		{
			exceptions += "Username already used.<br>";
		}
		
		if (!EmailValid(email))
		{
			exceptions += "Email Address invalid.<br/>";
		}
		
		if (refid < 1)
		{
			exceptions += "Reference ID Invalid.<br/>";
		}
		
		if (dob == null)
		{
			exceptions += "Invalid Date of Bith.<br>";
		}
		
		if (exceptions.length() == 0)
		{
			UserEntity user = new UserEntity();
			user.setUsername(username);
			user.setFirstname(firstname);
			user.setSurname(surname);
			user.setReferenceId(refid);
			user.setEmailAddress(email);
			user.setGender(gender);
			user.setAddress(address);
			user.setDOB(dob);
			user.setUserTypeId(usertypeid);
			user.setPassword(password);
			
			if (UserDAO.CreateUser(user))
			{
				user = UserDAO.GetUserByUsername(user.getUsername());
				RegistrationHelper.SendActivationEmail(user.getUserId());
				return "/accounts/confirm.jsp";
			}
		}
		else
		{
			request.setAttribute("exception", exceptions);
		}
		
		return "/accounts/register.jsp";
	}
	
	private boolean EmailValid(String email)
	{
	      //Set the email pattern string
	      Pattern p = Pattern.compile(".+@.+\\.[a-z]+");

	      //Match the given string with the pattern
	      Matcher m = p.matcher(email);

	      //check whether match is found 
	      return m.matches();
	}
	
	private boolean StringIsNullOrEmpty(String str)
	{
		if (str == null || str.trim().length() == 0)
		{
			return true;
		}
		
		return false;
	}
	
}
