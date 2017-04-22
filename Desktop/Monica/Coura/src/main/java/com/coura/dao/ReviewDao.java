package com.coura.dao;

import java.util.List;

import com.coura.model.CourseReview;

public interface ReviewDao {
	public List<Integer> courseReviewedByUser(String emailId);
	public void insertCourseReview(CourseReview cr);
	public List<CourseReview> getCourseReviews(Integer courseId);
}
