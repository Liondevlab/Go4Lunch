package com.liondevlab.go4lunch.model;

import com.liondevlab.go4lunch.model.Places.Location;
import com.liondevlab.go4lunch.model.Places.Period;

import java.io.Serializable;
import java.util.List;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 04/10/2021
 */
public class Restaurant implements Serializable {

	String restaurantId;
	String restaurantName;
	String photos;
	String address;
	String website;
	String phoneNumber;
	Location location;
	int distance;
	List<Period> openingHours;
	double rating;

	public Restaurant() {

	}

	public Restaurant(String restaurantId,
	                  String restaurantName,
	                  String photos,
	                  String address,
	                  String website,
	                  String phoneNumber,
	                  Location location,
	                  int distance,
	                  List<Period> openingHours,
	                  double rating) {

		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.photos = photos;
		this.address = address;
		this.website = website;
		this.phoneNumber = phoneNumber;
		this.location = location;
		this.distance = distance;
		this.openingHours = openingHours;
		this.rating = rating;
	}


	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String name) {
		restaurantName = name;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public List<Period> getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(List<Period> openingHours) {
		this.openingHours = openingHours;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

}
