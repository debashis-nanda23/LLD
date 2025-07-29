package com.course;

import java.util.HashSet;
import java.util.Set;

public class Course {
	
	private final String code;
	private final String name;
	private final int maxCapacity;
	private final String instructor;
	private final Set<Student> enrolledStudents;
	
	public Course(String code, String name, int maxCapacity, String instructor) {
		this.code = code;
		this.name = name;
		this.maxCapacity = maxCapacity;
		this.instructor = instructor;
		this.enrolledStudents = new HashSet<Student>();
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public String getInstructor() {
		return instructor;
	}

	public Set<Student> getEnrolledStudents() {
		return enrolledStudents;
	}
	
	
	public boolean isCourseAvailable() {
		return maxCapacity>enrolledStudents.size();
	}
	
	public synchronized void enroll(Student student) {
		if(enrolledStudents.size()>=maxCapacity) {
			throw new IllegalStateException("Course is full");
		}
		enrolledStudents.add(student);
		
	}
	
	public synchronized void drop(Student student) {
		enrolledStudents.remove(student);
	}

}
