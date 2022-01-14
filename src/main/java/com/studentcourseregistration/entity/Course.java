package com.studentcourseregistration.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Course {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	@ManyToMany(mappedBy = "courses")
	private List<Student> students;

	public Course(String name) {
		super();
		this.name = name;
	}

	public Course(String name, List<Student> students) {
		super();
		this.name = name;
		this.students = students;
	}

	public Course() {
		super();
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public int getId() {
		return id;
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
