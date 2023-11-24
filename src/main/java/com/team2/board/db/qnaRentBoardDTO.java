package com.team2.board.db;

import java.sql.Timestamp;

public class qnaRentBoardDTO {
	
	private int category ; // qna = 0 , rent = 1
	private int qna_bno ; // 0 일때 증가
	private int rent_bno ; //1 일때 증가
	private String user_id ; 
	private String subject ;
	private String content ;
	private int readCount ;
	private Timestamp regdate ;
	private Timestamp updatedate ;
	private String rent_name ; //대관신청
	private String cinema_name ; // 대관신청
	private String rent_phone ; // 대관신청
	private String rent_email ; // 대관신청 
	private int answer ; // 회신여부 
	private String answer_context ;
	
	
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getQna_bno() {
		return qna_bno;
	}
	public void setQna_bno(int qna_bno) {
		this.qna_bno = qna_bno;
	}
	public int getRent_bno() {
		return rent_bno;
	}
	public void setRent_bno(int rent_bno) {
		this.rent_bno = rent_bno;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public Timestamp getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Timestamp updatedate) {
		this.updatedate = updatedate;
	}
	public String getRent_name() {
		return rent_name;
	}
	public void setRent_name(String rent_name) {
		this.rent_name = rent_name;
	}
	public String getCinema_name() {
		return cinema_name;
	}
	public void setCinema_name(String cinema_name) {
		this.cinema_name = cinema_name;
	}
	public String getRent_phone() {
		return rent_phone;
	}
	public void setRent_phone(String rent_phone) {
		this.rent_phone = rent_phone;
	}
	public String getRent_email() {
		return rent_email;
	}
	public void setRent_email(String rent_email) {
		this.rent_email = rent_email;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public String getAnswer_context() {
		return answer_context;
	}
	public void setAnswer_context(String answer_context) {
		this.answer_context = answer_context;
	}
	
	
	@Override
	public String toString() {
		return "qnaRentBoardDTO";
	}//toString
	
}// class 
