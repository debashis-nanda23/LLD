package com.debashis.concertTicket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConcertTicketBookingSystemDemo {
	
	public static void main(String[] args) {
		
		ConcertTicketBooingSystem bookingSystem=ConcertTicketBooingSystem.getInstance();
		
		//create concerts
		List<Seat> concertSeats1=generateSeats(100);
		Concert concert1=new Concert("C001","Artist 1", "Venue 1",LocalDateTime.now().plusDays(10), concertSeats1);
		bookingSystem.addConcert(concert1);
		
		List<Seat> concertSeats2=generateSeats(200);
		Concert concert2=new Concert("C002","Artist 2", "Venue 2",LocalDateTime.now().plusDays(20), concertSeats2);
		bookingSystem.addConcert(concert2);
		
		//create Users
		User user1=new User("U001","Debashis Nanda","debashis.nanda@gmail.com");
		User user2=new User("U002","Sonali Dash","sonali.dash@gmail.com");
		
		//search concerts
		List<Concert> serachResult= bookingSystem.searchConcert("Artist 1","Venue 1",LocalDateTime.now().plusDays(10));
		System.out.println("Search Results::");
		serachResult.stream().forEach(c->System.out.println("Concert: "+c.getArtists()+" at "+c.getVenue()));
		
		//Book Tickets
		List<Seat>  selectedSeats1= selectSeats(concert1,3);
		Booking booking1=bookingSystem.bookTickets(user1, concert1, selectedSeats1);
		
		List<Seat> selectedSeats2=selectSeats(concert2,4);
		Booking booking2=bookingSystem.bookTickets(user2, concert2, selectedSeats2);
		
		//cancel booking
		bookingSystem.cancelBooking(booking1.getId());
		
		
		
		
		
	}

	private static List<Seat> selectSeats(Concert concert, int numberOfSeats) {
		List<Seat> selectedSeats=new ArrayList<>();
		List<Seat> availableSeats=concert.getSeats().stream().
		                   filter(s->s.getStatus()==SeatStatus.AVAILABLE).
		                   limit(numberOfSeats).toList();
		return availableSeats;
	}

	private static List<Seat> generateSeats(int numberOfSeats) {
		List<Seat> seats=new ArrayList<>();
		for(int i=1;i<=numberOfSeats;i++) {
			String seatNumber="S"+i;
			SeatType seatType=(i<=10) ? SeatType.VIP : (i<=30) ? SeatType.PREMIUM : SeatType.REGULAR;
			double price= (i<=10) ? 100.0 : (i<=30) ? 75.0 :50.0;
			Seat seat=new Seat(UUID.randomUUID().toString(),seatNumber,seatType,price);
			seats.add(seat);
		}
		return seats;
	}
	
	

}
