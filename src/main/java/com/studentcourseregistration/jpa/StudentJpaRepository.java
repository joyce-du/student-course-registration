package com.studentcourseregistration.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.studentcourseregistration.entity.Student;

@Repository
@Transactional
public class StudentJpaRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	public Student insert(Student student) {
		return entityManager.merge(student);
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
				"SELECT sc "
				+ "FROM Course c JOIN c.students sc "
				+ "WHERE c.name = :name "
				+ "ORDER BY sc.name",
				Student.class);
		return query.setParameter("name", courseName).getResultList();
	}
}
