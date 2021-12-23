package com.liondevlab.go4lunch.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.liondevlab.go4lunch.R;
import com.liondevlab.go4lunch.model.Restaurant;
import com.liondevlab.go4lunch.model.User;
import com.liondevlab.go4lunch.viewmodel.MainViewModel;
import com.liondevlab.go4lunch.viewmodel.RestaurantDetailsViewModel;
import com.liondevlab.go4lunch.viewmodel.RestaurantMapViewModel;
import com.liondevlab.go4lunch.viewmodel.SignInViewModel;

public class RestaurantDetailsActivity extends AppCompatActivity {

	public static final String EXTRA_RESTAURANT = "RestaurantId";

	RestaurantDetailsViewModel mRestaurantDetailsViewModel;

	User mCurrentUser;

	ImageButton mBackButton;
	ImageView mRestaurantPicture;
	ImageView mRating;
	TextView mRestaurantName;
	TextView mAddress;
	FloatingActionButton mChoiceButton;
	ImageButton mLikeButton;
	ImageButton mCallButton;
	ImageButton mWebsiteButton;
	RecyclerView mRecyclerView;

	boolean mIsChosen = false;
	boolean mIsFavorite = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurant_details);
		mRestaurantDetailsViewModel = new ViewModelProvider(this, ViewModelProvider
				.AndroidViewModelFactory
				.getInstance(getApplication()))
				.get(RestaurantDetailsViewModel.class);
		setupView();
		getCurrentUserAndRestaurant();
	}

	private void setupView() {
		mBackButton = findViewById(R.id.back_button);
		mRestaurantPicture = findViewById(R.id.restaurant_picture);
		mRating = findViewById(R.id.rating_image);
		mRestaurantName = findViewById(R.id.restaurant_name);
		mAddress = findViewById(R.id.restaurant_type_and_location);
		mChoiceButton = findViewById(R.id.choice_button);
		mLikeButton = findViewById(R.id.like_button);
		mCallButton = findViewById(R.id.call_button_picture);
		mWebsiteButton = findViewById(R.id.web_button_picture);
		mRecyclerView = findViewById(R.id.participants_recycler_view);
	}

	private void getCurrentUserAndRestaurant() {
		String restaurantId = getIntent().getStringExtra(EXTRA_RESTAURANT);
		mRestaurantDetailsViewModel.getCurrentUser().observe(this, user -> {
			mCurrentUser = user;
		});
		mRestaurantDetailsViewModel.getRestaurant(restaurantId).observe(this, this::populateView);
	}

	private void populateView(Restaurant restaurant) {
		Glide.with(mRestaurantPicture)
				.load(restaurant.getPhotos())
				.apply(RequestOptions.centerCropTransform())
				.into(mRestaurantPicture);

		mRestaurantName.setText(restaurant.getRestaurantName());
		mAddress.setText(restaurant.getAddress());

		mBackButton.setOnClickListener(v -> finish());

	}


}