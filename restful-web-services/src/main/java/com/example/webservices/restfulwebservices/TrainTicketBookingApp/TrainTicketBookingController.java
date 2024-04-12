package com.example.webservices.restfulwebservices.TrainTicketBookingApp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webservices.restfulwebservices.TrainTicketBookingApp.model.BookRequest;
import com.example.webservices.restfulwebservices.TrainTicketBookingApp.model.BookedTicket;
import com.example.webservices.restfulwebservices.TrainTicketBookingApp.model.Seat;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.HttpStatus;

@RestController
public class TrainTicketBookingController {
	
	private TrainTicketBookingService trainTicketBookingService;
	
	public TrainTicketBookingController(TrainTicketBookingService trainTicketBookingService) {
		this.trainTicketBookingService = trainTicketBookingService;
	}
	
	/*
	 * @description Book a seat for the user
	 * Request has train number, section number, seat number, from, to destination and user details
	 */
	
	@PostMapping(path = "/book") 
	public ResponseEntity<BookedTicket> bookTicket(@RequestBody BookRequest bookRequest) {
		
		BookedTicket bookedTicket = trainTicketBookingService.bookTicketService(bookRequest);
		
		if(bookedTicket == null) {
			return new ResponseEntity<>(bookedTicket, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<>(bookedTicket, HttpStatus.OK);
		}
		
	}
	
	/*
	 * @description Fetch a ticket booked by the user
	 * An user can be uniquely identified using mailID
	 */
	
	@GetMapping(path = "/receipt/{mailID}")
	public ResponseEntity<BookedTicket> getReceipt(@PathVariable String mailID) {
		BookedTicket bookedTicket = trainTicketBookingService.getReceiptForUser(mailID);
		
		if(bookedTicket == null) {
			return new ResponseEntity<>(bookedTicket, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<>(bookedTicket, HttpStatus.OK);
		}
	}
	
	/*
	 * @description View all the seats along with its user that are available in the specified section in the specified train 
	 */
	
	@GetMapping(path = "/view/{trainID}/{sectionID}")
	public ResponseEntity<List<Seat>> viewAllSeatsInSection(@PathVariable String trainID, @PathVariable String sectionID) {
		List<Seat> seatList = trainTicketBookingService.viewAllSeats(trainID, sectionID);
		
		if(seatList == null) {
			return new ResponseEntity<>(seatList, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<>(seatList, HttpStatus.OK);
		}
	}
	
	/*
	 * @description Delete a user's ticket using the booking ID
	 */
	
	@DeleteMapping(path = "/delete/{bookingID}")
	public ResponseEntity<String> deleteTicket(@PathVariable int bookingID) {
		Boolean status = trainTicketBookingService.deleteTicket(bookingID);
		
		if(status == true) {
			return new ResponseEntity<>("Ticket successfully deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Ticket deletion is not successful", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * @description Edit a seat in already booked ticket
	 */
	
	@PutMapping(path = "/edit/{bookingID}/{newSectionID}/{newSeatID}")
	public ResponseEntity<String> editSeat(@PathVariable int bookingID, @PathVariable String newSectionID, @PathVariable int newSeatID) {
		Boolean status = trainTicketBookingService.editTicket(bookingID, newSectionID, newSeatID);
		
		if(status == true) {
			return new ResponseEntity<>("Modified Seat Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Seat Modification is not successful", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
