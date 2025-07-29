package com.course;

import java.util.List;

public class CourseRegistrationSystemDemo {

	
	public static void main(String[] args) {
		
		CourseRegistrationSystem instance=CourseRegistrationSystem.getInstance();
		
		//Add course
		Course course1=instance.addCourse("C101", "Java", "Nataraz", 50);
		Course course2=instance.addCourse("C102","SQL", "Murali", 2);
		
		//Create student
		Student student1=instance.registerStudent("Debashis", "debashis@gmail.com");
		Student student2=instance.registerStudent("Suvasis", "suvasis@gmail.com");
		
		//Search for courses
		List<Course> courses=instance.searchCourses("Java");
		System.out.println("searched courses");
		courses.stream().forEach(c->System.out.println(c.getCode()+" "+c.getName()+" "+c.getInstructor()));
		
		//Register courses
		instance.enroll(student1.getId(), course1.getCode());
		instance.enroll(student1.getId(), course2.getCode());
		instance.enroll(student2.getId(), course2.getCode());
		
		instance.getAavailableCourse().forEach(c->System.out.println(c.getCode()+" "+c.getName()));

	}
}
