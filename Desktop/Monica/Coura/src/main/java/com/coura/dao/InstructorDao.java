package com.coura.dao;

import java.util.List;

import com.coura.model.Course;							  
import com.coura.model.CourseInstructorWrapper;
import com.coura.model.Instructor;

public interface InstructorDao {
	public boolean addInstructor(CourseInstructorWrapper wrapper);
	public void deleteInstructor(Integer instructorId);
	public List<Instructor> listAllInstructors();
	public List<Instructor> getInstructorsForCourse(Integer courseId);
	public List<Instructor> getInstructorById(Integer instructorId);
	public Integer getIdForInstructor(String emailId);
	public List<Course> getCourseForInstructor(Integer instructorId);															 															  
}
