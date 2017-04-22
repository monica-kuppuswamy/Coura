package com.coura.service;

import java.util.List;

import com.coura.model.CourseRating;

public interface RatingService {
	public List<Integer> courseRatedByUser(String emailId);
	public void insertCourseRating(CourseRating cr);
	public List<CourseRating> getCourseRating(Integer courseId);
}
