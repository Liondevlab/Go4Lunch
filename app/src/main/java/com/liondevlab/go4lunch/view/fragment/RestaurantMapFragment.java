package com.liondevlab.go4lunch.view.fragment;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import static com.liondevlab.go4lunch.service.Utility.sLatLng;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.liondevlab.go4lunch.R;
import com.liondevlab.go4lunch.databinding.FragmentRestaurantMapBinding;
import com.liondevlab.go4lunch.model.Restaurant;
import com.liondevlab.go4lunch.model.places.ResponseModel;
import com.liondevlab.go4lunch.view.activity.RestaurantDetailsActivity;
import com.liondevlab.go4lunch.viewmodel.RestaurantMapViewModel;

import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;


@RuntimePermissions
public class RestaurantMapFragment extends Fragment implements OnMapReadyCallback{

	private GoogleMap mGoogleMap;
	private FusedLocationProviderClient mFusedLocationProviderClient;
    private LatLng mLatLng;
	private RestaurantMapViewModel mRestaurantMapViewModel;

	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		// Obtain the SupportMapFragment and get notified when the map is ready to be used.
		FragmentRestaurantMapBinding fragmentRestaurantMapBinding = FragmentRestaurantMapBinding.inflate(inflater, container, false);
		mRestaurantMapViewModel = new ViewModelProvider(requireActivity()).get(RestaurantMapViewModel.class);
		return fragmentRestaurantMapBinding.getRoot();
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initGoogleMap();
	}

	public void initGoogleMap() {
		SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
		assert supportMapFragment != null;
		supportMapFragment.getMapAsync(this);
		// Initialize Fused Location
		mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.requireContext());
	}

	@SuppressLint("MissingPermission")
	@NeedsPermission({ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_WIFI_STATE})
	public void getCurrentLocation() {
		mGoogleMap.setMyLocationEnabled(true);
		//Initialize Task Location
		@SuppressLint("MissingPermission") Task<Location> task = mFusedLocationProviderClient.getLastLocation();
		task.addOnSuccessListener(new OnSuccessListener<Location>() {
			@Override
			public void onSuccess(Location location) {
				mLatLng = new LatLng(location.getLatitude()
						, location.getLongitude());
				sLatLng = mLatLng;
				//Zoom Map
				mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 15));
				getRestaurants();
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
	}

	@Override
	public void onMapReady(@NonNull GoogleMap googleMap) {
		mGoogleMap = googleMap;
		RestaurantMapFragmentPermissionsDispatcher.getCurrentLocationWithPermissionCheck(this);
		updateMapUI();
	}

	private void updateMapUI() {
		mGoogleMap.getUiSettings().setMapToolbarEnabled(false);
		mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
	}

	private void getRestaurants() {
		mRestaurantMapViewModel.getRestaurantsList().observe(getViewLifecycleOwner(), this::setMarkers);
	}

	private void setMarkers(List<Restaurant> restaurantList) {
		mGoogleMap.clear();
		if (getContext() != null) {
			for (Restaurant restaurant : restaurantList) {
				ResponseModel.Location location = restaurant.getLocation();
				LatLng latLng = new LatLng(location.getLat(), location.getLng());
				Bitmap bitmap;
				bitmap = getCustomIcon(getContext(), R.drawable.ic_baseline_location_red_24);
				mGoogleMap.addMarker(new MarkerOptions()
						.position(latLng)
						.icon(BitmapDescriptorFactory.fromBitmap(bitmap))
						.title(restaurant.getRestaurantName()));
			}
		}
		mGoogleMap.setOnInfoWindowClickListener(marker -> {
			for (Restaurant restaurant : restaurantList) {
				if (restaurant.getRestaurantName().equals(marker.getTitle())) {
					Intent intent = new Intent(this.getContext(), RestaurantDetailsActivity.class);
					intent.putExtra(RestaurantDetailsActivity.EXTRA_RESTAURANT, restaurant.getRestaurantId());
					this.requireContext().startActivity(intent);
				}
			}
		});

	}

	private Bitmap getCustomIcon(Context context, int drawableId) {
		Drawable drawable = ContextCompat.getDrawable(context, drawableId);
		assert drawable != null;
		Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight()
				,Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0,0, canvas.getWidth(),canvas.getHeight());
		drawable.draw(canvas);

		return bitmap;
	}
}