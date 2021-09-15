package com.liondevlab.go4lunch.model;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 15/09/2021
 */
public class Message {

	private String message;
	private Date dateCreated;
	private User userSender;

	public Message() { }

	public Message(String message, User userSender) {
		this.message = message;
		this.userSender = userSender;
	}

	// --- GETTERS ---

	public String getMessage() { return message; }
	@ServerTimestamp
	public Date getDateCreated() { return dateCreated; }

	public User getUserSender() { return userSender; }

	// --- SETTERS ---

	public void setMessage(String message) { this.message = message; }

	public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }

	public void setUserSender(User userSender) { this.userSender = userSender; }
}
