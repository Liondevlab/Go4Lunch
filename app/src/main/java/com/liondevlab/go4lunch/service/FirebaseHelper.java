package com.liondevlab.go4lunch.service;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 21/10/2021
 */
public class FirebaseHelper {

	private static FirebaseHelper sFirebaseHelper;

	private static final String MESSAGE_COLLECTION = "messages";
	private static final String USER_COLLECTION = "users";
	private static final String RESTAURANT_COLLECTION = "restaurants";

	public static FirebaseHelper getInstance() {
		if (sFirebaseHelper == null) {
			sFirebaseHelper = new FirebaseHelper();
		}
		return sFirebaseHelper;
	}
	private final FirebaseFirestore db = FirebaseFirestore.getInstance();
	public final CollectionReference messageReference = db.collection(MESSAGE_COLLECTION);
	public final CollectionReference userReference = db.collection(USER_COLLECTION);
	public final CollectionReference restaurantReference = db.collection(RESTAURANT_COLLECTION);

	public Query getMessagesQuery() {
		return messageReference.orderBy("dateCreated");
	}

	public Task<QuerySnapshot> getAllUsers() {
		return userReference.get();
	}

	public Task<QuerySnapshot> getAllRestaurants() {
		return restaurantReference.get();
	}

}
