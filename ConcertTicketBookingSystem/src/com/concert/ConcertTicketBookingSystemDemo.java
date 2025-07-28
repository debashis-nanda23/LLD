package com.concert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConcertTicketBookingSystemDemo {
	
	public static void main(String[] args) {
		
		ConcertTicketBookingSystem instance=ConcertTicketBookingSystem.getInstance();
		
		//create Concerts
		List<Seat> conccert1Seats=generateSeats(100);
		Concert concert1=new Concert("C101","Artist 1", LocalDateTime.now().plusDays(5), "Venue1", conccert1Seats);
		instance.addConcert(concert1);
		
		List<Seat> conccert2Seats=generateSeats(80);
		Concert concert2=new Concert("C102","Artist 2", LocalDateTime.now().plusDays(10), "Venue2", conccert2Seats);
		instance.addConcert(concert2);
		
		//Create Users
		User user1=new User("U101", "Debashis", "debashis@gmail.com");
		User user2=new User("U102","Suvasis","suvasis@gmail.com");
		
		//Search concerts
		List<Concert> concerts= instance.getConcerts("Artist 1","Venue1",LocalDateTime.now().plusDays(5));
		System.out.println("Search Results:");
		System.out.println(concerts.size());
		for(Concert concert:concerts) {
			System.out.println("Concert: "+concert.getArtist()+" at "+concert.getVenue());
		}
		
		//Book Tickets
		List<Seat> selectedSeats1=selectSeat(concert1, 3);
		Booking booking1=instance.bookTickets(concert1, user1, selectedSeats1);
		
		List<Seat> selectedSeats2=selectSeat(concert2, 5);
		Booking booking2=instance.bookTickets(concert2, user2, selectedSeats2);
		
		//cancel booking
		instance.cancelBooking(booking1.getId());
		
		
	}
	
	private static  List<Seat> selectSeat(Concert concert,int numberOfSeats){
		List<Seat> selectedSeats=new ArrayList<>();
		List<Seat> availableSeat=concert.getSeats().stream().filter(seat->seat.getSeatStatus()==SeatStatus.AVAILABLE).limit(numberOfSeats).collect(Collectors.toList());
		selectedSeats.addAll(availableSeat);
		return selectedSeats;
		
	}

	private static List<Seat> generateSeats(int numberOfSeats) {
		List<Seat> seats=new ArrayList<Seat>();
		for(int i=1;i<=numberOfSeats;i++) {
			String seatNumber="S"+i;
			SeatType seatType=(i<=10) ? SeatType.VIP : (i<=30) ? SeatType.PREMIUM : SeatType.REGULAR;
			double price=(i<=10) ? 300.0 : (i<=30) ? 200.0 : 100.0;
			seats.add(new Seat(seatNumber, seatNumber, seatType, price));
		}
		return seats;
	}

}
