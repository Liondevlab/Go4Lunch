package com.liondevlab.go4lunch.view.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.liondevlab.go4lunch.R;
import com.liondevlab.go4lunch.databinding.FragmentChatBinding;
import com.liondevlab.go4lunch.databinding.FragmentWorkmateListBinding;
import com.liondevlab.go4lunch.model.User;
import com.liondevlab.go4lunch.view.adapter.ChatAdapter;
import com.liondevlab.go4lunch.view.adapter.WorkmatesListAdapter;
import com.liondevlab.go4lunch.viewmodel.ChatViewModel;
import com.liondevlab.go4lunch.viewmodel.WorkmateListViewModel;

import java.util.ArrayList;
import java.util.List;

public class WorkmateListFragment extends Fragment {

	private WorkmatesListAdapter mWorkmatesListAdapter;
	private FragmentWorkmateListBinding mFragmentWorkmateListBinding;
	private WorkmateListViewModel mWorkmateListViewModel;

	public static WorkmateListFragment newInstance() {
		return new WorkmateListFragment();
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		mWorkmateListViewModel = new ViewModelProvider(this).get(WorkmateListViewModel.class);
		mFragmentWorkmateListBinding = FragmentWorkmateListBinding.inflate(inflater, container, false);
		View rootView = mFragmentWorkmateListBinding.getRoot();
		configureRecyclerView();

		return rootView;
	}

	private void configureRecyclerView() {

		RecyclerView recyclerView = mFragmentWorkmateListBinding.workmateListRecyclerView;
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		this.mWorkmatesListAdapter = new WorkmatesListAdapter();
		mFragmentWorkmateListBinding.workmateListRecyclerView
				.setLayoutManager(new LinearLayoutManager(this.getContext()));
		mFragmentWorkmateListBinding.workmateListRecyclerView
				.setAdapter(this.mWorkmatesListAdapter);
		recyclerView.setAdapter(mWorkmatesListAdapter);
		getUserList();
	}

	private void getUserList() {
		mWorkmateListViewModel.getAllUsers().observe(getViewLifecycleOwner(), mWorkmatesListAdapter::submitList);
	}
}