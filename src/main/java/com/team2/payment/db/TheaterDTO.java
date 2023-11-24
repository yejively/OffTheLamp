package com.team2.payment.db;

public class TheaterDTO {
	private String theater_name;
	private String cinema_name;
	private int seat_num;
	
	
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
	public int getSeat_num() {
		return seat_num;
	}
	public void setSeat_num(int seat_num) {
		this.seat_num = seat_num;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
}
