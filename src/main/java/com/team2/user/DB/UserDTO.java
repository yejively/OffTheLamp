package com.team2.user.DB;

import java.sql.Timestamp;

public class UserDTO {
	private int user_num;
	private String user_id;
	private String user_name;
	private String user_pass;
	private String user_phone;
	private Timestamp user_regdate;
	private int user_orderCount;
	private String user_car_num;
	private String user_car_type;
	private Timestamp last_access;
	private int user_type;
	
	
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_pass() {
		return user_pass;
	}
	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public Timestamp getUser_regdate() {
		return user_regdate;
	}
	public void setUser_regdate(Timestamp user_regdate) {
		this.user_regdate = user_regdate;
	}
	public int getUser_orderCount() {
		return user_orderCount;
	}
	public void setUser_orderCount(int user_orderCount) {
		this.user_orderCount = user_orderCount;
	}
	public String getUser_car_num() {
		return user_car_num;
	}
	public void setUser_car_num(String user_car_num) {
		this.user_car_num = user_car_num;
	}
	public String getUser_car_type() {
		return user_car_type;
	}
	public void setUser_car_type(String user_car_type) {
		this.user_car_type = user_car_type;
	}
	public Timestamp getLast_access() {
		return last_access;
	}
	public void setLast_access(Timestamp last_access) {
		this.last_access = last_access;
	}
	public int getUser_type() {
		return user_type;
	}
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	
		
	
}
