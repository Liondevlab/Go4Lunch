package com.liondevlab.go4lunch.view.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liondevlab.go4lunch.R;
import com.liondevlab.go4lunch.model.User;
import com.liondevlab.go4lunch.view.adapter.WorkmatesListAdapter;
import com.liondevlab.go4lunch.view.manager.UserManager;
import com.liondevlab.go4lunch.viewmodel.WorkmateListViewModel;

import java.util.ArrayList;

public class WorkmateListFragment extends Fragment {

	private WorkmateListViewModel mWorkmateListViewModel;
	private UserManager mUserManager;

	private ArrayList<User> mWorkmates = new ArrayList<>();

	private final WorkmatesListAdapter mWorkmatesListAdapter = new WorkmatesListAdapter(mWorkmates);

	private RecyclerView mWorkmateListRecyclerView;

	public static WorkmateListFragment newInstance() {
		return new WorkmateListFragment();
	}


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mWorkmateListViewModel = new ViewModelProvider(this).get(WorkmateListViewModel.class);
		// TODO: Use the ViewModel
		mUserManager.getAllUsers();
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_workmate_list, container, false);
	}

}