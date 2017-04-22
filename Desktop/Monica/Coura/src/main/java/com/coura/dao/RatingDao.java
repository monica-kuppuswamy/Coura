package com.coura.dao;

import java.util.List;

import com.coura.model.CourseRating;

public interface RatingDao {
	public List<Integer> courseRatedByUser(String emailId);
	public void insertCourseRating(CourseRating cr);
	public List<CourseRating> getCourseRating(Integer courseId);
}
