package com.liondevlab.go4lunch.model.places;

import java.util.List;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 16/12/2021
 */
public class ResponseModel {


	/**
	 * Go4Lunch
	 * Created by LioNDeVLaB on 10/12/2021
	 */
	public static class PlusCode {

		private String compoundCode;
		private String globalCode;

		public String getCompoundCode() {
			return compoundCode;
		}

		public void setCompoundCode(String compoundCode) {
			this.compoundCode = compoundCode;
		}

		public String getGlobalCode() {
			return globalCode;
		}

		public void setGlobalCode(String globalCode) {
			this.globalCode = globalCode;
		}

	}

	/**
	 * Go4Lunch
	 * Created by LioNDeVLaB on 10/12/2021
	 */
	public static class Geometry {

		private Location location;
		private Viewport viewport;

		public Location getLocation() {
			return location;
		}

		public void setLocation(Location location) {
			this.location = location;
		}

		public Viewport getViewport() {
			return viewport;
		}

		public void setViewport(Viewport viewport) {
			this.viewport = viewport;
		}

	}

	/**
	 * Go4Lunch
	 * Created by LioNDeVLaB on 10/12/2021
	 */
	public static class Southwest {

		private Double lat;
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

	/**
	 * Go4Lunch
	 * Created by LioNDeVLaB on 10/12/2021
	 */
	public static class PlaceDetails {

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

	/**
	 * Go4Lunch
	 * Created by LioNDeVLaB on 10/12/2021
	 */
	public static class Viewport {

		private Northeast northeast;
		private Southwest southwest;

		public Northeast getNortheast() {
			return northeast;
		}

		public void setNortheast(Northeast northeast) {
			this.northeast = northeast;
		}

		public Southwest getSouthwest() {
			return southwest;
		}

		public void setSouthwest(Southwest southwest) {
			this.southwest = southwest;
		}

	}

	/**
	 * Go4Lunch
	 * Created by LioNDeVLaB on 10/12/2021
	 */
	public static class Period {

		private Close close;
		private Open open;

		public Period(Close close, Open open) {
			this.close = close;
			this.open = open;
		}

		public Period() {

		}

		public Close getClose() {
			return close;
		}

		public void setClose(Close close) {
			this.close = close;
		}

		public Open getOpen() {
			return open;
		}

		public void setOpen(Open open) {
			this.open = open;
		}

	}

	/**
	 * Go4Lunch
	 * Created by LioNDeVLaB on 10/12/2021
	 */
	public static class Location {

		private Double lat;
		private Double lng;

		public Location(Double lat, Double lng) {
			this.lat = lat;
			this.lng = lng;
		}

		public Location() {
		}

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

	/**
	 * Go4Lunch
	 * Created by LioNDeVLaB on 10/12/2021
	 */
	public static class Open {

		private Integer day;
		private String time;

		public Open(Integer day, String time) {
			this.day = day;
			this.time = time;
		}

		public Open() {

		}

		public Integer getDay() {
			return day;
		}

		public void setDay(Integer day) {
			this.day = day;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

	}

	/**
	 * Go4Lunch
	 * Created by LioNDeVLaB on 10/12/2021
	 */
	public static class Northeast {

		private Double lat;
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

	/**
	 * Go4Lunch
	 * Created by LioNDeVLaB on 10/12/2021
	 */
	public static class Photo {

		private Integer height;
		private List<String> htmlAttributions = null;
		private String photoReference;
		private Integer width;

		public Integer getHeight() {
			return height;
		}

		public void setHeight(Integer height) {
			this.height = height;
		}

		public List<String> getHtmlAttributions() {
			return htmlAttributions;
		}

		public void setHtmlAttributions(List<String> htmlAttributions) {
			this.htmlAttributions = htmlAttributions;
		}

		public String getPhotoReference() {
			return photoReference;
		}

		public void setPhotoReference(String photoReference) {
			this.photoReference = photoReference;
		}

		public Integer getWidth() {
			return width;
		}

		public void setWidth(Integer width) {
			this.width = width;
		}

	}

	/**
	 * Go4Lunch
	 * Created by LioNDeVLaB on 10/12/2021
	 */
	public static class Review {

		private String authorName;
		private String authorUrl;
		private String language;
		private String profilePhotoUrl;
		private Integer rating;
		private String relativeTimeDescription;
		private String text;
		private Integer time;

		public String getAuthorName() {
			return authorName;
		}

		public void setAuthorName(String authorName) {
			this.authorName = authorName;
		}

		public String getAuthorUrl() {
			return authorUrl;
		}

		public void setAuthorUrl(String authorUrl) {
			this.authorUrl = authorUrl;
		}

		public String getLanguage() {
			return language;
		}

		public void setLanguage(String language) {
			this.language = language;
		}

		public String getProfilePhotoUrl() {
			return profilePhotoUrl;
		}

		public void setProfilePhotoUrl(String profilePhotoUrl) {
			this.profilePhotoUrl = profilePhotoUrl;
		}

		public Integer getRating() {
			return rating;
		}

		public void setRating(Integer rating) {
			this.rating = rating;
		}

		public String getRelativeTimeDescription() {
			return relativeTimeDescription;
		}

		public void setRelativeTimeDescription(String relativeTimeDescription) {
			this.relativeTimeDescription = relativeTimeDescription;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public Integer getTime() {
			return time;
		}

		public void setTime(Integer time) {
			this.time = time;
		}

	}

	/**
	 * Go4Lunch
	 * Created by LioNDeVLaB on 10/12/2021
	 */
	public static class OpeningHours {

		private Boolean openNow;
		private List<Period> periods = null;
		private List<String> weekdayText = null;

		public Boolean getOpenNow() {
			return openNow;
		}

		public void setOpenNow(Boolean openNow) {
			this.openNow = openNow;
		}

		public List<Period> getPeriods() {
			return periods;
		}

		public void setPeriods(List<Period> periods) {
			this.periods = periods;
		}

		public List<String> getWeekdayText() {
			return weekdayText;
		}

		public void setWeekdayText(List<String> weekdayText) {
			this.weekdayText = weekdayText;
		}

	}

	/**
	 * Go4Lunch
	 * Created by LioNDeVLaB on 10/12/2021
	 */
	public static class Close {

		private Integer day;
		private String time;

		public Close(Integer day, String time) {
			this.day = day;
			this.time = time;
		}

		public Close() {

		}

		public Integer getDay() {
			return day;
		}

		public void setDay(Integer day) {
			this.day = day;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

	}

	/**
	 * Go4Lunch
	 * Created by LioNDeVLaB on 10/12/2021
	 */
	public static class AddressComponent {

		private String longName;
		private String shortName;
		private List<String> types = null;

		public String getLongName() {
			return longName;
		}

		public void setLongName(String longName) {
			this.longName = longName;
		}

		public String getShortName() {
			return shortName;
		}

		public void setShortName(String shortName) {
			this.shortName = shortName;
		}

		public List<String> getTypes() {
			return types;
		}

		public void setTypes(List<String> types) {
			this.types = types;
		}

	}
}
