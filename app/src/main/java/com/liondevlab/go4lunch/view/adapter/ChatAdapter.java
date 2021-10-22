package com.liondevlab.go4lunch.view.adapter;

import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.liondevlab.go4lunch.R;
import com.liondevlab.go4lunch.databinding.ItemMessageBinding;
import com.liondevlab.go4lunch.model.Message;
import com.liondevlab.go4lunch.service.UserRepository;
import com.liondevlab.go4lunch.view.item.MessageStateItem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 14/10/2021
 */
public class ChatAdapter extends ListAdapter<MessageStateItem, ChatAdapter.ChatViewHolder> {


	// VIEW TYPES
	private static final int SENDER_TYPE = 1;
	private static final int RECEIVER_TYPE = 2;

	private final RequestManager glide;

	public ChatAdapter(RequestManager glide) {
		super(new ListNeighbourItemCallback());
		this.glide = glide;
	}

	@NonNull
	@Override
	public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new ChatViewHolder(LayoutInflater.from(parent.getContext())
				.inflate(R.layout.item_message, parent, false), viewType == 1);
	}

	@Override
	public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
		holder.itemView.invalidate();
		holder.updateWithMessage(getItem(position), this.glide);
	}

	@Override
	public int getItemViewType(int position) {
		// Determine if the user is the sender of the message
		String currentUserId = UserRepository.getInstance().getCurrentUserUID();
		boolean isSender = getItem(position).getUserSender().getUserId().equals(currentUserId);

		return (isSender) ? SENDER_TYPE : RECEIVER_TYPE;
	}

	// ------ ViewHolder ------

	static class ChatViewHolder extends RecyclerView.ViewHolder {

		private final ItemMessageBinding mItemMessageBinding;

		private final int currentUserColor;
		private final int remoteUserColor;
		private final boolean isSender;

		public ChatViewHolder(@NonNull View itemView, boolean isSender) {
			super(itemView);
			this.isSender = isSender;
			mItemMessageBinding = ItemMessageBinding.bind(itemView);

			currentUserColor = ContextCompat.getColor(itemView.getContext(), R.color.colorAccent);
			remoteUserColor = ContextCompat.getColor(itemView.getContext(), R.color.colorPrimary);

		}

		public void updateWithMessage(MessageStateItem message, RequestManager glide) {

			// Update Message
			mItemMessageBinding.itemMessageTextview.setText(message.getMessageText());
			mItemMessageBinding.itemMessageTextview.setTextAlignment(isSender ? View.TEXT_ALIGNMENT_TEXT_END : View.TEXT_ALIGNMENT_TEXT_START);

			// Update Date
			if (message.getDateCreated() != null)
				mItemMessageBinding.itemMessageDateTextview
						.setText(this.convertDateToHour(message.getDateCreated()));

			// Update profile picture
			if (message.getUserSender().getUrlPicture() != null)
				glide.load(message.getUserSender().getUrlPicture())
						.apply(RequestOptions.circleCropTransform())
						.into(mItemMessageBinding.itemMessageAvatar);

			// Update image sent
			if (message.getUrlImage() != null) {
				glide.load(message.getUrlImage())
						.into(mItemMessageBinding.itemMessageSenderImageview);
				mItemMessageBinding.itemMessageSenderImageview.setVisibility(View.VISIBLE);
			} else {
				mItemMessageBinding.itemMessageSenderImageview.setVisibility(View.GONE);
			}
			updateLayoutFromSenderType();
		}

		private void updateLayoutFromSenderType() {

			// Update Message Bubble Color Background
			((GradientDrawable) mItemMessageBinding.itemMessageTextview.getBackground())
					.setColor(isSender ? currentUserColor : remoteUserColor);

			if(!isSender) {
				updateProfileContainer();
				updateMessageContainer();
			}
		}

		private void updateProfileContainer() {
			// Update the constraint for the profile container (Push it to the left for receiver message)
			ConstraintLayout.LayoutParams profileContainerLayoutParams = (ConstraintLayout.LayoutParams) mItemMessageBinding.itemMessageProfileContainer.getLayoutParams();
			profileContainerLayoutParams.endToEnd = ConstraintLayout.LayoutParams.UNSET;
			profileContainerLayoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
			mItemMessageBinding.itemMessageProfileContainer.requestLayout();
		}

		private void updateMessageContainer() {
			// Update the constraint for the message container (Push it to the right of the profile container for receiver message)
			ConstraintLayout.LayoutParams messageContainerLayoutParams = (ConstraintLayout.LayoutParams) mItemMessageBinding.itemMessageContainer.getLayoutParams();
			messageContainerLayoutParams.startToStart = ConstraintLayout.LayoutParams.UNSET;
			messageContainerLayoutParams.endToStart = ConstraintLayout.LayoutParams.UNSET;
			messageContainerLayoutParams.startToEnd = mItemMessageBinding.itemMessageProfileContainer.getId();
			messageContainerLayoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
			messageContainerLayoutParams.horizontalBias = 0.0f;
			mItemMessageBinding.itemMessageContainer.requestLayout();

			// Update the constraint (gravity) for the text of the message (content + date) (Align it to the left for receiver message)
			LinearLayout.LayoutParams messageTextLayoutParams = (LinearLayout.LayoutParams) mItemMessageBinding.itemMessageContainer.getLayoutParams();
			messageTextLayoutParams.gravity = Gravity.START;
			mItemMessageBinding.itemMessageContainer.requestLayout();

			LinearLayout.LayoutParams dateLayoutParams = (LinearLayout.LayoutParams) mItemMessageBinding.itemMessageDateTextview.getLayoutParams();
			dateLayoutParams.gravity = Gravity.BOTTOM | Gravity.START;
			mItemMessageBinding.itemMessageDateTextview.requestLayout();
		}

		private String convertDateToHour(Date date) {
			DateFormat dateFormatHour = new SimpleDateFormat("HH:mm", Locale.getDefault());
			return dateFormatHour.format(date);
		}
	}

	private static class ListNeighbourItemCallback extends DiffUtil.ItemCallback<MessageStateItem> {
		@Override
		public boolean areItemsTheSame(@NonNull MessageStateItem oldItem, @NonNull MessageStateItem newItem) {
			return oldItem.getUserSender().equals(newItem.getUserSender()) && oldItem.getMessageText().equals(newItem.getMessageText());
		}

		@Override
		public boolean areContentsTheSame(@NonNull MessageStateItem oldItem, @NonNull MessageStateItem newItem) {
			return oldItem.equals(newItem);
		}
	}
}
