package com.studentcourseregistration.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.studentcourseregistration.entity.Course;
import com.studentcourseregistration.entity.Student;
import com.studentcourseregistration.entity.StudentCourse;

@Repository
@Transactional
public class StudentJpaRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	public Student insertStudent(Student student) {
		return entityManager.merge(student);
	}
	
	public StudentCourse insertStudentCourse(StudentCourse studentCourse) {
		Student s = findById(studentCourse.getStudent().getId());
		if (s != null) {
			studentCourse.setStudent(s);
		}
		return entityManager.merge(studentCourse);
	}
	
	public Course insertCourse(Course course) {
		return entityManager.merge(course);
	}
	private Student findById(int id) {
		return entityManager.find(Student.class, id);
	}
	
	public void deleteById(int id) {
		Student student = findById(id);
		entityManager.remove(student);
	}

	
	public List<Student> findAllStudentsByCourse(String courseName){
		TypedQuery<Student> query = entityManager.createQuery(
				"SELECT sc.student "
				+ "FROM Course c JOIN c.studentCourses sc "
				+ "WHERE c.name = :name "
				+ "ORDER BY sc.student.name",
				Student.class);
		return query.setParameter("name", courseName).getResultList();
	}
	
	public List<Student> findAllStudentsNotInCourse(String courseName){
		TypedQuery<Student> query = entityManager.createQuery(
				" SELECT s FROM Student s WHERE s.id NOT IN"
				+ "(SELECT sc.student "
				+ "FROM Course c JOIN c.studentCourses sc "
				+ "WHERE c.name = :name) ",
				Student.class);
		return query.setParameter("name", courseName).getResultList();
	}
}
