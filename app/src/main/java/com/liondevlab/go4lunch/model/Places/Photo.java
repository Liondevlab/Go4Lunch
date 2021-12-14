package com.liondevlab.go4lunch.model.Places;

import java.util.List;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 10/12/2021
 */
public class Photo {

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
