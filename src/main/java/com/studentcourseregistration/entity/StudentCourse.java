package com.studentcourseregistration.entity;

import java.util.Objects;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class StudentCourse {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL, optional=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="student_id")
	private Student student;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="course_id")
	private Course course;
	
	private int score;

	public StudentCourse(Course course, int score) {
		super();
		this.course = course;
		this.score = score;
	}
	
	public StudentCourse(Student student, Course course, int score) {
		super();
		this.student = student;
		this.course = course;
		this.score = score;
	}


	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentCourse)) return false;
        StudentCourse that = (StudentCourse) o;
        return Objects.equals(student.getName(), that.student.getName()) &&
                Objects.equals(course.getName(), that.course.getName()) &&
                Objects.equals(score, that.score);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(student.getName(), course.getName(), score);
    }

	public StudentCourse() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "StudentCourse [id=" + id + ", student=" + student + ", course=" + course + ", score=" + score + "]";
	}
}
