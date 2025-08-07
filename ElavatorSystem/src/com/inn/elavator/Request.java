package com.inn.elavator;

public class Request {
	
	private final int sourceFloor;
	private final int destinationFloor;
	
	public int getSourceFloor() {
		return sourceFloor;
	}

	public int getDestinationFloor() {
		return destinationFloor;
	}

	public Request(int sourceFloor, int destinationFloor) {
		super();
		this.sourceFloor = sourceFloor;
		this.destinationFloor = destinationFloor;
	}
	
	

}
