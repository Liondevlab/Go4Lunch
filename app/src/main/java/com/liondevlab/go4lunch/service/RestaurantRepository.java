package com.liondevlab.go4lunch.service;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.liondevlab.go4lunch.R;
import com.liondevlab.go4lunch.model.places.NearbyPlaces;
import com.liondevlab.go4lunch.model.places.NearbyPlacesResult;
import com.liondevlab.go4lunch.model.places.NearbyPlacesResultDetails;
import com.liondevlab.go4lunch.model.Restaurant;
import com.liondevlab.go4lunch.model.User;
import com.liondevlab.go4lunch.model.places.ResponseModel;
import com.liondevlab.go4lunch.service.WebServices.RetrofitAPI;
import com.liondevlab.go4lunch.service.WebServices.RetrofitClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 17/11/2021
 */
public class RestaurantRepository {

	private static volatile RestaurantRepository mRestaurantRepository;
	RetrofitAPI mRetrofitApi = RetrofitClient.getRetrofitClient().create(RetrofitAPI.class);
	FirebaseHelper mFirebaseHelper;

	public static RestaurantRepository getInstance() {
		RestaurantRepository result = mRestaurantRepository;
		if (mRestaurantRepository == null) {
			mRestaurantRepository = new RestaurantRepository();
		}
		return mRestaurantRepository;
	}

	public RestaurantRepository() {
		mFirebaseHelper = FirebaseHelper.getInstance();
	}

	public LiveData<Restaurant> getRestaurant(String id) {
		MutableLiveData<Restaurant> data = new MutableLiveData<>();
		mFirebaseHelper.restaurantReference.document(id).get().addOnCompleteListener(task -> {
			if (task.isSuccessful()) {
				Restaurant restaurant = task.getResult().toObject(Restaurant.class);
				data.setValue(restaurant);
			}
		});
		return data;
	}

	public LiveData<List<Restaurant>> getRestaurantsList() {
		MutableLiveData<List<Restaurant>> data = new MutableLiveData<>();
		getFirestoreCollections(data, false, null);
		return data;
	}

	public LiveData<List<Restaurant>> getFilteredRestaurantList(String input) {
		MutableLiveData<List<Restaurant>> data = new MutableLiveData<>();
		getFirestoreCollections(data, true, input);
		return data;
	}

	public void getFirestoreCollections(MutableLiveData<List<Restaurant>> data, boolean isFiltered, String input) {
		// TODO change user by choice
		List<User> workmatesList = new ArrayList<>();
		List<Restaurant> restaurantsList = new ArrayList<>();
		mFirebaseHelper.userReference.get().addOnCompleteListener(taskWorkmate -> {
			for (QueryDocumentSnapshot documentSnapshot : taskWorkmate.getResult()) {
				User workmate = documentSnapshot.toObject(User.class);
				workmatesList.add(workmate);
			}
			mFirebaseHelper.restaurantReference.get().addOnCompleteListener(taskRestaurant -> {
				for (QueryDocumentSnapshot documentSnapshot : taskRestaurant.getResult()) {
					Restaurant restaurant = documentSnapshot.toObject(Restaurant.class);
				}

				if (restaurantsList.isEmpty() && Utility.sLatLng != null) {
					getPlaces(Utility.sLatLng.latitude, Utility.sLatLng.longitude, restaurantsList, data);
				} else {
					if (isFiltered) {
						//TODO
					} else {
						data.setValue(restaurantsList);
					}
				}
			});
		});
	}



	//FILTER --------------------
	//TODO
	//---------------------------

	public void getPlaces(Double latitude, Double longitude, List<Restaurant> restaurantList, MutableLiveData<List<Restaurant>> data) {
		List<NearbyPlacesResult> placesResultList = new ArrayList<>();
		mRetrofitApi.getNearbyPlaces(latitude + "," + longitude,
				"1000", "restaurant",
				RetrofitClient.API_KEY)
				.enqueue(new Callback<NearbyPlaces>() {
					@Override
					public void onResponse(@NonNull Call<NearbyPlaces> call, @NonNull Response<NearbyPlaces> response) {
						String nextPage = Objects.requireNonNull(response.body()).getNextPageToken();
						placesResultList.addAll(response.body().getResults());
						if (nextPage != null) {
							try {
								Thread.sleep(2500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							mRetrofitApi.getNextPageToken(nextPage, String.valueOf(R.string.google_places_maps_api_key)).enqueue(new Callback<NearbyPlaces>() {
								@Override
								public void onResponse(@NonNull Call<NearbyPlaces> call, @NonNull Response<NearbyPlaces> response) {
									placesResultList.addAll(Objects.requireNonNull(response.body()).getResults());
									int counter = placesResultList.size();
									for (NearbyPlacesResult result : placesResultList) {
										counter--;
										getPlacesDetails(result.getPlaceId(), restaurantList, counter, data);
									}
								}
								@Override
								public void onFailure(@NonNull Call<NearbyPlaces> call, @NonNull Throwable t) {
									//TODO
								}
							});
						} else {
							int counter = placesResultList.size();
							for (NearbyPlacesResult result : placesResultList) {
								counter--;
								getPlacesDetails(result.getPlaceId(), restaurantList, counter, data);
							}
						}
					}
					@Override
					public void onFailure(@NonNull Call<NearbyPlaces> call, @NonNull Throwable t) {
						//TODO
					}
				});
	}

	private void getPlacesDetails(String placeId, List<Restaurant> restaurantList, int counter, MutableLiveData<List<Restaurant>> data) {
		mRetrofitApi.getPlacesDetails(RetrofitClient.API_KEY, placeId).enqueue(new Callback<ResponseModel.PlaceDetails>() {
			@Override
			public void onResponse(@NonNull Call<ResponseModel.PlaceDetails> call, @NonNull Response<ResponseModel.PlaceDetails> response) {
				Restaurant restaurant = setRestaurant(Objects.requireNonNull(response.body()).getResult(), placeId);
				if (restaurant != null){
					restaurantList.add(restaurant);
				}

				if (counter == 0) {
					data.setValue(restaurantList);
				}
			}

			@Override
			public void onFailure(@NonNull Call<ResponseModel.PlaceDetails> call, @NonNull Throwable t) {
				//TODO
			}
		});
	}

	public Restaurant setRestaurant(NearbyPlacesResultDetails placeDetails, String restaurantId) {
		String photos = "";
		if (placeDetails.getPhotos() != null && !placeDetails.getPhotos().isEmpty()) {
			photos = RetrofitClient.BASE_URL
					+ RetrofitClient.PHOTOS_URL
					+ placeDetails.getPhotos().get(0).getPhotoReference()
					+ "&key="
					+ RetrofitClient.API_KEY;
		}
		int distance = 0;
		List<ResponseModel.Period> openHours = new ArrayList<>();
		if (placeDetails.getOpeningHours() != null) {
			openHours = placeDetails.getOpeningHours().getPeriods();
		}

		double rating = 0;
		if (placeDetails.getRating() != null) {
			rating = placeDetails.getRating();
		}

		Restaurant restaurant = new Restaurant(
				restaurantId,
				placeDetails.getName(),
				photos,
				placeDetails.getVicinity(),
				placeDetails.getWebsite(),
				placeDetails.getFormattedPhoneNumber(),
				placeDetails.getGeometry().getLocation(),
				distance,
				openHours,
				rating
		);

		if (restaurant.getPhotos() != null && !restaurant.getPhotos().isEmpty()) {
			mFirebaseHelper.restaurantReference.document(restaurant.getRestaurantId()).set(restaurant);
			return restaurant;
		} else {
			return null;
		}
	}

}
