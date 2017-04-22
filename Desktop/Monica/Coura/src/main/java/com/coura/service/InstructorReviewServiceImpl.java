package com.coura.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.coura.dao.InstructorReviewDao;
import com.coura.model.InstructorReview;

public class InstructorReviewServiceImpl implements InstructorReviewService {
	
	private InstructorReviewDao instructorReviewDao;

	public void setInstructorReviewDao(InstructorReviewDao instructorReviewDao) {
		this.instructorReviewDao = instructorReviewDao;
	}

	@Override
	@Transactional
	public List<Integer> instructorReviewedByUser(String emailId) {
		
		// TODO Auto-generated method stub
		List<Integer> iIds = this.instructorReviewDao.instructorReviewedByUser(emailId);
		return iIds;
	}

	@Override
	@Transactional
	public void insertInstructorReview(InstructorReview ir) {
		
		// TODO Auto-generated method stub
		this.instructorReviewDao.insertInstructorReview(ir);
	}

	@Override
	@Transactional
	public List<InstructorReview> getInstructorReviews(Integer instructorId) {
		
		// TODO Auto-generated method stub
		List<InstructorReview> ir = this.instructorReviewDao.getInstructorReviews(instructorId);
		return ir;
	}
}
