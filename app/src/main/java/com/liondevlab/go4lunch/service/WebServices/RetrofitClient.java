package com.liondevlab.go4lunch.service.WebServices;

import com.liondevlab.go4lunch.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 18/11/2021
 */
public class RetrofitClient {

	private static Retrofit retrofit = null;
	public static final String BASE_URL = String.valueOf(R.string.url_googleapis);

	public static Retrofit getRetrofitClient() {
		if (retrofit == null) {
			retrofit = new Retrofit.Builder()
					.baseUrl(BASE_URL)
					.addConverterFactory(ScalarsConverterFactory.create())
					.addConverterFactory(GsonConverterFactory.create())
					.build();
		}
		return retrofit;
	}

}
