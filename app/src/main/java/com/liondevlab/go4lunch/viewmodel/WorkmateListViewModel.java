package com.liondevlab.go4lunch.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.Query;
import com.liondevlab.go4lunch.model.Message;
import com.liondevlab.go4lunch.model.User;
import com.liondevlab.go4lunch.service.ChatRepository;
import com.liondevlab.go4lunch.service.UserRepository;
import com.liondevlab.go4lunch.view.item.MessageStateItem;
import com.liondevlab.go4lunch.view.item.UserStateItem;

import java.util.ArrayList;
import java.util.List;

public class WorkmateListViewModel extends ViewModel {

    private final UserRepository mUserRepository;

    public WorkmateListViewModel() {
        mUserRepository = UserRepository.getInstance();
    }

    private LiveData<List<UserStateItem>> mapUserDataToViewState(LiveData<List<User>> users) {
        return Transformations.map(users, user -> {
            List<UserStateItem> userViewStateItems = new ArrayList<>();
            if (user != null) {
                for (User u : user) {
                    userViewStateItems.add(
                            new UserStateItem(u)
                    );
                }
            }
            return userViewStateItems;
        });
    }

    public LiveData<List<UserStateItem>> getAllUsers() {
        return mapUserDataToViewState(mUserRepository.getAllUsers());
    }

}