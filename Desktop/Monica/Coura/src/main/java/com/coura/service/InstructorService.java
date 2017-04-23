package com.coura.service;

import java.util.List;

import com.coura.model.Course;										 
import com.coura.model.CourseInstructorWrapper;
import com.coura.model.Instructor;

public interface InstructorService {
	public boolean addInstructor(CourseInstructorWrapper wrapper);
	public Instructor findInstructorByEmailId(String emailId);
	public boolean isExistingInstructor(String emailId);
	public boolean updateInstructor(Instructor instructor);
	public void deleteInstructor(Integer instructorId);
	public List<Instructor> listAllInstructors();
	public List<Instructor> getInstructorById(Integer instructorId);
	public Integer getIdForInstructor(String emailId);
	public List<Course> getCourseForInstructor(Integer instructorId);
}
