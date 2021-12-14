package com.liondevlab.go4lunch.model.Places;

import java.util.List;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 10/12/2021
 */
public class PlaceDetails {

	private List<Object> htmlAttributions = null;
	private NearbyPlacesResultDetails result;
	private String status;

	public List<Object> getHtmlAttributions() {
		return htmlAttributions;
	}

	public void setHtmlAttributions(List<Object> htmlAttributions) {
		this.htmlAttributions = htmlAttributions;
	}

	public NearbyPlacesResultDetails getResult() {
		return result;
	}

	public void setResult(NearbyPlacesResultDetails result) {
		this.result = result;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
