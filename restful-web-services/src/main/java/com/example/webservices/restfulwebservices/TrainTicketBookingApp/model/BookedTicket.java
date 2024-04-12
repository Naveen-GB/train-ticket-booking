package com.example.webservices.restfulwebservices.TrainTicketBookingApp.model;

public class BookedTicket {
	private int bookingID;
	private String fromPlace;
	private String toPlace;
	private double price;
	private Passenger passenger;
	private String sectionID;
	private int seatID;
	private String trainID;
	public BookedTicket(int bookingID, String fromPlace, String toPlace, double price, Passenger passenger,
			String sectionID, int seatID, String trainID) {
		this.bookingID = bookingID;
		this.fromPlace = fromPlace;
		this.toPlace = toPlace;
		this.price = price;
		this.passenger = passenger;
		this.sectionID = sectionID;
		this.seatID = seatID;
		this.trainID = trainID;
	}
	public int getBookingID() {
		return bookingID;
	}
	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
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
	public String getSectionID() {
		return sectionID;
	}
	public void setSectionID(String sectionID) {
		this.sectionID = sectionID;
	}
	public int getSeatID() {
		return seatID;
	}
	public void setSeatID(int seatID) {
		this.seatID = seatID;
	}
	public String getTrainID() {
		return trainID;
	}
	public void setTrainID(String trainID) {
		this.trainID = trainID;
	}
	
	
}
