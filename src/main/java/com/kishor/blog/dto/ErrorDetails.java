package com.kishor.blog.dto;

import java.util.Date;

public class ErrorDetails {

	private Date timeStamp;
	private String message;
	private String details;
	
	public ErrorDetails(Date timeStamp, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}
	
	public String getDetails() {
		return details;
	}
	public String getMessage() {
		return message;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	
	
}
