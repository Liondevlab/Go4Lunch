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
import com.liondevlab.go4lunch.viewmodel.RestaurantListFragmentViewModel;

public class RestaurantListFragment extends Fragment {

	private RestaurantListFragmentViewModel mRestaurantListFragmentViewModel;

	public static RestaurantListFragment newInstance() {
		return new RestaurantListFragment();
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_restaurant_list, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mRestaurantListFragmentViewModel = new ViewModelProvider(this).get(RestaurantListFragmentViewModel.class);
		// TODO: Use the ViewModel
	}

}