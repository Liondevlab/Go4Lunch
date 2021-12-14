package com.liondevlab.go4lunch.model.Places;

import java.util.List;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 10/12/2021
 */
public class AddressComponent {

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
