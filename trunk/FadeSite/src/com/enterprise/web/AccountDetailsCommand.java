/*
 * LoginCommand.java
 *
 * Created on 9 August 2003, 11:12
 */

package com.enterprise.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enterprise.dao.UserDAO;
import com.enterprise.entity.UserEntity;

/**
 * This is the command that will be used for logging in users.
 * If logon is successful, the command should place a list of phonebook entries
 * in the request attriubutes.
 * @author  yunki
 */
public class AccountDetailsCommand implements Command {
	/**
	 * The helper class to delegate all function calls to
	 */
	/** Creates a new instance of LoginCommand */
	public AccountDetailsCommand() {
	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
	
		HttpSession session = request.getSession();

		UserEntity user = (UserEntity) session.getAttribute("userEntity");
		
		if (request.getParameter("mode") != null && request.getParameter("mode").compareTo("edit") == 0
				&& user != null)
		{
			request.setAttribute("mode", "edit");
		}
		
		if (request.getParameter("modeaction") != null && request.getParameter("modeaction").compareTo("save") == 0
				&& user != null)
		{
			SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yy" );
			
			String exceptions = "";
			
			String firstname = request.getParameter("firstname");
			String surname = request.getParameter("surname");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String gender = request.getParameter("gender");
			long refid = -1;
			Date dob = null;
			
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
				user.setFirstname(firstname);
				user.setSurname(surname);
				user.setReferenceId(refid);
				user.setEmailAddress(email);
				user.setGender(gender);
				user.setAddress(address);
				user.setDOB(dob);
				
				UserDAO.UpdateUser(user);
				session.setAttribute("userEntity", user);
			}
			else
			{
				request.setAttribute("mode", "edit");
				request.setAttribute("exceptions", exceptions);
			}
		}
		
		return "/accounts/details.jsp";
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
