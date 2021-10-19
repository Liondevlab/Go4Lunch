package com.liondevlab.go4lunch.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.Query;
import com.liondevlab.go4lunch.view.repository.ChatRepository;
import com.liondevlab.go4lunch.view.repository.UserRepository;

public class ChatViewModel extends ViewModel {

	private final MutableLiveData<Query> mMessagesLivedata = new MutableLiveData<>();
	private final UserRepository mUserRepository = UserRepository.getInstance();
	private final ChatRepository mChatRepository = ChatRepository.getInstance();

	public LiveData<Query> getMessagesLivedata() {
		return mMessagesLivedata;
	}

	public Query getAllMessagesForChat() {
		Query getLatestMessages = mChatRepository.getAllMessageForChat();
		mMessagesLivedata.setValue(getLatestMessages);
		return mChatRepository.getAllMessageForChat();
	}

	public void createMessageForChat(String message) {
		mChatRepository.createMessageForChat(message);
	}

	public void initViewModel() {
		//TODO
	}

	// TODO: Implement the ViewModel
}