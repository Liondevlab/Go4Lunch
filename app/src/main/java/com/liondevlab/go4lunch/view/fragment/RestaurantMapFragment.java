package com.liondevlab.go4lunch.view.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.liondevlab.go4lunch.R;
import com.liondevlab.go4lunch.databinding.FragmentChatBinding;
import com.liondevlab.go4lunch.databinding.FragmentRestaurantMapBinding;
import com.liondevlab.go4lunch.viewmodel.RestaurantMapViewModel;

public class RestaurantMapFragment extends Fragment implements OnMapReadyCallback {

	private GoogleMap mGoogleMap;

	private RestaurantMapViewModel mRestaurantMapViewModel;

	public static RestaurantMapFragment newInstance() {
		return new RestaurantMapFragment();
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		mRestaurantMapViewModel = new ViewModelProvider(this).get(RestaurantMapViewModel.class);
		// Obtain the SupportMapFragment and get notified when the map is ready to be used.
		FragmentRestaurantMapBinding fragmentRestaurantMapBinding = FragmentRestaurantMapBinding.inflate(inflater, container, false);
		initGoogleMap();
		return fragmentRestaurantMapBinding.getRoot();
	}

	private void initGoogleMap() {
		SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
		if (mapFragment != null) {
			mapFragment.getMapAsync(this);
		}
	}


	/**
	 * Manipulates the map once available.
	 * This callback is triggered when the map is ready to be used.
	 * This is where we can add markers or lines, add listeners or move the camera. In this case,
	 * we just add a marker near Sydney, Australia.
	 *
	 * If Google Play services is not installed on the device, the user will be prompted to install
	 * it inside the SupportMapFragment. This method will only be triggered once the user has
	 * installed Google Play services and returned to the app.
	 */
	@Override
	public void onMapReady(GoogleMap googleMap) {
		mGoogleMap = googleMap;

		// Add a marker in Sydney and move the camera
		LatLng parisCloud = new LatLng(48.86955265365005, 2.33788665284974);
		mGoogleMap.addMarker(new MarkerOptions()
				.position(parisCloud)
				.title("CLoud Paris"));
		mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(parisCloud));
	}
}