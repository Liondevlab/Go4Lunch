package com.liondevlab.go4lunch.service.WebServices;

import com.liondevlab.go4lunch.model.Places.GoogleResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 18/11/2021
 */
public interface RetrofitAPI {

	@GET
	Call<GoogleResponseModel> getNearByPlaces(@Url String url);
}
