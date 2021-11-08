package com.liondevlab.go4lunch.view.adapter;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.liondevlab.go4lunch.R;
import com.liondevlab.go4lunch.model.User;
import com.liondevlab.go4lunch.view.item.MessageStateItem;
import com.liondevlab.go4lunch.view.item.UserStateItem;

import java.util.List;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 10/10/2021
 */
public class WorkmatesListAdapter  extends ListAdapter<UserStateItem, WorkmatesListAdapter.WorkmatesListViewHolder> {

	private static String mWorkmateHasChosenText;
	private static String mWorkmateHasNtChosenText;

	public WorkmatesListAdapter() {
		super(new WorkmatesListViewHolder.WorkmateListNeighbourItemCallback());
	}

	@NonNull
	@Override
	public WorkmatesListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
		return new WorkmatesListViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_workmate_list, viewGroup, false));
	}

	@Override
	public void onBindViewHolder(@NonNull WorkmatesListViewHolder workmatesListViewHolder, int position) {
		mWorkmateHasChosenText = workmatesListViewHolder.itemView.getContext().getString(R.string.item_workmate_chosen_lunch_text);
		mWorkmateHasNtChosenText = workmatesListViewHolder.itemView.getContext().getString(R.string.item_workmate_lunch_not_chosen_text, getItem(position).getUsername());
		workmatesListViewHolder.bind(getItem(position));
	}

	public static class WorkmatesListViewHolder extends RecyclerView.ViewHolder {

		private final ImageView mWorkmateAvatarImageView;
		private final TextView mWorkmateDescriptionText;

		public WorkmatesListViewHolder(@NonNull View itemView) {
			super(itemView);
			mWorkmateAvatarImageView = itemView.findViewById(R.id.item_workmates_list_avatar);
			mWorkmateDescriptionText = itemView.findViewById(R.id.item_workmates_list_text);
			//TODO link each part of item with part of the layout

		}

		public void bind(UserStateItem item) {
			// TODO things to update with data on item
			if (!item.isRestaurantChosen()) {
				mWorkmateDescriptionText.setText(mWorkmateHasNtChosenText);
			}
			Glide.with(mWorkmateAvatarImageView)
					.load(item.getUrlPicture())
					.apply(RequestOptions.circleCropTransform())
					.into(mWorkmateAvatarImageView);
		}

		private static class WorkmateListNeighbourItemCallback extends DiffUtil.ItemCallback<UserStateItem> {
			@Override
			public boolean areItemsTheSame(@NonNull UserStateItem oldItem, @NonNull UserStateItem newItem) {
				return oldItem.getUsername().equals(newItem.getUsername()) && oldItem.getUserId().equals(newItem.getUserId());
			}

			@Override
			public boolean areContentsTheSame(@NonNull UserStateItem oldItem, @NonNull UserStateItem newItem) {
				return oldItem.equals(newItem);
			}
		}
	}
}
