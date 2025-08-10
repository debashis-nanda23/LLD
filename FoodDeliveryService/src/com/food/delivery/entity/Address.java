package com.food.delivery.entity;

public class Address {

	private String street;
	private String city;
	private String zipCode;
	private double latitude;
	private double longitude;
	
	public Address(String street, String city, String zipCode, double latitude, double longitude) {
		super();
		this.street = street;
		this.city = city;
		this.zipCode = zipCode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}
	
	
	public double distanceTo(Address other) {
		double latDiff=this.latitude-other.latitude;
		double longDiff=this.longitude-other.longitude;
		return Math.sqrt(latDiff*latDiff+longDiff*longDiff);
	}
	
	public String toString() {
		return street+","+city+","+zipCode+",@("+latitude+","+longitude+")";
	}
	
	
}
