package com.liondevlab.go4lunch.view.fragment;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.AlertDialog;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.libraries.places.api.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.api.Context;
import com.liondevlab.go4lunch.R;
import com.liondevlab.go4lunch.databinding.FragmentRestaurantMapBinding;
import com.liondevlab.go4lunch.model.Restaurant;
import com.liondevlab.go4lunch.viewmodel.RestaurantMapViewModel;

import java.util.Collections;
import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class RestaurantMapFragment extends Fragment implements OnMapReadyCallback {

	private GoogleMap mGoogleMap;
	private Location mLocation;
	private LatLng mActualLocation;
	private int mRadius;
	private Context mContext;
	public List<Restaurant> mRestaurantList;

	private RestaurantMapViewModel mRestaurantMapViewModel;

	public static RestaurantMapFragment newInstance() {
		return new RestaurantMapFragment();
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		mRestaurantMapViewModel = new ViewModelProvider(requireActivity()).get(RestaurantMapViewModel.class);
		// Obtain the SupportMapFragment and get notified when the map is ready to be used.
		FragmentRestaurantMapBinding fragmentRestaurantMapBinding = FragmentRestaurantMapBinding.inflate(inflater, container, false);
		RestaurantMapFragmentPermissionsDispatcher.initGooglePlacesWithPermissionCheck(this);
		initGoogleMap();
		initGooglePlaces();
		return fragmentRestaurantMapBinding.getRoot();
	}

	@NeedsPermission({ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_WIFI_STATE})
	public void initGoogleMap() {
		SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
		if (mapFragment != null) {
			mapFragment.getMapAsync(this);
		}
	}

	@NeedsPermission({ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_WIFI_STATE})
	public void initGooglePlaces() {
		// Initialize Places. (Check for Context if good or not)
		Places.initialize(requireActivity().getApplicationContext(), String.valueOf(R.string.google_places_maps_api_key));
		// Create a new Places client instance. (Check for Context if good or not "this" by default doesn't work)
		PlacesClient placesClient = Places.createClient(requireContext());
		// Use fields to define the data types to return.
		List<Place.Field> placeFields = Collections.singletonList(Place.Field.NAME);
		// Use the builder to create a FindCurrentPlaceRequest.
		FindCurrentPlaceRequest request = FindCurrentPlaceRequest.newInstance(placeFields);

	}

	@OnShowRationale({ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_WIFI_STATE})
	void showRationaleForLocation(final PermissionRequest request) {
		new AlertDialog.Builder(requireContext())
				.setMessage(R.string.permission_location_rationale)
				.setPositiveButton(R.string.button_allow, (dialog, button) -> request.proceed())
				.setNegativeButton(R.string.button_deny, (dialog, button) -> request.cancel())
				.show();
	}

	@OnPermissionDenied({ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_WIFI_STATE})
	void showDeniedForLocation() {
		Toast.makeText(getActivity(), R.string.permission_location_denied, Toast.LENGTH_SHORT).show();
	}

	@OnNeverAskAgain({ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_WIFI_STATE})
	void showNeverAskForLocation() {
		Toast.makeText(getActivity(), R.string.permission_location_neverask, Toast.LENGTH_SHORT).show();
	}


	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		// NOTE: delegate the permission handling to generated method
		RestaurantMapFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
	}


	@Override
	public void onMapReady(GoogleMap googleMap) {
		mGoogleMap = googleMap;
		updateLocationOnMap();
	}

	@NeedsPermission({ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_WIFI_STATE})
	public void updateLocationOnMap() {
		mRestaurantMapViewModel.getDeviceLocation().observe(requireActivity(), this::initLocationOnMap);
		mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
		mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
		mGoogleMap.setOnMyLocationButtonClickListener(() -> {
			getDeviceLocation();
			return true;
		});
		mGoogleMap.setOnMyLocationClickListener(location -> getDeviceLocation());
	}
	@NeedsPermission({ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_WIFI_STATE})
	public void initLocationOnMap(LocationModel locationModel) {
		mActualLocation = new LatLng(locationModel.getLocation().latitude, locationModel.getLocation().longitude);
		CameraPosition cameraPosition = CameraPosition.builder().target(mActualLocation).zoom(10).build();
		mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 100, null);
	}

	@NeedsPermission({ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_WIFI_STATE})
	public void getDeviceLocation() {

	}

}