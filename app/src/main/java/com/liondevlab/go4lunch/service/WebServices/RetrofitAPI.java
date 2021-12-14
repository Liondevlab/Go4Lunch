package com.liondevlab.go4lunch.service.WebServices;

import com.liondevlab.go4lunch.model.Places.NearbyPlaces;
import com.liondevlab.go4lunch.model.Places.PlaceDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 18/11/2021
 */
public interface RetrofitAPI {

	@GET("nearbysearch/json")
	Call<NearbyPlaces> getNearbyPlaces(
			@Query("location") String location,
			@Query("radius") String radius,
			@Query("type") String type,
			@Query("key") String key
	);

	@GET("details/json")
	Call<PlaceDetails> getPlacesDetails(
			@Query("key") String key,
			@Query("place_id") String placeId);

	@GET("nearbysearch/json")
	Call<NearbyPlaces> getNextPageToken(
			@Query("pagetoken") String page,
			@Query("key") String key);
}
