package com.liondevlab.go4lunch.view.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseUser;
import com.liondevlab.go4lunch.R;
import com.liondevlab.go4lunch.databinding.ActivityMainBinding;
import com.liondevlab.go4lunch.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

	private ActivityMainBinding mBinding;
	private Toolbar mToolbar;
	private DrawerLayout mDrawerLayout;
	private NavigationView mNavigationView;
	private MainViewModel mMainViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mMainViewModel = new ViewModelProvider(this, ViewModelProvider
				.AndroidViewModelFactory
				.getInstance(getApplication()))
				.get(MainViewModel.class);
		mBinding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(mBinding.getRoot());
		this.configureToolBar();
		this.configureDrawerLayout();
		this.configureNavigationView();

		BottomNavigationView navigationView = findViewById(R.id.main_nav_view);
		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.
		AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
				R.id.navigation_restaurant_map,
				R.id.navigation_restaurant_list,
				R.id.navigation_workmate_list,
				R.id.navigation_chat)
				.build();
		NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment_activity_main);
		NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
		NavigationUI.setupWithNavController(navigationView, navController);
		updateUIWithUserData();
		// Initialize the SDK
		Places.initialize(getApplicationContext(), String.valueOf(R.string.google_places_maps_api_key));

		// Create a new PlacesClient instance
		PlacesClient placesClient = Places.createClient(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_toolbar, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		// 5 - Handle back click to close menu
		if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
			this.mDrawerLayout.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// 4 - Handle Navigation Item Click
		int id = item.getItemId();

		switch (id){
			case R.id.item_your_lunch:
				break;
			case R.id.item_settings:
				break;
			case R.id.item_logout:
				mMainViewModel.signOut(this).addOnSuccessListener(aVoid -> {
					finish();
				});
				break;
			default:
				break;
		}
		this.mDrawerLayout.closeDrawer(GravityCompat.START);
		return true;
	}

	private void configureToolBar() {
		this.mToolbar = findViewById(R.id.main_toolbar);
		setSupportActionBar(mToolbar);
	}

	private void configureDrawerLayout(){
		this.mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_search_for_lunch_drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		mDrawerLayout.addDrawerListener(toggle);
		toggle.syncState();
	}

	// 3 - Configure NavigationView
	private void configureNavigationView(){
		this.mNavigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
		mNavigationView.setNavigationItemSelectedListener(this);
	}

	private void updateUIWithUserData() {
		FirebaseUser user = mMainViewModel.getCurrentUser();
		setProfilePicture(user.getPhotoUrl());
		setTextUserData(user);
	}

	private void setProfilePicture(Uri photoUrl) {
		mNavigationView = findViewById(R.id.activity_main_nav_view);
		View header = mNavigationView.getHeaderView(0);
		ImageView profileImageView = header.findViewById(R.id.profile_image_view);
		if (photoUrl != null) {
			Glide.with(this).load(photoUrl).apply(RequestOptions.circleCropTransform()).into(profileImageView);
		} else {
			profileImageView.setColorFilter(R.color.white);
		}
	}

	private void setTextUserData(FirebaseUser user) {
		mNavigationView = findViewById(R.id.activity_main_nav_view);
		View header = mNavigationView.getHeaderView(0);
		TextView emailTextView = header.findViewById(R.id.profile_user_mail);
		TextView usernameTextView = header.findViewById(R.id.profile_username);
		String email = TextUtils.isEmpty(user.getEmail()) ? getString(R.string.info_no_email_found) : user.getEmail();
		String username = TextUtils.isEmpty(user.getDisplayName()) ? getString(R.string.info_no_username_found) : user.getDisplayName();

		emailTextView.setText(email);
		usernameTextView.setText(username);
	}
}