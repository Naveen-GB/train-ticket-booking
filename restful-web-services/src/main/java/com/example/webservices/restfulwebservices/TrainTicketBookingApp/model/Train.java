package com.example.webservices.restfulwebservices.TrainTicketBookingApp.model;

import java.util.ArrayList;
import java.util.List;

public class Train {
	private String trainID;
	
	private List<Section> sections;
	
	public Train(String trainID, int numSeatsPerSection) {
		this.trainID = trainID;
		this.sections = new ArrayList<>();
		this.sections.add(new Section("A", numSeatsPerSection));
		this.sections.add(new Section("B", numSeatsPerSection));
	}

	public String getTrainID() {
		return trainID;
	}

	public void setTrainID(String trainID) {
		this.trainID = trainID;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	
	
}
