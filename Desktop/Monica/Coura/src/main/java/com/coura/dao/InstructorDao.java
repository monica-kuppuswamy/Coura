package com.coura.dao;

import java.util.List;

import com.coura.model.CourseInstructorWrapper;
import com.coura.model.Instructor;

public interface InstructorDao {
	public boolean addInstructor(CourseInstructorWrapper wrapper);
	public void deleteInstructor(Integer instructorId);
	public List<Instructor> listAllInstructors();
	public List<Instructor> getInstructorsForCourse(Integer courseId);
}
