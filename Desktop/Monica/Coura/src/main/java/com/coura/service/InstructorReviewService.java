package com.coura.service;

import java.util.List;

import com.coura.model.InstructorReview;

public interface InstructorReviewService {
	public List<Integer> instructorReviewedByUser(String emailId);
	public void insertInstructorReview(InstructorReview ir);
	public List<InstructorReview> getInstructorReviews(Integer instructorId);
}
