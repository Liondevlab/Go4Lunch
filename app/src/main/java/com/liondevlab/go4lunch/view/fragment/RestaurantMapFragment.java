package com.liondevlab.go4lunch.view.fragment;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.liondevlab.go4lunch.R;
import com.liondevlab.go4lunch.databinding.FragmentRestaurantMapBinding;
import com.liondevlab.go4lunch.model.Places.NearbyPlaces;
import com.liondevlab.go4lunch.model.Restaurant;
import com.liondevlab.go4lunch.service.Utility;
import com.liondevlab.go4lunch.service.WebServices.RetrofitAPI;
import com.liondevlab.go4lunch.service.WebServices.RetrofitClient;
import com.liondevlab.go4lunch.viewmodel.RestaurantMapViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RuntimePermissions
public class RestaurantMapFragment extends Fragment implements OnMapReadyCallback{

	private GoogleMap mGoogleMap;
	private SupportMapFragment mSupportMapFragment;
	private FusedLocationProviderClient mFusedLocationProviderClient;
	private Location mLocation;
	double lat, lng;

	private RestaurantMapViewModel mRestaurantMapViewModel;

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		// Obtain the SupportMapFragment and get notified when the map is ready to be used.
		FragmentRestaurantMapBinding fragmentRestaurantMapBinding = FragmentRestaurantMapBinding.inflate(inflater, container, false);
		RestaurantMapFragmentPermissionsDispatcher.initGoogleMapWithPermissionCheck(this);
		mRestaurantMapViewModel = new ViewModelProvider(requireActivity()).get(RestaurantMapViewModel.class);
		initGoogleMap();
		return fragmentRestaurantMapBinding.getRoot();
	}

	@NeedsPermission({ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_WIFI_STATE})
	public void initGoogleMap() {
		mSupportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
		// Initialize Fused Location
		mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.requireContext());
		if (ActivityCompat.checkSelfPermission(this.requireContext()
				, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
			getCurrentLocation();
		} else {
			//If permission denied request for it
			ActivityCompat.requestPermissions(requireActivity(), new String[]{ACCESS_FINE_LOCATION}, 44);
		}
	}

	private void getCurrentLocation() {
		//Initialize Task Location
		@SuppressLint("MissingPermission") Task<Location> task = mFusedLocationProviderClient.getLastLocation();
		task.addOnSuccessListener(new OnSuccessListener<Location>() {
			@Override
			public void onSuccess(Location location) {
				mLocation = location;
				if (mLocation != null) {
					mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
						@Override
						public void onMapReady(@NonNull GoogleMap googleMap) {
							mGoogleMap = googleMap;
							// Initialize coordinates
							Utility.sLatLng = new LatLng(mLocation.getLatitude()
									, mLocation.getLongitude());
							//Create Marker options
							MarkerOptions options = new MarkerOptions().position(Utility.sLatLng)
									.title(getString(R.string.user_location));
							//Zoom Map
							mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Utility.sLatLng, 16));
							//Add marker on map
							mGoogleMap.addMarker(options);
							getRestaurants();
						}
					});
				}
			}
		});
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
		if (requestCode == 44) {
			if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				getCurrentLocation();
			}
		}
	}

	@Override
	public void onMapReady(@NonNull GoogleMap googleMap) {
		mGoogleMap = googleMap;
		updateMapUI();
		//TODO
		mGoogleMap.setOnInfoWindowClickListener(marker -> viewRestaurantDetail((Restaurant) marker.getTag()));
	}

	private void updateMapUI() {
		//TODO

	}

	private void viewRestaurantDetail(Restaurant tag) {
		//TODO

	}

	private void getRestaurants() {
		mRestaurantMapViewModel.getRestaurantsList().observe(getViewLifecycleOwner(), this::setMarkers);
	}

	private void setMarkers(List<Restaurant> restaurantList) {
		//TODO

	}

	private BitmapDescriptor getCustomIcon() {
		int color = ContextCompat.getColor(requireContext(), R.color.quantum_googred900);
		Drawable background = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_location_on_24);
		if (background != null) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
				background.setTint(color);
			} else {
				background.mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN);
			}
		}
		background.setBounds(0,0, background.getIntrinsicWidth(),background.getIntrinsicHeight());
		Bitmap bitmap = Bitmap.createBitmap(background.getIntrinsicWidth(),background.getIntrinsicHeight()
				,Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		background.draw(canvas);
		return BitmapDescriptorFactory.fromBitmap(bitmap);
	}
}