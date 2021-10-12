package com.liondevlab.go4lunch.model;

import androidx.annotation.Nullable;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 04/10/2021
 */
public class Message {

	private String messageId;
	private String userId;
	private String messageText;

	public Message() { }

	public Message(String messageId, String userId, String messageText) {
		this.messageId = messageId;
		this.userId = userId;
		this.messageText = messageText;
	}

	// --- GETTERS ---

	public String getMessageId() { return messageId; }
	public String getUserId() { return userId; }
	public String getMessageText() { return messageText; }

	// --- SETTERS ---

	public void setMessageId(String messageId) { this.messageId = messageId; }
	public void setUserId(String userId) { this.userId = userId; }
	public void setMessageText(String messageText) { this.messageText = messageText; }

}
