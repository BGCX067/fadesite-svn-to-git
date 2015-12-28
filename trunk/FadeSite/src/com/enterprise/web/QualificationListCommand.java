package com.enterprise.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
public class QualificationListCommand implements Command {
	/**
	 * The helper class to delegate all function calls to
	 */
	
	/** Creates a new instance of AddCommand */
	public QualificationListCommand() {

	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserEntity user = (UserEntity) session.getAttribute("userEntity");

		if (user != null && request.getParameter("mode") != null && request.getParameter("mode").compareTo("save") == 0)
		{
			if (HTMLHelper.GetCookieValue(request.getCookies(), "createQualification", "false").compareTo("false") != 0)
			{
				String strDegreeId = request.getParameter("degree");
				String strGraduated = request.getParameter("graduated");
				
				int degreeId = -1;
				
				degreeId = Integer.parseInt(strDegreeId);
				
				SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
				
				Date graduated = null;
				
				try {
					graduated = df.parse(strGraduated);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				UserDegreeEntity degree = new UserDegreeEntity();
				degree.setUserID(user.getUserId());
				degree.setGraduatingDate(graduated);
				degree.setDegreeID(degreeId);
				UserDegreeDAO.CreateUserDegree(degree);
				
				response.addCookie(new Cookie("createQualification", "false"));
			}
		}
		else if (user != null && request.getParameter("mode") != null && request.getParameter("mode").compareTo("delete") == 0)
		{
				String strDegree = request.getParameter("degreeId");
				int degreeID = Integer.parseInt(strDegree);
				UserDegreeEntity degree = UserDegreeDAO.getUserDegree(degreeID);
				degree.setIsDeleted(true);
				UserDegreeDAO.UpdateUserDegree(degree);
		}
		
		return "/qualifications/list.jsp";
	}
	
}
