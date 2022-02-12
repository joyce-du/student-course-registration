package com.studentcourseregistration.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.*;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="student_id")
	private int id;
	
	private String name;
	/*
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "student_courses", 
	joinColumns = @JoinColumn(name = "student_id"), 
	inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses;
	*/
	
	@OneToMany(mappedBy="student")
	private Set<StudentCourse> studentCourses = new HashSet<>();

	public Student(String name) {
		super();
		this.name = name;
	}
	
	public Student(String name, StudentCourse... studentCourses) {
		this.name = name;
		for (StudentCourse studentCourse : studentCourses) {
			studentCourse.setStudent(this);
		}
		this.studentCourses = Stream.of(studentCourses).collect(Collectors.toSet());
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student that = (Student) o;
        return Objects.equals(name, that.name);
    }

	/*
	public Student(String name, List<Course> courses) {
		super();
		this.name = name;
		this.courses = courses;
	}
	*/

	public Student() {
		super();
	}

	/*
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	*/

	public int getId() {
		return id;
	}

	public Set<StudentCourse> getStudentCourses() {
		return studentCourses;
	}

	public void setStudentCourses(Set<StudentCourse> studentCourses) {
		this.studentCourses = studentCourses;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}

}
