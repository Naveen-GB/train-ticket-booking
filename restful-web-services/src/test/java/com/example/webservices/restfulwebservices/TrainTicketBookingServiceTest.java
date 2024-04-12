package com.example.webservices.restfulwebservices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.webservices.restfulwebservices.TrainTicketBookingApp.TrainTicketBookingService;
import com.example.webservices.restfulwebservices.TrainTicketBookingApp.model.BookRequest;
import com.example.webservices.restfulwebservices.TrainTicketBookingApp.model.BookedTicket;
import com.example.webservices.restfulwebservices.TrainTicketBookingApp.model.Passenger;
import com.example.webservices.restfulwebservices.TrainTicketBookingApp.model.Seat;

@SpringBootTest
public class TrainTicketBookingServiceTest {
	private TrainTicketBookingService trainTicketBookingService;
	
	@BeforeEach
    void setUp() {
		trainTicketBookingService = new TrainTicketBookingService();
    }
	
	String fromPlace = "MAS";
	String toPlace = "TEN";
	double price = 1020;
	String mailID = "hello34@gmail.com";
	String trainID = "12345";
	int bookingID = 1;
	
	@Test
	public void bookTicketServiceTest() {
		String sectionID = "A";
		int seatID = 29;
		Passenger passenger = new Passenger("Naveen", "G B", mailID);
		BookRequest bookRequest = new BookRequest(fromPlace, toPlace, price, passenger, sectionID, seatID, trainID);
		
		BookedTicket bookedTicket = trainTicketBookingService.bookTicketService(bookRequest);
		assertEquals(price, bookedTicket.getPrice());
		assertEquals(trainID, bookedTicket.getTrainID());
	}
	
	@Test
	public void deleteTicketTest() {
		String sectionID = "B";
		int seatID = 12;
		Passenger passenger = new Passenger("Naveen", "G B", mailID);
		BookRequest bookRequest = new BookRequest(fromPlace, toPlace, price, passenger, sectionID, seatID, trainID);
		
		BookedTicket bookedTicket = trainTicketBookingService.bookTicketService(bookRequest);
		Boolean status = trainTicketBookingService.deleteTicket(bookingID);
		
		assertEquals(true, status);
	}
	
	@Test
	public void getReceiptForUserTest() {
		String sectionID = "B";
		int seatID = 29;
		Passenger passenger = new Passenger("Naveen", "G B", mailID);
		BookRequest bookRequest = new BookRequest(fromPlace, toPlace, price, passenger, sectionID, seatID, trainID);
		BookedTicket bookedTicket = trainTicketBookingService.bookTicketService(bookRequest);
		
		BookedTicket userBookedTicket = trainTicketBookingService.getReceiptForUser(bookedTicket.getPassenger().geteMail());
		assertNotNull(userBookedTicket);
	}
	
	@Test
	public void viewAllSeatsTest() {
		List<Seat> seatList = trainTicketBookingService.viewAllSeats(trainID, "A");
		assertNotNull(seatList);
	}
	
	@Test
	public void editTicketTest() {
		String sectionID = "A";
		int seatID = 50;
		String newSectionID = "A";
		int newSeatID = 40;
		Passenger passenger = new Passenger("Naveen", "G B", mailID);
		BookRequest bookRequest = new BookRequest(fromPlace, toPlace, price, passenger, sectionID, seatID, trainID);
		BookedTicket bookedTicket = trainTicketBookingService.bookTicketService(bookRequest);
		
		Boolean status = trainTicketBookingService.editTicket(bookedTicket.getBookingID(), newSectionID, newSeatID);
		assertEquals(true, status);
	}
}
