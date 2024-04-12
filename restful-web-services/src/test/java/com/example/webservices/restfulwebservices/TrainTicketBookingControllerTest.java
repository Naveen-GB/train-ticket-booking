package com.example.webservices.restfulwebservices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.example.webservices.restfulwebservices.TrainTicketBookingApp.model.BookedTicket;
import com.example.webservices.restfulwebservices.TrainTicketBookingApp.model.BookRequest;
import com.example.webservices.restfulwebservices.TrainTicketBookingApp.model.Passenger;
import com.example.webservices.restfulwebservices.TrainTicketBookingApp.model.Seat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

@SpringBootTest
public class TrainTicketBookingControllerTest {
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@BeforeEach
	private void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	String fromPlace = "MAS";
	String toPlace = "TEN";
	double price = 1020;
	String mailID = "hello34@gmail.com";
	String sectionID = "A";
	int seatID = 29;
	String trainID = "12345";
	int bookingID = 1;
	
	@Test
	public void bookTicketTest() throws Exception {
		Passenger passenger = new Passenger("Naveen", "G B", mailID);
		BookRequest bookRequest = new BookRequest(fromPlace, toPlace, price, passenger, sectionID, seatID, trainID);
		
		String jsonRequest = objectMapper.writeValueAsString(bookRequest);
		MvcResult mvcResult = mockMvc.perform(post("/book").content(jsonRequest).content(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		
		String resultContent = mvcResult.getResponse().getContentAsString();
		BookedTicket bookedTicket = objectMapper.readValue(resultContent, BookedTicket.class);
		
		assertEquals(mvcResult.getResponse().getStatus(), 200);
		assertNotNull(bookedTicket);
	}
	
	@Test
	public void getReceiptTest() throws Exception { 
		MvcResult mvcResult = mockMvc.perform(get("/receipt/{mailID}", mailID).content(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		
		String resultContent = mvcResult.getResponse().getContentAsString();
		BookedTicket bookedTicket = objectMapper.readValue(resultContent, BookedTicket.class);
		
		assertEquals(mvcResult.getResponse().getStatus(), 200);
		assertNotNull(bookedTicket);
	}
	
	@Test
	public void viewAllSeatsInSectionTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/view/{trainID}/{sectionID}", trainID, sectionID).content(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		
		String resultContent = mvcResult.getResponse().getContentAsString();
		List<Seat> seatList = objectMapper.readValue(resultContent, new TypeReference<ArrayList<Seat>>(){});
		
		assertEquals(mvcResult.getResponse().getStatus(), 200);
		assertNotNull(seatList);
	}
	
	@Test
	public void deleteTicketTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(delete("/delete/{bookingID}", bookingID).content(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		
		assertEquals(mvcResult.getResponse().getStatus(), 200);
	}
	
	@Test
	public void editSeatTest() throws Exception {
		String newSectionID = "A";
		int newSeatID = 12;
		
		MvcResult mvcResult = mockMvc.perform(put("/edit/{bookingID}/{newSectionID}/{newSeatID}", bookingID, newSectionID, newSeatID).content(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		assertEquals(mvcResult.getResponse().getStatus(), 200);
	}
}
