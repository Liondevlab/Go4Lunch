package com.liondevlab.go4lunch.view.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liondevlab.go4lunch.R;
import com.liondevlab.go4lunch.viewmodel.RestaurantMapViewModel;

public class RestaurantMapFragment extends Fragment {

	private RestaurantMapViewModel mRestaurantMapViewModel;

	public static RestaurantMapFragment newInstance() {
		return new RestaurantMapFragment();
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_restaurant_map, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mRestaurantMapViewModel = new ViewModelProvider(this).get(RestaurantMapViewModel.class);
		// TODO: Use the ViewModel
	}

}