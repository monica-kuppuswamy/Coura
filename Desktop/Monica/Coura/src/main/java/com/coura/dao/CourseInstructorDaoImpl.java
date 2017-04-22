package com.coura.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coura.model.CourseInstructor;
import com.coura.model.Users;

public class CourseInstructorDaoImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(CourseInstructorDaoImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	public CourseInstructor findRecordById(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		CourseInstructor ci = (CourseInstructor) session.get(CourseInstructor.class, id);
		return ci;
	}
	
	public boolean isRecordPresent(Integer id) {
		CourseInstructor ci = findRecordById(id);
		if (ci != null) {
			return true;
		} else {
			return false;
		}
	}
}
