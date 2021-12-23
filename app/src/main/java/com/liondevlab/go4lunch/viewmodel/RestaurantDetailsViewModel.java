package com.liondevlab.go4lunch.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;
import com.liondevlab.go4lunch.model.Restaurant;
import com.liondevlab.go4lunch.model.User;
import com.liondevlab.go4lunch.service.RestaurantRepository;
import com.liondevlab.go4lunch.service.UserRepository;

import java.util.List;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 04/10/2021
 */
public class RestaurantDetailsViewModel extends ViewModel {

	private final MutableLiveData<Boolean> mUserConnectedLiveData = new MutableLiveData<>();
	private final UserRepository mUserRepository;
	private final RestaurantRepository mRestaurantRepository;

	public RestaurantDetailsViewModel() {
		mRestaurantRepository = RestaurantRepository.getInstance();
		mUserRepository = UserRepository.getInstance();
	}
	public LiveData<User> getCurrentUser() {
		return mUserRepository.getCurrentUser();
	}

	public LiveData<Restaurant> getRestaurant(String restaurantId) {
		return mRestaurantRepository.getRestaurant(restaurantId);
	}

	public LiveData<List<Restaurant>> getRestaurantsList(){
		return mRestaurantRepository.getRestaurantsList();
	}
}
