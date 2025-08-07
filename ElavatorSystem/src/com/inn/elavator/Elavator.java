package com.inn.elavator;

import java.util.ArrayList;
import java.util.List;

public class Elavator {
	
	private final int id;
	private final int capacity;
	private int currentFloor;
	private Direction direction;
	private final List<Request> requests;
	
	public Elavator(int id, int capacity) {
		super();
		this.id = id;
		this.capacity = capacity;
		this.currentFloor=0;
		this.direction=Direction.UP;
		this.requests=new ArrayList<>();
	}
	
	
	public synchronized void addRequest(Request request) {
		if(requests.size()<this.capacity) {
			this.requests.add(request);
			System.out.println("Elavator:"+id+" added request:"+request);
			notifyAll();
		}
	}
	
	public synchronized Request getNextRequest() {
		while(requests.isEmpty()) {
			try {
				wait(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		return requests.removeFirst();
	}

	public synchronized void processRequests() {
		while(true) {
			while(!requests.isEmpty()) {
				Request request=getNextRequest();
				processRequest(request);
			}
			try {
				wait(1000);
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}


	private void processRequest(Request request) {
		int startFloor=currentFloor;
		int endFloor=request.getDestinationFloor();
		
		if(startFloor<endFloor) {
			direction=Direction.UP;
			for(int i=startFloor;i<=endFloor;i++) {
				currentFloor=i;
				System.out.println("Elavator reached at "+currentFloor);
				try {
					Thread.sleep(1000);
				}catch(Exception ex) {
					
				}
			}
		}
		else if(startFloor>endFloor) {
			direction=Direction.DOWN;
			for(int i=startFloor;i>=endFloor;i--) {
				currentFloor=i;
				System.out.println("Elavator reached at "+currentFloor);
				try {
					Thread.sleep(1000);
				}catch(Exception ex) {
					
				}
			}
		}
		
	}


	public int getCurrentFloor() {
		return currentFloor;
	}




	public Direction getDirection() {
		return direction;
	}
	
	public void run() {
		processRequests();
	}
}
