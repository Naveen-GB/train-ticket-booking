package com.example.webservices.restfulwebservices.TrainTicketBookingApp.model;

import java.util.ArrayList;
import java.util.List;

public class Section {
	private String sectionID;
	private List<Seat> seats;
	private int numSeatsAvailable;
	
	public Section(String sectionID, int numSeatsperSection) {
		this.sectionID = sectionID;
		this.numSeatsAvailable = numSeatsperSection;
		this.seats = new ArrayList<>();

		for(int seatID = 0; seatID < numSeatsperSection; seatID++) {
			this.seats.add(new Seat(seatID));
		}
	}

	public String getSectionID() {
		return sectionID;
	}

	public void setSectionID(String sectionID) {
		this.sectionID = sectionID;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public int getNumSeatsAvailable() {
		return numSeatsAvailable;
	}

	public void setNumSeatsAvailable(int numSeats) {
		this.numSeatsAvailable = numSeats;
	}
}
