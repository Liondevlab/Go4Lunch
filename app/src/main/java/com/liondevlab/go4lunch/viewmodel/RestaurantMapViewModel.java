package com.liondevlab.go4lunch.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.model.LatLng;

public class RestaurantMapViewModel extends ViewModel {
	// TODO: Implement the ViewModel
	public final MutableLiveData<LocationModel> deviceLocation = new MutableLiveData<>();

	public void setUserLocation(double latitude, double longitude) {
		deviceLocation.postValue(new LocationModel(new LatLng(latitude, longitude)));
	}

	public LiveData<LocationModel> getDeviceLocation() {
		return deviceLocation;
	}
}