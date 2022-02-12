package com.studentcourseregistration.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.*;

@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="course_id")
	private int id;
	
	private String name;
	/*
	@ManyToMany(mappedBy = "courses")
	private List<Student> students;
	*/
	@OneToMany(mappedBy = "course")
	private Set<StudentCourse> studentCourses = new HashSet<>();

	public Course(String name) {
		super();
		this.name = name;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course that = (Course) o;
        return Objects.equals(name, that.name);
    }

	/*
	public Course(String name, List<Student> students) {
		super();
		this.name = name;
		this.students = students;
	}
	*/
	
	public Course() {
		super();
	}

	/*
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
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
		return "Course [id=" + id + ", name=" + name + "]";
	}

}
