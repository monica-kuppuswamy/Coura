package com.coura.service;

import java.util.List;

import com.coura.model.InstructorRating;

public interface InstructorRatingService {
	public List<Integer> instructorRatedByUser(String emailId);
	public void insertInstructorRating(InstructorRating ir);
	public List<InstructorRating> getInstructorRating(Integer instructorId);
}
