package com.liondevlab.go4lunch.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.liondevlab.go4lunch.model.Restaurant;
import com.liondevlab.go4lunch.service.RestaurantRepository;
import com.liondevlab.go4lunch.service.UserRepository;

import java.util.List;

public class RestaurantMapViewModel extends ViewModel {

	private final RestaurantRepository mRestaurantRepository;

	public RestaurantMapViewModel() {
		mRestaurantRepository = RestaurantRepository.getInstance();
	}

	public LiveData<List<Restaurant>> getRestaurantsList(){
		return mRestaurantRepository.getRestaurantsList();
	}

}