package com.inn.elavator;

public class ElavatorSystemDemo {
	
	public static void main(String[] args) {
		
		ElavatorController controller=new ElavatorController(3, 5);
		controller.requestElavator(5, 10);
		//controller.requestElavator(3, 7);
		controller.requestElavator(8, 2);
		//controller.requestElavator(1, 9);
	}

}
