package com.coura.dao;

import java.util.List;

import com.coura.model.InstructorRating;

public interface InstructorRatingDao {
	public List<Integer> instructorRatedByUser(String emailId);
	public void insertInstructorRating(InstructorRating ir);
	public List<InstructorRating> getInstructorRating(Integer instructorId);
	List<Integer> courseRatedByUser(String emailId);
}
