package com.liondevlab.go4lunch.model.places;

import java.util.List;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 10/12/2021
 */
public class NearbyPlacesResult {
	private String businessStatus;
	private ResponseModel.Geometry geometry;
	private String icon;
	private String name;
	private Boolean permanentlyClosed;
	private List<ResponseModel.Photo> photos = null;
	private String placeId;
	private ResponseModel.PlusCode plusCode;
	private Double rating;
	private String reference;
	private String scope;
	private List<String> types = null;
	private Integer userRatingsTotal;
	private String vicinity;
	private ResponseModel.OpeningHours openingHours;
	private Integer priceLevel;

	public String getBusinessStatus() {
		return businessStatus;
	}

	public void setBusinessStatus(String businessStatus) {
		this.businessStatus = businessStatus;
	}

	public ResponseModel.Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(ResponseModel.Geometry geometry) {
		this.geometry = geometry;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getPermanentlyClosed() {
		return permanentlyClosed;
	}

	public void setPermanentlyClosed(Boolean permanentlyClosed) {
		this.permanentlyClosed = permanentlyClosed;
	}

	public List<ResponseModel.Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<ResponseModel.Photo> photos) {
		this.photos = photos;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public ResponseModel.PlusCode getPlusCode() {
		return plusCode;
	}

	public void setPlusCode(ResponseModel.PlusCode plusCode) {
		this.plusCode = plusCode;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public Integer getUserRatingsTotal() {
		return userRatingsTotal;
	}

	public void setUserRatingsTotal(Integer userRatingsTotal) {
		this.userRatingsTotal = userRatingsTotal;
	}

	public String getVicinity() {
		return vicinity;
	}

	public void setVicinity(String vicinity) {
		this.vicinity = vicinity;
	}

	public ResponseModel.OpeningHours getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(ResponseModel.OpeningHours openingHours) {
		this.openingHours = openingHours;
	}

	public Integer getPriceLevel() {
		return priceLevel;
	}

	public void setPriceLevel(Integer priceLevel) {
		this.priceLevel = priceLevel;
	}
}
