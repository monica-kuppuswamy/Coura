package com.coura.service;

import java.util.List;

import com.coura.model.CourseReview;

public interface ReviewService {
	public List<Integer> courseReviewedByUser(String emailId);
	public void insertCourseReview(CourseReview cr);
	public List<CourseReview> getCourseReviews(Integer courseId);
}
