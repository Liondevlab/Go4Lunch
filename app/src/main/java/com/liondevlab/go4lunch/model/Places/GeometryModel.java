package com.liondevlab.go4lunch.model.Places;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 18/11/2021
 */
public class GeometryModel {

	@SerializedName("location")
	@Expose
	private LocationModel location;

	public LocationModel getLocation() {
		return location;
	}

	public void setLocation(LocationModel location) {
		this.location = location;
	}

}
