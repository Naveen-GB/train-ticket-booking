package com.example.webservices.restfulwebservices.TrainTicketBookingApp.model;

import jakarta.validation.constraints.NotEmpty;

public class Passenger {
	private String firstName;
	private String lastName;
	@NotEmpty(message = "Need to provide e-mail")
	private String eMail;
	
	public Passenger(String firstName, String lastName, @NotEmpty(message = "Need to provide e-mail") String eMail) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.eMail = eMail;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String geteMail() {
		return eMail;
	}
	
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
}
