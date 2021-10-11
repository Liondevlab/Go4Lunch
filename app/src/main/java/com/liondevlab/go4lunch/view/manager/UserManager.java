package com.liondevlab.go4lunch.view.manager;

import android.content.Context;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.liondevlab.go4lunch.model.User;
import com.liondevlab.go4lunch.view.repository.UserRepository;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 15/09/2021
 */
public class UserManager {

	private static volatile UserManager instance;
	private UserRepository mUserRepository;

	public UserManager() {
		mUserRepository = UserRepository.getInstance();
	}

	public static UserManager getInstance() {
		UserManager result = instance;
		if (result != null) {
			return result;
		}
		synchronized(UserRepository.class) {
			if (instance == null) {
				instance = new UserManager();
			}
			return instance;
		}
	}

	public FirebaseUser getCurrentUser() {
		return mUserRepository.getCurrentUser();
	}

	public void getAllUsers() {
		mUserRepository.getAllUsers();
	}

	public Boolean isCurrentUserLogged() {
		return (this.getCurrentUser() != null);
	}

	public Task<Void> signOut(Context context){
		return mUserRepository.signOut(context);
	}

	public void createUser() {
		mUserRepository.createUser();
	}

	public Task<User> getUserData() {
		// Get the user from Firestore and cast it to a model object
		return Objects.requireNonNull(mUserRepository.getUserData()).continueWith(task -> task.getResult().toObject(User.class));
	}

	public Task<Void> updateUsername(String username) {
		return mUserRepository.updateUsername(username);
	}

	public void updateIsRestaurantChosen(boolean isRestaurantChosen) {
		mUserRepository.updateIsRestaurantChosen(isRestaurantChosen);
	}

	public Task<Void> deleteUser(Context context) {
		// Delete the user account from the Auth
		return mUserRepository.deleteUser(context).addOnCompleteListener(Task -> {
			// Once done, delete the user data from Firestore
			mUserRepository.deleteUserFromFirestore();
		});
	}
}