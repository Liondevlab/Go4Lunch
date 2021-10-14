package com.liondevlab.go4lunch.model;

import androidx.annotation.Nullable;

import java.lang.reflect.Type;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 15/09/2021
 */
public class User {

	private String userId;
	private String username;
	@Nullable
	private String urlPicture = "";
	private boolean isRestaurantChosen = false;

	public User() { }

	public User(String userId, String username, @Nullable String urlPicture) {
		this.userId = userId;
		this.username = username;
		this.urlPicture = urlPicture;
	}
	public User(String userId, String username, @Nullable String urlPicture, Boolean isRestaurantChosen) {
		this.userId = userId;
		this.username = username;
		this.urlPicture = urlPicture;
		this.isRestaurantChosen = false;
	}

	// --- GETTERS ---
	public String getUserId() { return userId; }
	public String getUsername() { return username; }
	@Nullable
	public String getUrlPicture() {	return urlPicture; }
	public boolean isRestaurantChosen() { return isRestaurantChosen; }

	// --- SETTERS ---
	public void setUserId(String userId) { this.userId = userId; }
	public void setUsername(String username) { this.username = username; }
	public void setUrlPicture(@Nullable String urlPicture) { this.urlPicture = urlPicture; }
	public void setRestaurantChosen(boolean isRestaurantChosen) { this.isRestaurantChosen = isRestaurantChosen; }
}
