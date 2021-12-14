package com.liondevlab.go4lunch.model;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 12/10/2021
 */
public class ChosenRestaurant {

	private String date;
	private String userId;
	private String restaurantId;

	public ChosenRestaurant(String date, String userId, String restaurantId) {
		this.date = date;
		this.userId = userId;
		this.restaurantId = restaurantId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

}
