package com.liondevlab.go4lunch.view.activity;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.liondevlab.go4lunch.R;
import com.liondevlab.go4lunch.databinding.ActivityMainBinding;
import com.liondevlab.go4lunch.viewmodel.MainActivityViewModel;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

	private MainActivityViewModel mMainActivityViewModel;
	private FirebaseAuth mFirebaseAuth;
	private FirebaseUser mCurrentUser;

	@Override
	ActivityMainBinding getViewBinding() {
		return ActivityMainBinding.inflate(getLayoutInflater());
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mFirebaseAuth = FirebaseAuth.getInstance();
		setupListeners();
	}


	@Override
	protected void onResume() {
		super.onResume();
		updateLoginButton();
	}

	private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
			new FirebaseAuthUIActivityResultContract(),
			new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
				@Override
				public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
					onSignInResult(result);
				}
			}
	);

	private void setupListeners() {
		// Login Button
		mCurrentUser = mFirebaseAuth.getCurrentUser();
		binding.loginButton.setOnClickListener(view -> {
			if(mCurrentUser != null) {
				startSearchActivity();
			} else{
				startSigningActivity();
			}
		});
	}

	// Launching Search Activity
	private void startSearchActivity() {
		Intent intent = new Intent(this, SearchForLunchActivity.class);
		startActivity(intent);
	}

	// Launch Sign-in Activity
	private void startSigningActivity() {
		// Choose authentication providers
		List<AuthUI.IdpConfig> providers = Arrays.asList(
				new AuthUI.IdpConfig.GoogleBuilder().build(),
				new AuthUI.IdpConfig.FacebookBuilder().build(),
				new AuthUI.IdpConfig.EmailBuilder().build());

		// Launch the activity
		Intent signInIntent = AuthUI.getInstance().createSignInIntentBuilder().setTheme(R.style.LoginTheme).setAvailableProviders(providers).setIsSmartLockEnabled(false,true).setLogo(R.drawable.logo_white).build(),RC_SIGN_IN;
		signInLauncher.launch(signInIntent);
	}

	// Method that handles response after SignIn Activity close
	private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {

		IdpResponse response = result.getIdpResponse();
		// SUCCESS
		if (result.getResultCode() == RESULT_OK) {
			//userManager.createUser();
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

	// Show Snack Bar with a message
	private void showSnackBar(String message){
		Snackbar.make(binding.mainLayout, message, Snackbar.LENGTH_SHORT).show();
	}

	//Update Login Button when activity is resuming
	private void updateLoginButton(){
		mCurrentUser = mFirebaseAuth.getCurrentUser();
		if (mCurrentUser != null) {
			binding.loginButton.setText(getString(R.string.button_login_text_logged));
		} else {
			binding.loginButton.setText(getString(R.string.button_login_text_not_logged));
		}
	}
}
