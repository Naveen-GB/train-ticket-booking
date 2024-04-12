package com.example.webservices.restfulwebservices.exception;

import java.time.LocalDateTime;

public class ErrorDetails {
	/* Structure of the error/exception should be
	 * 1) Timestamp
	 * 2) Message
	 * 3) Details */
	
	private LocalDateTime timestamp;
	private String message;
	private String details;
	
	public ErrorDetails(LocalDateTime timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
	
}
