package com.coura.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.coura.dao.InstructorRatingDao;
import com.coura.model.InstructorRating;

public class InstructorRatingServiceImpl implements InstructorRatingService {
	
	private InstructorRatingDao instructorRatingDao;

	public void setInstructorRatingDao(InstructorRatingDao instructorRatingDao) {
		this.instructorRatingDao = instructorRatingDao;
	}

	@Override
	@Transactional
	public List<Integer> instructorRatedByUser(String emailId) {
		
		// TODO Auto-generated method stub
		List<Integer> iIds = this.instructorRatingDao.instructorRatedByUser(emailId);
		return iIds;
	}

	@Override
	@Transactional
	public void insertInstructorRating(InstructorRating ir) {
		
		// TODO Auto-generated method stub
		this.instructorRatingDao.insertInstructorRating(ir);
	}

	@Override
	@Transactional
	public List<InstructorRating> getInstructorRating(Integer instructorId) {
		
		// TODO Auto-generated method stub
		List<InstructorRating> instructorRating = this.instructorRatingDao.getInstructorRating(instructorId);
		return instructorRating;
	}
}
