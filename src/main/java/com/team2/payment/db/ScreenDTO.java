package com.team2.payment.db;

import java.security.Timestamp;
import java.sql.Date;

public class ScreenDTO {
	private int screening_id;
	private String screening_time;
	private Date screening_date;
	private int movie_id;
	private String movie_name;
	private String theater_name;
	private String cinema_name;
	private int price;
	
	
	public int getScreening_id() {
		return screening_id;
	}
	public void setScreening_id(int screening_id) {
		this.screening_id = screening_id;
	}
	public String getScreening_time() {
		return screening_time;
	}
	public void setScreening_time(String screening_time) {
		this.screening_time = screening_time;
	}
	public Date getScreening_date() {
		return screening_date;
	}
	public void setScreening_date(Date screening_date) {
		this.screening_date = screening_date;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public String getTheater_name() {
		return theater_name;
	}
	public void setTheater_name(String theater_name) {
		this.theater_name = theater_name;
	}
	public String getCinema_name() {
		return cinema_name;
	}
	public void setCinema_name(String cinema_name) {
		this.cinema_name = cinema_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
}
