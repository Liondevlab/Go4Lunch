package com.liondevlab.go4lunch.view.item;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.ServerTimestamp;
import com.liondevlab.go4lunch.model.Message;
import com.liondevlab.go4lunch.model.User;

import java.util.Date;
import java.util.Objects;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 21/10/2021
 */
public class MessageStateItem {

	@NonNull
	private final User userSender;
	@NonNull
	private final Date dateCreated;
	@NonNull
	private final String messageText;
	@NonNull
	private final String urlImage;

	public MessageStateItem(Message message) {
		this.userSender = message.getUserSender();
		this.dateCreated = message.getDateCreated();
		this.messageText = message.getMessageText();
		this.urlImage = message.getUrlImage();
	}

	public MessageStateItem(@NonNull User userSender,@NonNull Date dateCreated,@NonNull String messageText,@NonNull String urlImage) {
		this.userSender = userSender;
		this.dateCreated = dateCreated;
		this.messageText = messageText;
		this.urlImage = urlImage;
	}


	// --- GETTERS ---
	@NonNull
	public User getUserSender() {return userSender; }
	@NonNull
	public Date getDateCreated() { return dateCreated; }
	@NonNull
	public String getMessageText() { return messageText; }
	@NonNull
	public String getUrlImage() { return urlImage; }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof MessageStateItem)) return false;
		MessageStateItem that = (MessageStateItem) o;
		return userSender.equals(that.userSender) &&
				dateCreated.equals(that.dateCreated) &&
				messageText.equals(that.messageText) &&
				urlImage.equals(that.urlImage);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userSender, dateCreated, messageText, urlImage);
	}

}
