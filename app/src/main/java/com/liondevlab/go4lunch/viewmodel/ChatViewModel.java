package com.liondevlab.go4lunch.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.Query;
import com.liondevlab.go4lunch.view.repository.ChatRepository;

public class ChatViewModel extends ViewModel {

	private ChatRepository mChatRepository;

	private ChatViewModel() {
		mChatRepository = ChatRepository.getInstance();
	}

	public Query getAllMessagesForChat(String chat) {
		return mChatRepository.getAllMessageForChat(chat);
	}
	// TODO: Implement the ViewModel
}