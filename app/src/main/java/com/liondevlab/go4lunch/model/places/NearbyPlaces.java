package com.liondevlab.go4lunch.model.places;

import java.util.List;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 02/12/2021
 */
public class NearbyPlaces {

	private List<Object> htmlAttributions = null;
	private String nextPageToken;
	private List<NearbyPlacesResult> results = null;
	private String status;

	public List<Object> getHtmlAttributions() {
		return htmlAttributions;
	}

	public void setHtmlAttributions(List<Object> htmlAttributions) {
		this.htmlAttributions = htmlAttributions;
	}

	public String getNextPageToken() {
		return nextPageToken;
	}

	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}

	public List<NearbyPlacesResult> getResults() {
		return results;
	}

	public void setResults(List<NearbyPlacesResult> results) {
		this.results = results;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
