package com.liondevlab.go4lunch.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.Query;
import com.liondevlab.go4lunch.model.Message;
import com.liondevlab.go4lunch.view.item.MessageStateItem;
import com.liondevlab.go4lunch.service.ChatRepository;
import com.liondevlab.go4lunch.service.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class ChatViewModel extends ViewModel {

	private final MutableLiveData<Query> mMessagesLivedata = new MutableLiveData<>();
	private final UserRepository mUserRepository = UserRepository.getInstance();
	private final ChatRepository mChatRepository = ChatRepository.getInstance();

	private LiveData<List<MessageStateItem>> mapMessageDataToViewState(LiveData<List<Message>> messages) {
		return Transformations.map(messages, message -> {
			List<MessageStateItem> messageViewStateItems = new ArrayList<>();
			for (Message m : message) {
				messageViewStateItems.add(
						new MessageStateItem(m)
				);
			}
			return messageViewStateItems;
		});
	}

	public LiveData<List<MessageStateItem>> getAllMessages() {
		return mapMessageDataToViewState(mChatRepository.getAllMessageForChat());
	}

	public void createMessageForChat(String message) {
		mChatRepository.createMessageForChat(message);
	}

}