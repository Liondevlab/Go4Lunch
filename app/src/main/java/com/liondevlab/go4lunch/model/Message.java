package com.liondevlab.go4lunch.model;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.ServerTimestamp;

import java.io.Serializable;
import java.util.Date;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 04/10/2021
 */
public class Message implements Serializable {

	private String messageId;
	private String userId;
	private User userSender;
	private Date dateCreated;
	private String messageText;
	private String urlImage;

	public Message() { }

	public Message(String messageText, User userSender) {
		this.messageText = messageText;
		this.userSender = userSender;
	}

	public Message(String messageText,String urlImage, User userSender) {
		this.messageText = messageText;
		this.urlImage = urlImage;
		this.userSender = userSender;
	}

	public Message(String messageId, String userId, String messageText) {
		this.messageId = messageId;
		this.userId = userId;
		this.messageText = messageText;
	}

	// --- GETTERS ---

	@ServerTimestamp
	public Date getDateCreated() { return dateCreated; }
	public String getMessageId() { return messageId; }
	public String getUserId() { return userId; }
	public String getUrlImage() { return urlImage; }
	public String getMessageText() { return messageText; }
	public User getUserSender() {return userSender; }


	// --- SETTERS ---

	public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }
	public void setMessageId(String messageId) { this.messageId = messageId; }
	public void setUserId(String userId) { this.userId = userId; }
	public void setUrlImage(String urlImage) { this.urlImage = urlImage; }
	public void setMessageText(String messageText) { this.messageText = messageText; }
	public void setUserSender(User userSender) { this.userSender = userSender; }

}
