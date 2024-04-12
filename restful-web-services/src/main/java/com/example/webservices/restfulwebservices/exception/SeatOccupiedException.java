package com.example.webservices.restfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class SeatOccupiedException extends RuntimeException {
	public SeatOccupiedException(String message) {
		super(message);
	}
}
