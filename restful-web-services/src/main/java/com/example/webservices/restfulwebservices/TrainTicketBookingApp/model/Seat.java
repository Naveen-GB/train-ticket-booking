package com.example.webservices.restfulwebservices.TrainTicketBookingApp.model;

public class Seat {
	private int seatID;
	private Boolean isEmpty;
	private Passenger passenger;
	
	public Seat(int seatID) {
		this.seatID = seatID;
		this.isEmpty = true;
		this.passenger = null;
	}
	
	public int getSeatID() {
		return seatID;
	}
	public void setSeatID(int seatID) {
		this.seatID = seatID;
	}
	public Boolean getIsEmpty() {
		return isEmpty;
	}
	public void setIsEmpty(Boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	public Passenger getPassenger() {
		return passenger;
	}
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	
	
}
