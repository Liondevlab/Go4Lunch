package com.liondevlab.go4lunch.service;

import androidx.lifecycle.MutableLiveData;

import com.liondevlab.go4lunch.model.ChosenRestaurant;
import java.util.List;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 03/01/2022
 */
public class ChosenRestaurantRepository {

	private final MutableLiveData<List<ChosenRestaurant>> listOfChosenRestaurant = new MutableLiveData<>();

	private static volatile ChosenRestaurantRepository mChosenRestaurantRepository;
	private static final String COLLECTION_NAME = "chosenRestaurant";
	private final FirebaseHelper mFirebaseHelper;

	public static ChosenRestaurantRepository getInstance() {
		if (mChosenRestaurantRepository == null) {
			mChosenRestaurantRepository = new ChosenRestaurantRepository();
		}
		return mChosenRestaurantRepository;
	}

	public ChosenRestaurantRepository() {
		mFirebaseHelper = FirebaseHelper.getInstance();
	}
}
