package com.team2.board.db;

import java.sql.Blob;
import java.sql.Timestamp;

/*
 * itwill_board 테이블에 저장되는 정보를
 * 한번에 담기위한 객체
 * -> 테이블-컬럼 -> 변수 작성
 */
		
public class ENFBoardDTO {
	private byte category;
	private int event_bno;
	private int notice_bno;
	private int faq_bno;
	private String subject;
	private String content;
	private int read_count;
	private Timestamp regdate;
	private Timestamp updatedate;
	private byte event_type;
	private String img;
	
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}


	public byte getCategory() {
		return category;
	}
	public void setCategory(byte category) {
		this.category = category;
	}
	public int getEvent_bno() {
		return event_bno;
	}
	public void setEvent_bno(int event_bno) {
		this.event_bno = event_bno;
	}
	public int getNotice_bno() {
		return notice_bno;
	}
	public void setNotice_bno(int notice_bno) {
		this.notice_bno = notice_bno;
	}
	public int getFaq_bno() {
		return faq_bno;
	}
	public void setFaq_bno(int faq_bno) {
		this.faq_bno = faq_bno;
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
	public byte getEvent_type() {
		return event_type;
	}
	public void setEvent_type(byte event_type) {
		this.event_type = event_type;
	}
	@Override
	public String toString() {
		return "ENFBoardDTO [category=" + category + ", event_bno=" + event_bno + ", notice_bno=" + notice_bno
				+ ", faq_bno=" + faq_bno + ", subject=" + subject + ", content=" + content + ", read_count="
				+ read_count + ", regdate=" + regdate + ", updatedate=" + updatedate + ", event_type=" + event_type
				+ ", img=" + img + "]";
	}
	
	
}//class
