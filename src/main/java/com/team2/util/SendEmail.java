package com.team2.util;

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
import javax.mail.Session;

public class SendEmail {
	static Properties prop;
	static Session session;
	static MimeMessage message;
	
	public void generateAndSendEmail(int random, String user_email) throws Exception {
	
	final String user = "hhong865@naver.com";
	final String password = "dkssud1243!";
	System.out.println("dddd");
	// 1. 프로퍼티생성
	prop = new Properties();
	// "mail.smtp.host"은 이메일 발송을 처리해줄 SMTP서버를 나타냄
	// gmail 을 사용할때는 "smtp.gmail.com", 네이버를 사용할때는 "smtp.naver.com"
	prop.put("mail.smtp.host", "smtp.naver.com");
	//"mail.smtp.port"은 SMTP서버와 통신하는 포트고  
	// 네이버는 465 이다
	prop.put("mail.smtp.port", 465);
	prop.put("mail.smtp.auth", "true");
	prop.put("mail.smtp.ssl.enable", "true");
	prop.put("mail.smtp.ssl.trust", "smtp.naver.com");
	
	//2. 세션설정
	Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(user, password);
		}
	});
	
	//3. 메세지 보내기
	try {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(user));
		
		//수신자 메일주소
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(user_email));
		
		// 제목
		message.setSubject("Drive in Cinema 인증번호가 도착했습니다");
		
		// 내용
		message.setText("인증번호는 "+random+"입니다");
		//보내기
		Transport.send(message);
		System.out.println("보내기 성공");
	}catch (AddressException e) {
		e.printStackTrace();
	} catch (MessagingException e) {
		e.printStackTrace();
	}
	
	
}
}

