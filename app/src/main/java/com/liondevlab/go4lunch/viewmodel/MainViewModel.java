package com.liondevlab.go4lunch.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.liondevlab.go4lunch.view.manager.UserManager;
import com.liondevlab.go4lunch.view.repository.UserRepository;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 08/10/2021
 */
public class MainViewModel extends ViewModel {

	private final MutableLiveData<Boolean> mUserConnectedLiveData = new MutableLiveData<>();
	private final UserRepository mUserRepository = new UserRepository();

	public LiveData<Boolean> getUserConnectedLiveData() {
		return mUserConnectedLiveData;
	}

	public void checkIfUserConnected() {
		Boolean isUserConnected = FirebaseAuth.getInstance().getCurrentUser() != null;
		mUserConnectedLiveData.setValue(isUserConnected);
	}
	public FirebaseUser getCurrentUser() {
		return mUserRepository.getCurrentUser();
	}

	public Task<Void> signOut(Context context) {
		return mUserRepository.signOut(context);
	}
}
