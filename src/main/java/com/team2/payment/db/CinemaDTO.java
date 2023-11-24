package com.team2.payment.db;

public class CinemaDTO {
	private String cinema_name;
	private String region;
	private int cinema_close;
	
	public String getCinema_name() {
		return cinema_name;
	}
	public void setCinema_name(String cinema_name) {
		this.cinema_name = cinema_name;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public int getCinema_close() {
		return cinema_close;
	}
	public void setCinema_close(int cinema_close) {
		this.cinema_close = cinema_close;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
}
