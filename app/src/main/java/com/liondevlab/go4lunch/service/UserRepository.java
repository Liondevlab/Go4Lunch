package com.liondevlab.go4lunch.service;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.liondevlab.go4lunch.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 15/09/2021
 */
public final class UserRepository {

	private final MutableLiveData<List<User>> listOfUser = new MutableLiveData<>();

	private static volatile UserRepository mUserRepository;
	private static final String COLLECTION_NAME = "users";
	private static final String USERNAME_FIELD = "username";
	private static final String IS_RESTAURANT_CHOSEN_FIELD = "isRestaurantChosen";
	private static final String TAG = "UserRepository";
	private final FirebaseHelper mFirebaseHelper;

	public static UserRepository getInstance() {
		UserRepository result = mUserRepository;
		if (mUserRepository == null) {
			mUserRepository = new UserRepository();
		}
			return result;
	}

	public UserRepository() {
		mFirebaseHelper = FirebaseHelper.getInstance();
	}

	@Nullable
	public FirebaseUser getFirebaseCurrentUser() {
		return FirebaseAuth.getInstance().getCurrentUser();
	}

	@Nullable
	public String getCurrentUserUID() {
		FirebaseUser user = getFirebaseCurrentUser();
		return (user != null)? user.getUid() : null;
	}

	public LiveData<User> getCurrentUser() {
		MutableLiveData<User> data = new MutableLiveData<>();
		getUsersCollection().document(Objects.requireNonNull(getCurrentUserUID())).get().addOnCompleteListener(task -> {
			if (task.isSuccessful()) {
				User user = task.getResult().toObject(User.class);
				data.setValue(user);
			}
		});
		return data;
	}

	// Get the Collection Reference
	private CollectionReference getUsersCollection() {
		return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
	}

	// Create User in Firestore
	public void createUser() {
		FirebaseUser user = getFirebaseCurrentUser();
		if(user != null) {
			String urlPicture = (user.getPhotoUrl() != null) ? user.getPhotoUrl().toString() : null;
			String username = user.getDisplayName();
			String uid = user.getUid();

			User userToCreate = new User(uid, username, urlPicture);

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

	public MutableLiveData<List<User>> getAllUsers(){
		mFirebaseHelper.getAllUsers().addOnCompleteListener(task -> {
			if (task.isSuccessful()) {
				ArrayList<User> users = new ArrayList<>();
				for (QueryDocumentSnapshot document : task.getResult()) {
					users.add(document.toObject(User.class));
				}
				listOfUser.postValue(users);
			} else {
				Log.d("Error", "Error getting documents: ", task.getException());
			}
		}).addOnFailureListener(new OnFailureListener() {
			@Override
			public void onFailure(@NonNull Exception e) {
				//Handle error
				listOfUser.postValue(null);
			}
		});
		return listOfUser;
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