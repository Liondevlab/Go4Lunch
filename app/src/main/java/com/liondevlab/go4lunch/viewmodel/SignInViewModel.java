package com.liondevlab.go4lunch.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.liondevlab.go4lunch.service.UserRepository;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 04/10/2021
 */
public class SignInViewModel extends ViewModel {

	private final MutableLiveData<Boolean> mUserConnectedLiveData = new MutableLiveData<>();
	private final UserRepository mUserRepository = new UserRepository();

	public LiveData<Boolean> getUserConnectedLiveData() {
		return mUserConnectedLiveData;
	}

	public void checkIfUserConnected() {
		Boolean isUserConnected = FirebaseAuth.getInstance().getCurrentUser() != null;
		mUserConnectedLiveData.setValue(isUserConnected);
	}

	public void createUser() {
			mUserRepository.createUser();
	}

}
