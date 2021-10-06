package com.liondevlab.go4lunch.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.liondevlab.go4lunch.R;
import com.liondevlab.go4lunch.databinding.ActivitySearchForLunchBinding;

public class SearchForLunchActivity extends AppCompatActivity {

	private ActivitySearchForLunchBinding mBinding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mBinding = ActivitySearchForLunchBinding.inflate(getLayoutInflater());
		setContentView(mBinding.getRoot());

		BottomNavigationView navigationView = findViewById(R.id.search_for_lunch_nav_view);
		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.
		AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
				R.id.navigation_restaurant_map,
				R.id.navigation_restaurant_list,
				R.id.navigation_workmate_list,
				R.id.navigation_chat)
				.build();
		NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment_activity_search_for_lunch);
		NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
		NavigationUI.setupWithNavController(mBinding.searchForLunchNavView, navController);
	}
}