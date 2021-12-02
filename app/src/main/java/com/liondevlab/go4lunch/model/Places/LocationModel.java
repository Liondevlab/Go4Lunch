package com.liondevlab.go4lunch.model.Places;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 18/11/2021
 */
public class LocationModel {

	@SerializedName("lat")
	@Expose
	private Double lat;

	@SerializedName("lng")
	@Expose
	private Double lng;

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}
}
