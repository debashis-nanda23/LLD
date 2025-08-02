package com.digital;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private final String id;
	private final String name;
	private final String email;
	private final String password;
	private List<Account> accounts;
	
	public User(String id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.accounts = new ArrayList<Account>();
	}
	
	public void addAccoint(Account account) {
	   this.accounts.add(account);
	}
	
	public void removeAccount(Account account)
	{
		this.accounts.remove(account);
	}

	public String getId() {
		return id;
	}
	
	
	
	
	
	

}
