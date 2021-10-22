package com.liondevlab.go4lunch.service;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 21/10/2021
 */
public class FirebaseHelper {
	private static FirebaseHelper sFirebaseHelper;

	private static final String MESSAGE_COLLECTION = "messages";

	public static FirebaseHelper getInstance() {
		if (sFirebaseHelper == null) {
			sFirebaseHelper = new FirebaseHelper();
		}
		return sFirebaseHelper;
	}
	private final FirebaseFirestore db = FirebaseFirestore.getInstance();
	public final CollectionReference messageReference = db.collection(MESSAGE_COLLECTION);

	public Task<QuerySnapshot> getAllMessages() {
		return messageReference.get();
	}
}
