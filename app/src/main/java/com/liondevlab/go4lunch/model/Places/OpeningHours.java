package com.liondevlab.go4lunch.model.Places;

import java.util.List;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 10/12/2021
 */
public class OpeningHours {

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
