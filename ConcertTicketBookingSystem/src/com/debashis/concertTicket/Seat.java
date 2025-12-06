package com.debashis.concertTicket;

public class Seat {
	
	private final String id;
	private final String seatNumber;
	private final SeatType seatType;
	private final double price;
	private SeatStatus status;
	
	public Seat(String id, String seatNumber, SeatType seatType, double price) {
		super();
		this.id = id;
		this.seatNumber = seatNumber;
		this.seatType = seatType;
		this.price = price;
		this.status=SeatStatus.AVAILABLE;
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

	public SeatStatus getStatus() {
		return status;
	}
	
	
	public synchronized void book() {
		if(status==SeatStatus.AVAILABLE) {
			this.status=SeatStatus.BOOKED;
		}else {
			throw new SeatNotFoundException("Seat is already booked or reserved.");
		}
	}
	
	public synchronized void release() {
		if(status==SeatStatus.BOOKED) {
			this.status=SeatStatus.AVAILABLE;
		}
	}
	
	

}
