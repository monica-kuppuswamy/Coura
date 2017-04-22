package com.coura.dao;

import com.coura.model.CourseInstructor;

public interface CourseInstructorDao {
	public CourseInstructor findRecordById(Integer id);
	public boolean isRecordPresent(Integer id);
}
