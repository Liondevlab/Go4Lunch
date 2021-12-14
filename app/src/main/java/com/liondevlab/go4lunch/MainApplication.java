package com.liondevlab.go4lunch;

import android.app.Application;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 14/12/2021
 */
public class MainApplication extends Application {

	private static Application sApplication;

	@Override
	public void onCreate() {
		super.onCreate();
		sApplication = this;
	}

	public static Application getApplication() {
		return sApplication;
	}
}
