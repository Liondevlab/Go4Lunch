package com.liondevlab.go4lunch.viewmodel;

import android.content.Context;

import androidx.annotation.Nullable;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.liondevlab.go4lunch.model.Workmate;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 15/09/2021
 */
public final class UserRepository {

	private static volatile UserRepository instance;
	private static final String COLLECTION_NAME = "users";
	private static final String USERNAME_FIELD = "username";
	private static final String IS_RESTAURANT_CHOSEN_FIELD = "isRestaurantChosen";

	private UserRepository() { }

	public static UserRepository getInstance() {
		UserRepository result = instance;
		if (result != null) {
			return result;
		}
		synchronized(UserRepository.class) {
			if (instance == null) {
				instance = new UserRepository();
			}
			return instance;
		}
	}

	@Nullable
	public FirebaseUser getCurrentUser() {
		return FirebaseAuth.getInstance().getCurrentUser();
	}

	@Nullable
	public String getCurrentUserUID() {
		FirebaseUser user = getCurrentUser();
		return (user != null)? user.getUid() : null;
	}

	// Get the Collection Reference
	private CollectionReference getUsersCollection() {
		return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
	}

	// Create User in Firestore
	public void createUser() {
		FirebaseUser user = getCurrentUser();
		if(user != null) {
			String urlPicture = (user.getPhotoUrl() != null) ? user.getPhotoUrl().toString() : null;
			String username = user.getDisplayName();
			String uid = user.getUid();

			Workmate userToCreate = new Workmate(uid, username, urlPicture);

			Task<DocumentSnapshot> userData = getUserData();
			// If the user already exist in Firestore, we get his data (isRestaurantChosen)
			assert userData != null;
			userData.addOnSuccessListener(documentSnapshot -> {
				if (documentSnapshot.contains(IS_RESTAURANT_CHOSEN_FIELD)) {
					userToCreate.setRestaurantChosen((boolean) documentSnapshot.get(IS_RESTAURANT_CHOSEN_FIELD));
				}
				this.getUsersCollection().document(uid).set(userToCreate);
			});
		}
	}

	// Get User Data from Firestore
	public Task<DocumentSnapshot> getUserData(){
		String uid = this.getCurrentUserUID();
		if(uid != null){
			return this.getUsersCollection().document(uid).get();
		}else{
			return null;
		}
	}

	// Update User Username
	public Task<Void> updateUsername(String username) {
		String uid = this.getCurrentUserUID();
		if(uid != null){
			return this.getUsersCollection().document(uid).update(USERNAME_FIELD, username);
		}else{
			return null;
		}
	}

	// Update User isRestaurantChosen
	public void updateIsRestaurantChosen(Boolean isRestaurantChosen) {
		String uid = this.getCurrentUserUID();
		if(uid != null){
			this.getUsersCollection().document(uid).update(IS_RESTAURANT_CHOSEN_FIELD, isRestaurantChosen);
		}
	}

	// Delete the User from Firestore
	public void deleteUserFromFirestore() {
		String uid = this.getCurrentUserUID();
		if(uid != null){
			this.getUsersCollection().document(uid).delete();
		}
	}
	public Task<Void> signOut(Context context){
		return AuthUI.getInstance().signOut(context);
	}

	public Task<Void> deleteUser(Context context){
		return AuthUI.getInstance().delete(context);
	}

}