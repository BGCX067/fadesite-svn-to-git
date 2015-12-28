package com.enterprise.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enterprise.dao.UserDAO;
import com.enterprise.dao.UserDegreeDAO;



/**
 * This command adds a new contact entry
 * @author $author 
 */
public class SearchUserCommand implements Command {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

		HttpSession session = request.getSession();

		String strDegreeId = request.getParameter("sdegree");
		String strStartDate = request.getParameter("sstartdate");
		String strEndDate = request.getParameter("senddate");
		
		if (strDegreeId != null && strStartDate != null && strEndDate != null)
		{
			SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
			Date startDate = null;
			
			try {
				startDate = df.parse(strStartDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			
			Date endDate = null;
			
			try {
				endDate = df.parse(strEndDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			
			int degreeId = Integer.parseInt(strDegreeId);
			
			request.setAttribute("userList", UserDegreeDAO.ListClassmates(degreeId, startDate, endDate));
		}
		else
		{
			request.setAttribute("userList", UserDAO.GetAllActiveAlumni());
		}
		
		return "/search/default.jsp";
	}
	
}
