package com.liondevlab.go4lunch.view.fragment;

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
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;
import com.liondevlab.go4lunch.R;
import com.liondevlab.go4lunch.databinding.FragmentChatBinding;
import com.liondevlab.go4lunch.model.Message;
import com.liondevlab.go4lunch.view.adapter.ChatAdapter;
import com.liondevlab.go4lunch.viewmodel.ChatViewModel;

public class ChatFragment extends Fragment {

	private ChatViewModel mChatViewModel;
	private ChatAdapter mChatAdapter;
	private FragmentChatBinding mFragmentChatBinding;


/*	public static ChatFragment newInstance() {
		return new ChatFragment();
	}

	FragmentChatBinding getViewBinding() {
		return FragmentChatBinding.inflate(getLayoutInflater());
	}*/

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mChatViewModel = new ViewModelProvider(requireActivity()).get(ChatViewModel.class);
		mFragmentChatBinding = FragmentChatBinding.inflate(inflater, container, false);
		View rootView = mFragmentChatBinding.getRoot();
		RecyclerView recyclerView = rootView.findViewById(R.id.chat_recyclerView);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		configureRecyclerView();
		recyclerView.setAdapter(mChatAdapter);
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
		mChatViewModel.getAllMessages().observe(getViewLifecycleOwner(), mChatAdapter::submitList);
	}

	private void sendMessage() {
		// Check if message is not null and if user is connected before sending message
		if(!TextUtils.isEmpty(mFragmentChatBinding.chatEditText.getText())) {
			mChatViewModel.createMessageForChat(mFragmentChatBinding.chatEditText.getText().toString());
			mFragmentChatBinding.chatEditText.setText("");
		}
	}


	private FirestoreRecyclerOptions<Message> generateOptionsForAdapter(Query query) {
		return new FirestoreRecyclerOptions.Builder<Message>()
				.setQuery(query, Message.class)
				.setLifecycleOwner(this)
				.build();
	}

	public void onDataChanged() {
		// Show text if RecyclerView is empty
		mFragmentChatBinding.emptyRecyclerView.setVisibility(this.mChatAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
	}

}