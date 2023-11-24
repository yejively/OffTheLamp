package com.team2.payment.db;

import java.util.Date;

public class OrderDTO {
	private int order_id;
	private String user_id;
	private int nonuser_id;
	private String region;
	private String cinema_name;
	private String theater_name;
	private String seat;
	private String movie_name;
	private Date order_date;
	private int order_state;
	private String screening_time;
	private int screening_id;
	private String car_type;
	private String car_num;
	
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getNonuser_id() {
		return nonuser_id;
	}
	public void setNonuser_id(int nonuser_id) {
		this.nonuser_id = nonuser_id;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCinema_name() {
		return cinema_name;
	}
	public void setCinema_name(String cinema_name) {
		this.cinema_name = cinema_name;
	}
	public String getTheater_name() {
		return theater_name;
	}
	public void setTheater_name(String theater_name) {
		this.theater_name = theater_name;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public int getOrder_state() {
		return order_state;
	}
	public void setOrder_state(int order_state) {
		this.order_state = order_state;
	}
	public String getScreening_time() {
		return screening_time;
	}
	public void setScreening_time(String screening_time) {
		this.screening_time = screening_time;
	}
	public int getScreening_id() {
		return screening_id;
	}
	public void setScreening_id(int screening_id) {
		this.screening_id = screening_id;
	}
	public String getCar_type() {
		return car_type;
	}
	public void setCar_type(String car_type) {
		this.car_type = car_type;
	}
	public String getCar_num() {
		return car_num;
	}
	public void setCar_num(String car_num) {
		this.car_num = car_num;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
