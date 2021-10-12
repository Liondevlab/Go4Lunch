package com.liondevlab.go4lunch.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.liondevlab.go4lunch.R;
import com.liondevlab.go4lunch.model.User;

import java.util.List;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 10/10/2021
 */
public class WorkmatesListAdapter  extends RecyclerView.Adapter<WorkmatesListAdapter.WorkmatesListViewHolder> {

	@NonNull
	private List<User> mWorkmates;

	public WorkmatesListAdapter(@NonNull final List<User> workmates) {
		this.mWorkmates = workmates;
	}

	void updateWorkmates(@NonNull final List<User> workmates) {
		this.mWorkmates = workmates;
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public WorkmatesListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_workmate_list, viewGroup, false);
		return new WorkmatesListViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull WorkmatesListViewHolder workmatesListViewHolder, int position) {
		workmatesListViewHolder.bind(mWorkmates.get(position));
	}

	@Override
	public int getItemCount() {
		return mWorkmates.size();
	}

	static class WorkmatesListViewHolder extends RecyclerView.ViewHolder {

		private final ImageView mWorkmateAvatarImageView;
		private final TextView mWorkmateDescriptionText;

		WorkmatesListViewHolder(@NonNull View itemView) {
			super(itemView);

			mWorkmateAvatarImageView = itemView.findViewById(R.id.item_workmates_list_avatar);
			mWorkmateDescriptionText = itemView.findViewById(R.id.item_workmates_list_text);
			//TODO link each part of item with part of the layout

		}

		void bind(User workmate) {
			// TODO things to update with data on item
		}

	}
}
