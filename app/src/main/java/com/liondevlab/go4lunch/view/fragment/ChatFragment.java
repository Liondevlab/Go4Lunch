package com.liondevlab.go4lunch.view.fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.liondevlab.go4lunch.R;
import com.liondevlab.go4lunch.databinding.FragmentChatBinding;
import com.liondevlab.go4lunch.view.adapter.ChatAdapter;
import com.liondevlab.go4lunch.view.item.MessageStateItem;
import com.liondevlab.go4lunch.viewmodel.ChatViewModel;

import java.util.List;

public class ChatFragment extends Fragment {

	private ChatViewModel mChatViewModel;
	private ChatAdapter mChatAdapter;
	private FragmentChatBinding mFragmentChatBinding;
	private RecyclerView mRecyclerView;

	//TODO adjust pan view and scrool automaticaly down
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mChatViewModel = new ViewModelProvider(requireActivity()).get(ChatViewModel.class);
		mFragmentChatBinding = FragmentChatBinding.inflate(inflater, container, false);
		View rootView = mFragmentChatBinding.getRoot();
		mRecyclerView = rootView.findViewById(R.id.chat_recyclerView);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		configureRecyclerView();
		mRecyclerView.setAdapter(mChatAdapter);
		setupListeners();
		return rootView;
	}

	private void setupListeners() {
		// TODO Livedata for updating message list
		// Chat buttons
		mFragmentChatBinding.addFileButton.setOnClickListener(view -> {});
		mFragmentChatBinding.sendButton.setOnClickListener(view -> { sendMessage(); });
	}

	private void configureRecyclerView() {
		this.mChatAdapter = new ChatAdapter(Glide.with(this));
		mFragmentChatBinding.chatRecyclerView
				.setLayoutManager(new LinearLayoutManager(this.getContext()));
		mFragmentChatBinding.chatRecyclerView
				.setAdapter(this.mChatAdapter);
		getMessageList();
	}

	private void getMessageList() {
		mChatViewModel.getAllMessages().observe(getViewLifecycleOwner(), this::updateChatList);
	}

	private void updateChatList(List<MessageStateItem> messages) {
		Runnable runnable = () -> mRecyclerView.scrollToPosition(messages.size() - 1);
		mChatAdapter.submitList(messages, runnable);
		mFragmentChatBinding.emptyRecyclerView.setVisibility(messages.isEmpty() ? View.VISIBLE : View.GONE);
	}

	private void sendMessage() {
		// Check if message is not null and if user is connected before sending message
		if(!TextUtils.isEmpty(mFragmentChatBinding.chatEditText.getText())) {
			mChatViewModel.createMessageForChat(mFragmentChatBinding.chatEditText.getText().toString());
			mFragmentChatBinding.chatEditText.setText("");
		}
	}
}