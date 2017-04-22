package com.coura.dao;

import java.util.List;

import com.coura.model.InstructorReview;

public interface InstructorReviewDao {
	public List<Integer> instructorReviewedByUser(String emailId);
	public void insertInstructorReview(InstructorReview ir);
	public List<InstructorReview> getInstructorReviews(Integer instructorId);
}
