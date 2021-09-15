package com.liondevlab.go4lunch.view.ui;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseUser;
import com.liondevlab.go4lunch.databinding.ActivityProfileBinding;
import com.liondevlab.go4lunch.view.ui.manager.UserManager;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 15/09/2021
 */
public class ProfileActivity extends BaseActivity<ActivityProfileBinding> {

	private UserManager userManager = UserManager.getInstance();

	@Override
	ActivityProfileBinding getViewBinding() {
		return ActivityProfileBinding.inflate(getLayoutInflater());
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupListeners();
		//updateUIWithUserData();
	}

	//TODO
	private void setupListeners(){
		//binding.updateButton.setOnClickListener(view -> { });
		//binding.signOutButton.setOnClickListener(view -> { });
		//binding.deleteButton.setOnClickListener(view -> { });
	}

/*	private void updateUIWithUserData() {
		if (userManager.isCurrentUserLogged()) {
			FirebaseUser user = userManager.getCurrentUser();
			if(user.getPhotoUrl() != null) {
				setProfilePicture(user.getPhotoUrl());
			}
			setTextUserData(user);
		}
	}

	private void setProfilePicture(Uri profilePictureUrl) {
		Glide.with(this)
				.load(profilePictureUrl)
				.apply(RequestOptions.circleCropTransform())
				.into(binding.profileImageView);
	}*/

	private void setTextUserData(FirebaseUser user) {

		//TODO Get user data

		//TODO Update view with user data
	}

}