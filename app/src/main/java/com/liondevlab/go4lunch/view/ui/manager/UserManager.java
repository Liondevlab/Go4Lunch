package com.liondevlab.go4lunch.view.ui.manager;

import com.google.android.gms.tasks.Task;
import android.content.Context;
import com.google.firebase.auth.FirebaseUser;
import com.liondevlab.go4lunch.model.User;
import com.liondevlab.go4lunch.view.ui.repository.UserRepository;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 15/09/2021
 */
public class UserManager {

	private static volatile UserManager instance;
	private UserRepository userRepository;

	private UserManager() {
		userRepository = UserRepository.getInstance();
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
		return userRepository.getCurrentUser();
	}

	public Boolean isCurrentUserLogged() {
		return (this.getCurrentUser() != null);
	}

	public Task<Void> signOut(Context context){
		return userRepository.signOut(context);
	}

	public void createUser() {
		userRepository.createUser();
	}

	public Task<User> getUserData() {
		// Get the user from Firestore and cast it to a model object
		return userRepository.getUserData().continueWith(task -> task.getResult().toObject(User.class));
	}

	public Task<Void> updateUsername(String username) {
		return userRepository.updateUsername(username);
	}

	public void updateIsRestaurantChosen(boolean isRestaurantChosen) {
		userRepository.updateIsRestaurantChosen(isRestaurantChosen);
	}

	public Task<Void> deleteUser(Context context) {
		// Delete the user account from the Auth
		return userRepository.deleteUser(context).addOnCompleteListener(Task -> {
			// Once done, delete the user data from Firestore
			userRepository.deleteUserFromFirestore();
		});
	}
}