package com.liondevlab.go4lunch.service;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.liondevlab.go4lunch.model.Message;
import com.liondevlab.go4lunch.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 14/10/2021
 */
public class ChatRepository {

	private final MutableLiveData<List<Message>> listOfMessage = new MutableLiveData<>();

	private static final String MESSAGE_COLLECTION = "messages";
	private static volatile ChatRepository instance;
	private final UserRepository mUserRepository;
	private final FirebaseHelper mFirebaseHelper;

	private ChatRepository() {
		mFirebaseHelper = FirebaseHelper.getInstance();
		this.mUserRepository = UserRepository.getInstance();
	}

	public static ChatRepository getInstance() {
		ChatRepository result = instance;
		if (result != null) {
			return result;
		}
		synchronized(ChatRepository.class) {
			if (instance == null) {
				instance = new ChatRepository();
			}
			return instance;
		}
	}

	public CollectionReference getChatCollection(){
		return FirebaseFirestore.getInstance().collection(MESSAGE_COLLECTION);
	}

	public MutableLiveData<List<Message>> getAllMessageForChat(){
		// TODO Sort by date and limit number of message to 50 in RecyclerView
		mFirebaseHelper.getAllMessages().addOnCompleteListener(task -> {
			if (task.isSuccessful()) {
				ArrayList<Message> messages = new ArrayList<>();
				for (QueryDocumentSnapshot document : task.getResult()) {
					messages.add(document.toObject(Message.class));
				}
				listOfMessage.postValue(messages);
			} else {
				Log.d("Error", "Error getting documents: ", task.getException());
			}
		}).addOnFailureListener(new OnFailureListener() {
			@Override
			public void onFailure(@NonNull Exception e) {
				//Handle error
				listOfMessage.postValue(null);
			}
		});
		return listOfMessage;
	}

	public void createMessageForChat(String textMessage) {
		mUserRepository.getUserData().addOnSuccessListener(userSnapshot -> {
			User user = userSnapshot.toObject(User.class);
			Message message = new Message(textMessage, user);
			this.getChatCollection().document().collection(MESSAGE_COLLECTION).add(message);
		});
	}

}
