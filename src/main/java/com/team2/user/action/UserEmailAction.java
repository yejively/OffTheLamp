package com.team2.user.action;

import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team2.user.DB.UserDAO;
import com.team2.util.Action;
import com.team2.util.ActionForward;
import com.team2.util.SendEmail;

public class UserEmailAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("----------------");
		String user_email = request.getParameter("user_id");
		System.out.println(user_email+"@@@@");
		int random =(int)(Math.random() * 8999) + 1000;
		UserDAO dao = new UserDAO();
		String result = dao.findPw(user_email);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(result.equals("")) {
			out.print("");
		}else {
			
			SendEmail sendemail = new SendEmail();
			sendemail.generateAndSendEmail(random, user_email);
			out.print(random);
		}
		
		return null;
	}
}
