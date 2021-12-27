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
import com.liondevlab.go4lunch.viewmodel.RestaurantDetailsViewModel;

public class RestaurantDetailsActivity extends AppCompatActivity {

	public static final String EXTRA_RESTAURANT = "RestaurantId";

	RestaurantDetailsViewModel mRestaurantDetailsViewModel;

	User mCurrentUser;

	ImageButton mBackButton;
	ImageView mRestaurantPicture;
	ImageView mRating1;
	ImageView mRating2;
	ImageView mRating3;
	ImageView mRating4;
	ImageView mRating5;
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
		mRating1 = findViewById(R.id.rating_star_1);
		mRating2 = findViewById(R.id.rating_star_2);
		mRating3 = findViewById(R.id.rating_star_3);
		mRating4 = findViewById(R.id.rating_star_4);
		mRating5 = findViewById(R.id.rating_star_5);
		mRestaurantName = findViewById(R.id.restaurant_name);
		mAddress = findViewById(R.id.restaurant_location);
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

		setRatingStars(restaurant);
		setChoiceButton(restaurant);
		setCallButton(restaurant);
		setLikeButton(restaurant);
		setWebsiteButton(restaurant);
		setRecyclerView(restaurant);
	}

	private void setRatingStars(Restaurant restaurant) {
		double rating = restaurant.getRating();
		if (rating >= 0 && rating < 0.5) {
			mRating1.setImageResource(R.drawable.ic_baseline_rating_star_off_24);
		} else {
			mRating1.setImageResource(R.drawable.ic_baseline_rating_star_on_24);
		}
		if (rating >= 0 && rating < 1.5) {
			mRating2.setImageResource(R.drawable.ic_baseline_rating_star_off_24);
		} else {
			mRating2.setImageResource(R.drawable.ic_baseline_rating_star_on_24);
		}
		if (rating >= 0 && rating < 2.5) {
			mRating3.setImageResource(R.drawable.ic_baseline_rating_star_off_24);
		} else {
			mRating3.setImageResource(R.drawable.ic_baseline_rating_star_on_24);
		}
		if (rating >= 0 && rating < 3.5) {
			mRating4.setImageResource(R.drawable.ic_baseline_rating_star_off_24);
		} else {
			mRating4.setImageResource(R.drawable.ic_baseline_rating_star_on_24);
		}
		if (rating >= 0 && rating < 4.5) {
			mRating5.setImageResource(R.drawable.ic_baseline_rating_star_off_24);
		} else {
			mRating5.setImageResource(R.drawable.ic_baseline_rating_star_on_24);
		}
	}

	private void setChoiceButton(Restaurant restaurant) {
		//TODO setup condition with ChosenRestaurant Collection

		if (mIsChosen) {
			mChoiceButton.setImageResource(R.drawable.ic_baseline_check_circle_24);
		} else {
			mChoiceButton.setImageResource(R.drawable.ic_baseline_check_circle_outline_24);
		}

		mChoiceButton.setOnClickListener(v -> {
			if (mIsChosen) {
				//TODO mRestaurantDetailsViewModel.deleteChoice(restaurant)
			} else {
				//TODO mRestaurantDetailsViewModel.createChoice(restaurant)
			}
		});

	}

	private void setCallButton(Restaurant restaurant) {

	}

	private void setLikeButton(Restaurant restaurant) {
		//TODO setup condition with Favorite collection

		if (mIsFavorite) {
			mLikeButton.setImageResource(R.drawable.ic_baseline_like_star_on_24);
		} else {
			mLikeButton.setImageResource(R.drawable.ic_baseline_like_star_off_24);
		}

		if (mIsFavorite) {
			//TODO mRestaurantDetailsViewModel.deleteFavorite(restaurant)
		} else {
			//TODO mRestaurantDetailsViewModel.createFavorite(restaurant)
		}

	}

	private void setWebsiteButton(Restaurant restaurant) {

	}

	private void setRecyclerView(Restaurant restaurant) {

	}


}