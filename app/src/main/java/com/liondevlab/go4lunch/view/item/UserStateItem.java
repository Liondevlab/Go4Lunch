package com.liondevlab.go4lunch.view.item;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.liondevlab.go4lunch.model.Message;
import com.liondevlab.go4lunch.model.User;

import java.util.Date;
import java.util.Objects;

public class UserStateItem {

    @NonNull
    private final String userId;
    @NonNull
    private final String username;
    @NonNull
    private final String urlPicture;
    private final boolean isRestaurantChosen;

    public UserStateItem(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.urlPicture = Objects.requireNonNull(user.getUrlPicture());
        this.isRestaurantChosen = user.isRestaurantChosen();
    }

    public UserStateItem(@NonNull String userId,
                            @NonNull String username,
                            @NonNull String urlPicture,
                            Boolean isRestaurantChosen) {
        this.userId = userId;
        this.username = username;
        this.urlPicture = urlPicture;
        this.isRestaurantChosen = isRestaurantChosen;
    }


    // --- GETTERS ---
    @NonNull
    public String getUserId() {return userId; }
    @NonNull
    public String getUsername() { return username; }
    @NonNull
    public String getUrlPicture() { return urlPicture; }
    @NonNull
    public boolean isRestaurantChosen() { return isRestaurantChosen; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserStateItem)) return false;
        UserStateItem that = (UserStateItem) o;
        return userId.equals(that.userId) &&
                username.equals(that.username) &&
                urlPicture.equals(that.urlPicture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, urlPicture, isRestaurantChosen);
    }

}
