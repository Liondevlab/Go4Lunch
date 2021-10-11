package com.liondevlab.go4lunch.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.liondevlab.go4lunch.view.manager.UserManager;

import java.util.concurrent.Executor;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 04/10/2021
 */
public class SignInViewModel extends ViewModel {

	private final MutableLiveData<Boolean> mUserConnectedLiveData = new MutableLiveData<>();
	private final UserManager mUserManager = new UserManager();

	public LiveData<Boolean> getUserConnectedLiveData() {
		return mUserConnectedLiveData;
	}

	public void checkIfUserConnected() {
		Boolean isUserConnected = FirebaseAuth.getInstance().getCurrentUser() != null;
		mUserConnectedLiveData.setValue(isUserConnected);
	}

	public void createUser() {
			mUserManager.createUser();
	}

}
