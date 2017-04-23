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
	public Instructor findInstructorByEmailId(String emailId) {
		return this.instructorDao.findInstructorByEmailId(emailId);
	}
	
	@Override
	@Transactional
	public boolean isExistingInstructor(String emailId) {
		return this.instructorDao.isExistingInstructor(emailId);
	}
	
	@Override
	@Transactional
	public boolean updateInstructor(Instructor instructor) {
		if (this.isExistingInstructor(instructor.getEmailId())) {
			return false;
		} else {
			this.instructorDao.updateInstructor(instructor);
			return true;
		}
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
