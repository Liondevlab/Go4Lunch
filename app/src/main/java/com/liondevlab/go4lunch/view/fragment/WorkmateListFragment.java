package com.liondevlab.go4lunch.view.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liondevlab.go4lunch.R;
import com.liondevlab.go4lunch.viewmodel.WorkmateListViewModel;

public class WorkmateListFragment extends Fragment {

	private WorkmateListViewModel mWorkmateListViewModel;

	public static WorkmateListFragment newInstance() {
		return new WorkmateListFragment();
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_workmate_list, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mWorkmateListViewModel = new ViewModelProvider(this).get(WorkmateListViewModel.class);
		// TODO: Use the ViewModel
	}

}