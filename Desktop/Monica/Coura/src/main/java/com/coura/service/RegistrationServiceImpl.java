package com.coura.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.coura.dao.RegistrationDao;
import com.coura.model.Course;
import com.coura.model.StudentCourse;

public class RegistrationServiceImpl implements RegistrationService {
	
	RegistrationDao registrationDao;

	
	public void setRegistrationDao(RegistrationDao registrationDao) {
		this.registrationDao = registrationDao;
	}

	@Override
	@Transactional
	public void insertRegistrationDetails(StudentCourse sc) {
		
		// TODO Auto-generated method stub
		this.registrationDao.insertRegistrationDetails(sc);
	}
	
	@Override
	@Transactional
	public List<Course> getEnrolledCourses(String id) {
		return this.registrationDao.getEnrolledCourses(id);
	}

	@Override
	@Transactional
	public void unEnrollCourse(String id, Integer courseId) {
		this.registrationDao.unEnrollCourse(id, courseId);
	}
}
