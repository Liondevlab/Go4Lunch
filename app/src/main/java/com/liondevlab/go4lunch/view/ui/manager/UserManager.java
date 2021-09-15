package com.liondevlab.go4lunch.view.ui.manager;

import com.google.firebase.auth.FirebaseUser;
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

}