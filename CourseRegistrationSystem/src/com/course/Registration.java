package com.course;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Registration {
	
	private final Course course;
	private final Student student;
	private final LocalDateTime registrationTime;
	
	public Registration(Course course, Student student, LocalDateTime registrationTime) {
		super();
		this.course = course;
		this.student = student;
		this.registrationTime = registrationTime;
	}
	
	

}
