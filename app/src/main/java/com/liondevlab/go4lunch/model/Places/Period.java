package com.liondevlab.go4lunch.model.Places;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 10/12/2021
 */
public class Period {

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
