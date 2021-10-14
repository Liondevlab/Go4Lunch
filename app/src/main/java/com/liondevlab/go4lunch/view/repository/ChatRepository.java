package com.liondevlab.go4lunch.view.repository;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 14/10/2021
 */
public class ChatRepository {

	private static final String CHAT_COLLECTION = "chats";
	private static final String MESSAGE_COLLECTION = "messages";
	private static volatile ChatRepository instance;

	private ChatRepository() { }

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

	public Query getAllMessageForChat(String chat){
		return this.getChatCollection()
				.document(chat)
				.collection(MESSAGE_COLLECTION)
				.orderBy("dateCreated")
				.limit(50);
	}
}