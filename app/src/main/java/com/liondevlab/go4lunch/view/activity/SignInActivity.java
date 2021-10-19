package com.liondevlab.go4lunch.view.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.material.snackbar.Snackbar;
import com.liondevlab.go4lunch.R;
import com.liondevlab.go4lunch.databinding.ActivitySignInBinding;
import com.liondevlab.go4lunch.viewmodel.SignInViewModel;

import java.util.Arrays;
import java.util.List;

public class SignInActivity extends BaseActivity<ActivitySignInBinding> {

	private SignInViewModel mSignInViewModel;

	@Override
	ActivitySignInBinding getViewBinding() {
		return ActivitySignInBinding.inflate(getLayoutInflater());
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mSignInViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(SignInViewModel.class);
		setupListeners();
	}

	private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
			new FirebaseAuthUIActivityResultContract(),
			this::onSignInResult
	);

	private void setupListeners() {
		mSignInViewModel.getUserConnectedLiveData().observe(this, new Observer<Boolean>() {
			@Override
			public void onChanged(Boolean isConnected) {
				if(isConnected) {
					startSearchActivity();
				} else{
					startSigningActivity();
				}
			}
		});
		mSignInViewModel.checkIfUserConnected();
	}

	// Launching Search Activity
	private void startSearchActivity() {
		Intent intent = new Intent(this, MainActivity.class);
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
		Intent signInIntent = AuthUI.getInstance()
				.createSignInIntentBuilder()
				.setTheme(R.style.LoginTheme)
				.setAvailableProviders(providers)
				.setIsSmartLockEnabled(false,true)
				.setLogo(R.drawable.logo_white)
				.build();
		signInLauncher.launch(signInIntent);
	}

	// Method that handles response after SignIn Activity close
	private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {

		IdpResponse response = result.getIdpResponse();
		// SUCCESS
		if (result.getResultCode() == RESULT_OK) {
			createUser();
			showSnackBar(getString(R.string.connection_succeed));
			startSearchActivity();
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

	private void createUser() {
		mSignInViewModel.createUser();
	}

	// Show Snack Bar with a message
	private void showSnackBar(String message){
		Snackbar.make(binding.launchLayout, message, Snackbar.LENGTH_SHORT).show();
	}
}
