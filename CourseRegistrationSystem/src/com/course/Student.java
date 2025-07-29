package com.course;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Student {
	
	private final String id;
	private final String name;
	private final String email;
	private final List<Course> enrolledCourses;
	
	public Student(String name, String email) {
		super();
		this.id =UUID.randomUUID().toString();
		this.name = name;
		this.email = email;
		this.enrolledCourses = new ArrayList<Course>();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public List<Course> getEnrolledCourses() {
		return enrolledCourses;
	}
	
	
	public void enroll(Course course) {
		enrolledCourses.add(course);
	}
	
	public void drop(Course course) {
		enrolledCourses.remove(course);
	}
	

}
