package com.debashis.concertTicket;

public class SeatNotFoundException extends  RuntimeException {
	
	public SeatNotFoundException(String message) {
		super(message);
	}

}
