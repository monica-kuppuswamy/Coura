package com.coura.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coura.model.Course;
import com.coura.model.StudentCourse;

@Repository
@Transactional
public class RegistrationDaoImpl implements RegistrationDao {
	
	private static final Logger logger = LoggerFactory.getLogger(RegistrationDaoImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void insertRegistrationDetails(StudentCourse sc) {
		
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(sc);
	}
	
	@SuppressWarnings("unchecked")
	public List<Course> getEnrolledCourses(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select c.id as id, c.courseNumber as courseNumber, c.courseName as courseName from Course c," +
				" StudentCourse sc where sc.courseId = c.id and sc.userEmailId like :emailId").setResultTransformer(Transformers.aliasToBean(Course.class));
		query.setParameter("emailId", id + "%");
		List<Course> courseList = query.list();
		return courseList;
	}

	public void unEnrollCourse(String id, Integer courseId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from StudentCourse where courseId = :courseId and userEmailId like :emailId");
		query.setParameter("courseId", courseId);
		query.setParameter("emailId", id + "%");
		query.executeUpdate();
	}
}
