package com.liondevlab.go4lunch.model.Places;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 10/12/2021
 */
public class Close {

	private Integer day;
	private String time;

	public Close(Integer day, String time) {
		this.day = day;
		this.time = time;
	}

	public Close() {

	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
