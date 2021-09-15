package com.liondevlab.go4lunch.view.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.liondevlab.go4lunch.databinding.ActivityProfileBinding;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 15/09/2021
 */
public class SearchActivity extends BaseActivity<ActivityProfileBinding> {

	@Override
	ActivityProfileBinding getViewBinding() {
		return ActivityProfileBinding.inflate(getLayoutInflater());
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupListeners();
	}

	private void setupListeners(){
		binding.mapButton.setOnClickListener(view -> { });
		binding.restaurantListButton.setOnClickListener(view -> { });
		binding.userListButton.setOnClickListener(view -> { });
	}
}
