package com.dronesystem.dronex.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException {

	private String message;
	private String error;
	private HttpStatus status;

	public CustomException(String message) {
		super(message);
	}
}
