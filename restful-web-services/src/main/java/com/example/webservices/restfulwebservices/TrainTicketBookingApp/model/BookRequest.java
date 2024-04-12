package com.example.webservices.restfulwebservices.TrainTicketBookingApp.model;

import jakarta.validation.constraints.NotEmpty;

public class BookRequest {
	@NotEmpty(message = "Need to provide source")
	private String fromPlace;
	
	@NotEmpty(message = "Need to provide destination")
	private String toPlace;
	
	@NotEmpty(message = "Need to provide price")
	private double price;
	
	@NotEmpty(message = "Need to provide Passenger details")
	private Passenger passenger;
	
	@NotEmpty(message = "Need to provide section ID")
	private String sectionID;
	
	@NotEmpty(message = "Need to provide seat ID")
	private int seatID;
	
	@NotEmpty(message = "Need to provide train ID")
	private String trainID;
	
	public BookRequest(String fromPlace, String toPlace, double price, Passenger passenger, String sectionID,
			int seatID, String trainID) {
		super();
		this.fromPlace = fromPlace;
		this.toPlace = toPlace;
		this.price = price;
		this.passenger = passenger;
		this.sectionID = sectionID;
		this.seatID = seatID;
		this.trainID = trainID;
	}
	public String getFromPlace() {
		return fromPlace;
	}
	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}
	public String getToPlace() {
		return toPlace;
	}
	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Passenger getPassenger() {
		return passenger;
	}
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	public String getTrainID() {
		return trainID;
	}
	public void setTrainID(String trainID) {
		this.trainID = trainID;
	}
	public String getSectionID() {
		return sectionID;
	}
	public void setSectionID(String sectionID) {
		this.sectionID = sectionID;
	}
	public int getSectionID(int sectionID) {
		return sectionID;
	}
	public void setSeatID(int seatID) {
		this.seatID = seatID;
	}
	public int getSeatID() {
		return seatID;
	}
	
	
}
