package com.liondevlab.go4lunch.view.repository;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.liondevlab.go4lunch.model.Message;
import com.liondevlab.go4lunch.model.User;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 14/10/2021
 */
public class ChatRepository {

	private static final String CHAT_COLLECTION = "chats";
	private static final String MESSAGE_COLLECTION = "messages";
	private static volatile ChatRepository instance;
	private UserRepository mUserRepository;

	private ChatRepository() {
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
		return FirebaseFirestore.getInstance().collection(CHAT_COLLECTION);
	}

	public Query getAllMessageForChat(){
		return this.getChatCollection()
				.document()
				.collection(MESSAGE_COLLECTION)
				.orderBy("dateCreated")
				.limit(50);
	}

	public void createMessageForChat(String textMessage) {
		mUserRepository.getUserData().addOnSuccessListener(userSnapshot -> {
			User user = userSnapshot.toObject(User.class);
			Message message = new Message(textMessage, user);
			this.getChatCollection().document().collection(MESSAGE_COLLECTION).add(message);
		});
	}

}
