package com.debashis.airline;

import java.util.UUID;

public class Passanger {
	
	private final String id;
	private final String name;
	private final String email;
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public Passanger(String name, String email) {
		super();
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.email = email;
	}
	
	

}
