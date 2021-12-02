package com.liondevlab.go4lunch.model.Places;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 18/11/2021
 */
public class GoogleResponseModel {

	@SerializedName("result")
	@Expose
	private List<GooglePlaceModel> googlePlaceModelList;

	public List<GooglePlaceModel> getGooglePlaceModelList() {
		return googlePlaceModelList;
	}

	public void setGooglePlaceModelList(List<GooglePlaceModel> googlePlaceModelList) {
		this.googlePlaceModelList = googlePlaceModelList;
	}
}
