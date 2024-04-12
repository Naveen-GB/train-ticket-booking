package com.example.webservices.restfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class SectionNotFoundException extends RuntimeException {
	public SectionNotFoundException(String message) {
		super(message);
	}
}
