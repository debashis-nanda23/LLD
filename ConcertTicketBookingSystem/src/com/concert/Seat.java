package com.concert;

public class Seat {
	
	private final String id;
	private final String seatNumber;
	private final SeatType seatType;
	private final double price;
	private SeatStatus seatStatus;
	
	public Seat(String id, String seatNumber, SeatType seatType, double price) {
		super();
		this.id = id;
		this.seatNumber = seatNumber;
		this.seatType = seatType;
		this.price = price;
		this.seatStatus = seatStatus.AVAILABLE;
	}

	public SeatStatus getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(SeatStatus seatStatus) {
		this.seatStatus = seatStatus;
	}

	public String getId() {
		return id;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public SeatType getSeatType() {
		return seatType;
	}

	public double getPrice() {
		return price;
	}
	
	
	public synchronized void book() {
		if(this.seatStatus==SeatStatus.AVAILABLE) {
			this.seatStatus=SeatStatus.BOOKED;
		}else {
			throw new SeatNotFoundException("Seat is already reserved");
		}
		
	}
	
	public synchronized void release() {
		if(this.seatStatus==SeatStatus.BOOKED)
			this.seatStatus=SeatStatus.AVAILABLE;
	}
	

}
