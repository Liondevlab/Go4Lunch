package com.liondevlab.go4lunch.view.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.liondevlab.go4lunch.R;
import com.liondevlab.go4lunch.databinding.ActivitySearchBinding;
import com.liondevlab.go4lunch.view.ui.manager.UserManager;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 15/09/2021
 */
public class SearchActivity extends BaseActivity<ActivitySearchBinding> implements NavigationView.OnNavigationItemSelectedListener {

	//FOR DESIGN
	private Toolbar mToolbar;
	private DrawerLayout mDrawerLayout;
	private NavigationView mNavigationView;

	private UserManager userManager = UserManager.getInstance();

	@Override
	ActivitySearchBinding getViewBinding() {
		return ActivitySearchBinding.inflate(getLayoutInflater());
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupListeners();

		this.configureToolBar();

		this.configureDrawerLayout();

		this.configureNavigationView();
	}

	@Override
	public void onBackPressed() {
		if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
			this.mDrawerLayout.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}


	@SuppressLint("NonConstantResourceId")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {

		int id = item.getItemId();
		switch (id){
			case R.id.item_your_lunch:
				//TODO
				break;
			case R.id.item_settings:
				//TODO
				break;
			case R.id.item_logout:
				userManager.signOut(this).addOnSuccessListener(aVoid -> {
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
		this.mToolbar = (Toolbar) findViewById(R.id.search_toolbar);
		setSupportActionBar(mToolbar);
	}

	private void configureDrawerLayout() {
		this.mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_search_drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		mDrawerLayout.addDrawerListener(toggle);
		toggle.syncState();
	}

	private void configureNavigationView() {
		this.mNavigationView = (NavigationView) findViewById(R.id.profile_view);
		mNavigationView.setNavigationItemSelectedListener(this);
	}

	//TODO
	private void setupListeners(){
		binding.searchToolbar.findViewById(R.id.item_profile_menu).setOnClickListener(view -> { });
		binding.bottomNavigation.findViewById(R.id.nav_restaurant_map).setOnClickListener(view -> { });
		binding.bottomNavigation.findViewById(R.id.nav_restaurant_list).setOnClickListener(view -> { });
		binding.bottomNavigation.findViewById(R.id.nav_workmates_list).setOnClickListener(view -> { });
	}

}
