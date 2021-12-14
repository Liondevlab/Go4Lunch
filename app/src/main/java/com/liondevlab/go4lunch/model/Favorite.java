package com.liondevlab.go4lunch.model;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 12/10/2021
 */
public class Favorite {

	private String userId;
	private String restaurantId;

	public Favorite(String userId, String restaurantId) {

		this.userId = userId;
		this.restaurantId = restaurantId;
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
