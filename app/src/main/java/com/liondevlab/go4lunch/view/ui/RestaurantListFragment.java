package com.liondevlab.go4lunch.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.liondevlab.go4lunch.R;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 15/09/2021
 */
public class RestaurantListFragment extends Fragment {

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_restaurant_list, container, false);
	}
}
