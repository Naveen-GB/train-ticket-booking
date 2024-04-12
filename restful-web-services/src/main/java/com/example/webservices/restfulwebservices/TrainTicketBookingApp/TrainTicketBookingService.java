package com.example.webservices.restfulwebservices.TrainTicketBookingApp;

import java.util.ArrayList;
import java.util.List;

import com.example.webservices.restfulwebservices.TrainTicketBookingApp.model.BookRequest;
import com.example.webservices.restfulwebservices.TrainTicketBookingApp.model.BookedTicket;
import com.example.webservices.restfulwebservices.TrainTicketBookingApp.model.Seat;
import com.example.webservices.restfulwebservices.TrainTicketBookingApp.model.Section;
import com.example.webservices.restfulwebservices.TrainTicketBookingApp.model.Train;
import com.example.webservices.restfulwebservices.exception.*;
import org.springframework.stereotype.Service;

@Service
public class TrainTicketBookingService {
	
	/* Creating variable in current session instead of database */
	private static List<Train> trainList = new ArrayList<>();
	private static List<BookedTicket>bookingList = new ArrayList<>();
	private static int bookingID = 0;
	
	/* All trains will have 2 sections by default.
	 * Train ID and number seats per Section are specified while constructing Train */
	public TrainTicketBookingService() {
		trainList.add(new Train("12345", 60));
		trainList.add(new Train("12344", 120));
	}
	
	public BookedTicket bookTicketService(BookRequest bookRequest) {
		Train train = findTrainByID(bookRequest.getTrainID());
		Section sectionToBook = findSectionByID(train, bookRequest.getSectionID());
		
		Seat seatToBook = findSeatByID(sectionToBook, bookRequest.getSeatID());
		
		if(!seatToBook.getIsEmpty()) {
			throw new SeatOccupiedException("Seat ID "+ bookRequest.getSeatID() +" already booked!");
		}
		
		seatToBook.setIsEmpty(false);
		seatToBook.setPassenger(bookRequest.getPassenger());
		sectionToBook.setNumSeatsAvailable(sectionToBook.getNumSeatsAvailable()-1);
		
		BookedTicket bookedTicket = new BookedTicket(++bookingID, bookRequest.getFromPlace(), bookRequest.getToPlace(), 
										bookRequest.getPrice(), bookRequest.getPassenger(), bookRequest.getSectionID(), 
										bookRequest.getSeatID(), bookRequest.getTrainID());
		bookingList.add(bookedTicket);
		return bookedTicket;
	}
	
	public Boolean deleteTicket(int bookingID) {
		BookedTicket bookedTicket = bookingList.stream().filter(booking -> booking.getBookingID() == bookingID).findFirst()
				.orElseThrow(() -> new BookingIDNotFoundException("Booking ID " + bookingID + " not found!"));
		
		bookingList.remove(bookedTicket);
		Train train = findTrainByID(bookedTicket.getTrainID());
		Section section = findSectionByID(train, bookedTicket.getSectionID());
		Seat seat = findSeatByID(section, bookedTicket.getSeatID());
		section.setNumSeatsAvailable(section.getNumSeatsAvailable()+1);
		seat.setIsEmpty(true);
		seat.setPassenger(null);
		return true;
	}
	
	public Boolean editTicket(int bookingID, String newSectionID, int newSeatID) {
		BookedTicket bookedTicket = bookingList.stream().filter(booking -> booking.getBookingID() == bookingID).findFirst()
				.orElseThrow(() -> new BookingIDNotFoundException("Booking ID " + bookingID + " not found!"));
		
		Train train = findTrainByID(bookedTicket.getTrainID());
		Section section = findSectionByID(train, bookedTicket.getSectionID());
		Seat seat = findSeatByID(section, bookedTicket.getSeatID());
		
		Section newSection = findSectionByID(train, newSectionID);
		Seat newSeat = findSeatByID(newSection, newSeatID);
		
		if(newSeat.getPassenger() != null) {
			throw new SeatOccupiedException("Seat ID "+ newSeatID +" already booked!");
		}
		
		bookedTicket.setSectionID(newSectionID);
		bookedTicket.setSeatID(newSeatID);
		
		seat.setPassenger(null);
		seat.setIsEmpty(true);
		section.setNumSeatsAvailable(section.getNumSeatsAvailable()-1);
		
		newSeat.setPassenger(bookedTicket.getPassenger());
		newSeat.setIsEmpty(false);
		newSection.setNumSeatsAvailable(newSection.getNumSeatsAvailable()+1);
		
		return true;
	}
	
	public BookedTicket getReceiptForUser(String mailID) {
		for (BookedTicket booking: bookingList) {
			if(booking.getPassenger().geteMail().equals(mailID)) {
				return booking;
			}
		}
		throw new BookingIDNotFoundException("No tickets for the user with mail ID " + mailID);
	}
	
	public List<Seat> viewAllSeats(String trainID, String sectionID) {
		Train train = findTrainByID(trainID);
		Section section = findSectionByID(train, sectionID);
		return section.getSeats();
	}
	
	public Train findTrainByID(String trainID) {
		for (Train train : trainList) {
			if(train.getTrainID().equals(trainID)) {
				return train;
			}
		}
		throw new TrainNotFoundException("Train ID "+ trainID + " not found!");
	}
	
	public Section findSectionByID(Train train, String sectionID) {
		List<Section> sectionList = train.getSections();
		for (Section section: sectionList) {
			if(section.getSectionID().equals(sectionID)) {
				return section;
			}
		}
		throw new SectionNotFoundException("Section ID "+ sectionID + " not found!");
	}
	
	public Seat findSeatByID(Section section, int seatID) {
		List<Seat> seatList = section.getSeats();
		for (Seat seat: seatList) {
			if(seat.getSeatID() == seatID) {
				return seat;
			}
		}
		throw new SeatNotFoundException("Seat ID "+ seatID + " not found!");
	}
	
}
