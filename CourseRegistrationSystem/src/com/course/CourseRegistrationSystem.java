package com.course;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class CourseRegistrationSystem {
	
	private static CourseRegistrationSystem instance;
	private final Map<String,Course> courses;
	private final Map<String,Student> students;
	private final List<Registration> registrations;
	
	public static synchronized CourseRegistrationSystem getInstance() {
		if(instance==null) {
			instance=new CourseRegistrationSystem();
		}
		return instance;
	}
	
	private CourseRegistrationSystem() {
		courses=new ConcurrentHashMap<String, Course>();
		students=new ConcurrentHashMap<String, Student>();
		registrations=new CopyOnWriteArrayList<Registration>();
	}
	
	public Student registerStudent(String name,String email) {
		Student student=new Student(name, email);
		students.put(student.getId(), student);
		return student;
	}
	
	public Course addCourse(String code,String name,String instructor,int capacity) {
		Course course=new Course(code, name, capacity, instructor);
		courses.put(course.getCode(), course);
		return course;
	}
	
	public synchronized void enroll(String studentId,String courseId) {
		Student student=students.get(studentId);
		Course course=courses.get(courseId);
		student.enroll(course);
		course.enroll(student);
	}
	
	public synchronized void drop(String courseId,String studentId) {
		Student student=students.get(studentId);
		Course course=courses.get(courseId);
		student.drop(course);
		course.drop(student);
	}
	
	public List<Course> getAavailableCourse(){
		return courses.values().stream().filter(Course::isCourseAvailable).toList();
	}
	
	public List<Course> searchCourses(String query){
		  return courses.values().stream().filter(c->c.getCode().contains(query) || c.getName().contains(query)).collect(Collectors.toList());
	}

}
