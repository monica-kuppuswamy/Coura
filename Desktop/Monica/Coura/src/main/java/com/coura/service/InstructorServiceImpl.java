package com.coura.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.coura.dao.InstructorDao;
import com.coura.model.Course;
import com.coura.model.CourseInstructorWrapper;
import com.coura.model.Instructor;

public class InstructorServiceImpl implements InstructorService {
	
	private InstructorDao instructorDao;
	
	public void setInstructorDao(InstructorDao instructorDao) {
		this.instructorDao = instructorDao;
	}

	@Override
	@Transactional
	public boolean addInstructor(CourseInstructorWrapper wrapper) {
		return this.instructorDao.addInstructor(wrapper);
	}
	
	@Override
	@Transactional
	public void deleteInstructor(Integer instructorId) {
		this.instructorDao.deleteInstructor(instructorId);
	}
	
	@Override
	@Transactional
	public List<Instructor> listAllInstructors() {
		return this.instructorDao.listAllInstructors();
	}
	
	@Override
	@Transactional
	public List<Instructor> getInstructorById(Integer instructorId) {
		return this.instructorDao.getInstructorById(instructorId);
	}
	
	@Override
	@Transactional
	public  Integer getIdForInstructor(String emailId) {
		return this.instructorDao.getIdForInstructor(emailId);
	}
	
	@Override
	@Transactional
	public List<Course> getCourseForInstructor(Integer instructorId) {
		return this.instructorDao.getCourseForInstructor(instructorId);
	}
}
