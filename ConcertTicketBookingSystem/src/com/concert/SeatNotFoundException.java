package com.concert;

public class SeatNotFoundException extends RuntimeException {

	public SeatNotFoundException(String message) {
		super(message);
	}
}
