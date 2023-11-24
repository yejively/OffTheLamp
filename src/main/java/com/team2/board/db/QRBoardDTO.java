package com.team2.board.db;

import java.sql.Timestamp;

/*
 * itwill_board 테이블에 저장되는 정보를
 * 한번에 담기위한 객체
 * -> 테이블-컬럼 -> 변수 작성
 */
		
public class QRBoardDTO {
	private byte category;
	private int qna_bno;
	private int rent_bno;
	private String user_id;
	private String subject;
	private String content;
	private int read_count;
	private Timestamp regdate;
	private Timestamp updatedate;
	private String rent_name;
	private String cinema_name;
	private String rent_phone;
	private String rent_email;
	private byte answer;
	private String answer_context;

	public byte getCategory() {
		return category;
	}
	public void setCategory(byte category) {
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
	public int getRead_count() {
		return read_count;
	}
	public void setRead_count(int readcount) {
		this.read_count = readcount;
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
	public byte getAnswer() {
		return answer;
	}
	public void setAnswer(byte answer) {
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
		return "QRBoardDTO [category=" + category + ", qna_bno=" + qna_bno + ", rent_bno=" + rent_bno + ", user_id="
				+ user_id + ", subject=" + subject + ", content=" + content + ", read_count=" + read_count
				+ ", regdate=" + regdate + ", updatedate=" + updatedate + ", rent_name=" + rent_name + ", cinema_name="
				+ cinema_name + ", rent_phone=" + rent_phone + ", rent_email=" + rent_email + ", answer=" + answer
				+ ", answer_context=" + answer_context + "]";
	}
	
	
	
	
	

}//class
