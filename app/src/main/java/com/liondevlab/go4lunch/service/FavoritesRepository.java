package com.liondevlab.go4lunch.service;

import androidx.lifecycle.MutableLiveData;

import com.liondevlab.go4lunch.model.Favorites;

import java.util.List;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 03/01/2022
 */
public class FavoritesRepository {

	private final MutableLiveData<List<Favorites>> listOfFavorites = new MutableLiveData<>();

	private static volatile FavoritesRepository mFavoritesRepository;
	private static final String COLLECTION_NAME = "favorites";
	private final FirebaseHelper mFirebaseHelper;

	public static FavoritesRepository getInstance() {
		if (mFavoritesRepository == null) {
			mFavoritesRepository = new FavoritesRepository();
		}
		return mFavoritesRepository;
	}

	public FavoritesRepository() {
		mFirebaseHelper = FirebaseHelper.getInstance();
	}
}
