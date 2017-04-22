package com.coura.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.coura.dao.ReviewDao;
import com.coura.model.CourseReview;

public class ReviewServiceImpl implements ReviewService {
	
	private ReviewDao reviewDao;

	public void setReviewDao(ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
	}

	@Override
	@Transactional
	public List<Integer> courseReviewedByUser(String emailId) {
		
		// TODO Auto-generated method stub
		List<Integer> cIds = this.reviewDao.courseReviewedByUser(emailId);
		return cIds;
	}

	@Override
	@Transactional
	public void insertCourseReview(CourseReview cr) {
		
		// TODO Auto-generated method stub
		this.reviewDao.insertCourseReview(cr);
	}

	@Override
	@Transactional
	public List<CourseReview> getCourseReviews(Integer courseId) {
		
		// TODO Auto-generated method stub
		List<CourseReview> cr = this.reviewDao.getCourseReviews(courseId);
		return cr;
	}
}
