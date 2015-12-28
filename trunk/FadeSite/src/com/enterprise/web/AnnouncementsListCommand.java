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

import com.enterprise.web.helper.HTMLHelper;

import com.enterprise.dao.*;
import com.enterprise.entity.*;


/**
 * This command adds a new contact entry
 * @author $author 
 */
public class AnnouncementsListCommand implements Command {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

		HttpSession session = request.getSession();

		UserEntity user = (UserEntity) session.getAttribute("userEntity");
		
		if (user == null)
		{
			user = new UserEntity();
		}
		
		if (request.getParameter("groupId") != null)
		{
			int groupId = Integer.parseInt(request.getParameter("groupId"));
			request.setAttribute("group", GroupDAO.GetGroup(groupId));
		}
		
		if (request.getParameter("eventId") != null)
		{
			int eventId = Integer.parseInt(request.getParameter("eventId"));
			request.setAttribute("event", EventDAO.getEvent(eventId));
		}
		
		if (request.getParameter("mode") != null && request.getParameter("mode").compareTo("save") == 0)
		{
			if (HTMLHelper.GetCookieValue(request.getCookies(), "createAnnouncement", "false").compareTo("false") != 0)
			{
				SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
				
				String strStartDate = request.getParameter("startdate");
				String strExpiryDate = request.getParameter("expirydate");
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				
				Date startDate = null;
				Date expiryDate = null;
				
				try {
					startDate = df.parse(strStartDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
				
				try {
					expiryDate = df.parse(strExpiryDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
				
				if (expiryDate != null && startDate != null && expiryDate.before(startDate))
				{
					request.setAttribute("exception", "Invalid Date Range.");
					return "/announcements/create.jsp";
				}
				
				AnnounceEntity announcement = new AnnounceEntity();
				announcement.setStartDate(startDate);
				announcement.setDescription(content);
				announcement.setTitle(title);
				announcement.setExpireDate(expiryDate);
				announcement.setCreatedByUser(user.getUserId());
				announcement.setUpdatedByUser(user.getUserId());
				announcement.setAnnounceType(1);
				
				if (user.getUserTypeId() == 3)
				{
					announcement.setIsApproved(true);
				}
				
				AnnounceDAO.CreateAnnounce(announcement);
				
				if (request.getParameter("groupId") != null)
				{
					int groupId = Integer.parseInt(request.getParameter("groupId"));
					int lastID = AnnounceDAO.getLastAnnounce().getAnnounceID();
					GroupAnnounceEntity ga = new GroupAnnounceEntity();
					ga.setAnnounceID(lastID);
					ga.setGroupID(groupId);
					GroupAnnounceDAO.createGroupAnnounce(ga);
				}
				
				if (request.getParameter("eventId") != null)
				{
					int eventId = Integer.parseInt(request.getParameter("eventId"));
					int lastID = AnnounceDAO.getLastAnnounce().getAnnounceID();
					EventAnnounceEntity ea = new EventAnnounceEntity();
					ea.setAnnounceID(lastID);
					ea.setEventID(eventId);
					EventAnnounceDAO.createEventAnnounce(ea);
				}
				
				// destroy cookie
				response.addCookie(new Cookie("createAnnouncement", "false"));
			}
		}
		else if (request.getParameter("mode") != null && (request.getParameter("mode").compareTo("edit") == 0 || request.getParameter("mode").compareTo("update") == 0))
		{
			if (HTMLHelper.GetCookieValue(request.getCookies(), "editAnnouncement", "false").compareTo("false") != 0)
			{
				String mode = request.getParameter("mode");
				
				SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
				
				String strStartDate = request.getParameter("startdate");
				String strExpiryDate = request.getParameter("expirydate");
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				String strAnnounceId = request.getParameter("announceId");
				int announceID = Integer.parseInt(strAnnounceId);
				
				Date startDate = null;
				Date expiryDate = null;
				
				try {
					startDate = df.parse(strStartDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
				
				try {
					expiryDate = df.parse(strExpiryDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
				
				if (expiryDate != null && startDate != null && expiryDate.before(startDate))
				{
					if (mode.compareTo("edit") == 0)
					{
						request.setAttribute("announce", AnnounceDAO.getAnnounce(announceID));
						request.setAttribute("exception", "Invalid Date Range.");
						return "/announcements/edit.jsp";
					}
					else
					{
						request.setAttribute("announce", AnnounceUpdateDAO.getUpdateByAnnounce(announceID));
						request.setAttribute("exception", "Invalid Date Range.");
						return "/announcements/update.jsp";
					}
				}
				
				if (user.getUserTypeId() == 3)
				{
					AnnounceEntity announcement = AnnounceDAO.getAnnounce(announceID);
					announcement.setStartDate(startDate);
					announcement.setDescription(content);
					announcement.setTitle(title);
					announcement.setExpireDate(expiryDate);
					announcement.setUpdatedByUser(user.getUserId());
					announcement.setAnnounceType(1);
					AnnounceDAO.UpdateAnnounce(announcement);
				}
				else
				{
					AnnounceUpdateEntity announcement = new AnnounceUpdateEntity();
					announcement.setAnnounceID(announceID);
					announcement.setStartDate(startDate);
					announcement.setDescription(content);
					announcement.setTitle(title);
					announcement.setExpireDate(expiryDate);
					announcement.setUpdatedByUser(user.getUserId());
					announcement.setAnnounceType(1);
					
					if (mode.compareTo("edit") == 0)
					{
						AnnounceUpdateDAO.CreateAnnounceUpdate(announcement);
					}
					else
					{
						announcement.setAnnounceUpdateID(AnnounceUpdateDAO.getUpdateByAnnounce(announceID).getAnnounceUpdateID());
						AnnounceUpdateDAO.UpdateAnnounceUpdate(announcement);
					}
				}
				
				// destroy cookie
				response.addCookie(new Cookie("editAnnouncement", "false"));
			}
		}
		
		if (request.getParameter("groupId") != null)
		{
			return "/groups/home.jsp";
		}
		
		if (request.getParameter("eventId") != null)
		{
			return "/events/home.jsp";
		}
		
		return "/announcements/list.jsp";
	}
	
}
