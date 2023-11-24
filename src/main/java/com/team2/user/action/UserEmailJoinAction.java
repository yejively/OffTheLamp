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

public class UserEmailJoinAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String user_email = req.getParameter("user_id");
		int random =(int)(Math.random() * 8999) + 1000;
		UserDAO dao = new UserDAO();
		String result = dao.findPw(user_email);
		
		System.out.println("result : "+result);
		System.out.println("user_email : "+user_email);
		
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		if(result.equals("")) {
			SendEmail sendemail = new SendEmail();
			sendemail.generateAndSendEmail(random, user_email);
			out.print(random);
		}else {
			out.print("");
		}
		
		return null;
	}
	
}
