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
import com.liondevlab.go4lunch.viewmodel.RestaurantListViewModel;

public class RestaurantListFragment extends Fragment {

	private RestaurantListViewModel mRestaurantListViewModel;

	public static RestaurantListFragment newInstance() {
		return new RestaurantListFragment();
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		mRestaurantListViewModel = new ViewModelProvider(this).get(RestaurantListViewModel.class);
		return inflater.inflate(R.layout.fragment_restaurant_list, container, false);
	}

}