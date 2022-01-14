package com.studentcourseregistration;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.studentcourseregistration.entity.Course;
import com.studentcourseregistration.entity.Student;
import com.studentcourseregistration.jpa.StudentJpaRepository;

@SpringBootApplication
public class StudentCourseRegistrationApplication implements CommandLineRunner {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StudentJpaRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(StudentCourseRegistrationApplication.class, args);
	}

	public void run(String... args) throws Exception {
		Course A = new Course("A");
		Course B = new Course("B");
		List<Course> courses = new ArrayList<Course>();
		courses.add(A);
		courses.add(B);
		logger.info("inserting student 1: {}", studentRepository.insert(new Student("Charlie", courses)));
		
		List<Course> courses2 = new ArrayList<Course>();
		courses2.add(A);
		logger.info("inserting student 2: {}", studentRepository.insert(new Student("Bob", courses2)));
		
		List<Course> courses3 = new ArrayList<Course>();
		courses3.add(B);
		logger.info("inserting student 3: {}", studentRepository.insert(new Student("Adam", courses3)));
		
		
		List<Student> students = studentRepository.findAllStudentsByCourse("A");
		logger.info("finding students for course A:");
		for (Student s : students) {
			logger.info("{}", s);
		}
		students = studentRepository.findAllStudentsByCourse("B");
		logger.info("finding students for course B:");
		for (Student s : students) {
			logger.info("{}", s);
		}
		
		logger.info("deleting student 1");
		studentRepository.deleteById(1);
	}

}
