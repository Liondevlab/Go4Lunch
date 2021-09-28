package com.liondevlab.go4lunch.view.ui;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.Nullable;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.snackbar.Snackbar;
import com.liondevlab.go4lunch.R;
import com.liondevlab.go4lunch.databinding.ActivityMainBinding;
import com.liondevlab.go4lunch.view.ui.manager.UserManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

	private static final int RC_SIGN_IN = 123;
	private UserManager userManager = UserManager.getInstance();

	@Override
	ActivityMainBinding getViewBinding() {
		return ActivityMainBinding.inflate(getLayoutInflater());
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupListeners();
	}

	@Override
	protected void onResume() {
		super.onResume();
		updateLoginButton();
	}

	private void setupListeners() {
		// Login Button
		binding.loginButton.setOnClickListener(view -> {
			if(userManager.isCurrentUserLogged()) {
				startSearchActivity();
			} else{
			startSigningActivity();
		}
		});
	}

	// Launching Search Activity
	private void startSearchActivity() {
		Intent intent = new Intent(this, SearchActivity.class);
		startActivity(intent);
	}

	// Launch Sign-in Activity
	private void startSigningActivity() {
		// Choose authentication providers
		List<AuthUI.IdpConfig> providers = Arrays.asList(
				new AuthUI.IdpConfig.GoogleBuilder().build(),
				new AuthUI.IdpConfig.FacebookBuilder().build(),
				new AuthUI.IdpConfig.EmailBuilder().build());

		// Laucnh the activity
		startActivityForResult(AuthUI.getInstance()
				.createSignInIntentBuilder()
				.setTheme(R.style.LoginTheme)
				.setAvailableProviders(providers)
				.setIsSmartLockEnabled(false,true)
				.setLogo(R.drawable.logo_white)
				.build(),
				RC_SIGN_IN);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		this.handleResponseAfterSignIn(requestCode, resultCode, data);
	}

	// Method that handles response after SignIn Activity close
	private void handleResponseAfterSignIn(int requestCode, int resultCode, Intent data) {

		IdpResponse response = IdpResponse.fromResultIntent(data);
		if (requestCode == RC_SIGN_IN) {
			// SUCCESS
			if (resultCode == RESULT_OK) {
				userManager.createUser();
				showSnackBar(getString(R.string.connection_succeed));
			} else {
				// ERRORS
				if (response == null) {
					showSnackBar(getString(R.string.error_authentication_canceled));
				} else if (response.getError()!= null) {
					if(response.getError().getErrorCode() == ErrorCodes.NO_NETWORK){
						showSnackBar(getString(R.string.error_no_internet));
					} else if (response.getError().getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
						showSnackBar(getString(R.string.error_unknown_error));
					}
				}
			}
		}
	}

	// Show Snack Bar with a message
	private void showSnackBar(String message){
		Snackbar.make(binding.mainLayout, message, Snackbar.LENGTH_SHORT).show();
	}

	//Update Login Button when activity is resuming
	private void updateLoginButton(){
		binding.loginButton.setText(userManager.isCurrentUserLogged() ? getString(R.string.button_login_text_logged) : getString(R.string.button_login_text_not_logged));
	}
}