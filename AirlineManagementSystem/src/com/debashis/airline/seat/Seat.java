package com.debashis.airline.seat;

public class Seat {

	
	private final String seatNumber;
	private final SeatType seatType;
	private SeatStatus seatStatus;
	
	public Seat(String seatNumber, SeatType seatType) {
		super();
		this.seatNumber = seatNumber;
		this.seatType = seatType;
		this.seatStatus = SeatStatus.AVAILABLE;
	}

	public String getSeatNumber() {
		return seatNumber;
	}
	
	public void reserve() {
		this.seatStatus=SeatStatus.RESERVED;
	}
	
	public void release() {
		this.seatStatus=SeatStatus.AVAILABLE;
	}
	
	public synchronized boolean isBooked() {
		return this.seatStatus==SeatStatus.OCCUPIED;
	}
	
	
}
