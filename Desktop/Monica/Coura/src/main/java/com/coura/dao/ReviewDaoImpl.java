package com.coura.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coura.model.CourseReview;

@Repository
@Transactional
public class ReviewDaoImpl implements ReviewDao {
	
	private static final Logger logger = LoggerFactory.getLogger(ReviewDaoImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> courseReviewedByUser(String emailId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select cr.courseId as courseId from CourseReview cr where cr.userEmailId like" + 
				" :emailId").setResultTransformer(Transformers.aliasToBean(CourseReview.class));
		query.setParameter("emailId", emailId + "%");
		List<CourseReview> cr = query.list();
		List<Integer> courseIds = new ArrayList<Integer>();
		for (int i = 0; i < cr.size(); i++) {
			courseIds.add(cr.get(i).getCourseId());
		}
		return courseIds;
	}

	@Override
	public void insertCourseReview(CourseReview cr) {
		
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<Integer> cIds = courseReviewedByUser(cr.getUserEmailId());
		if (cIds.contains(cr.getCourseId())) {
			Query query = session.createQuery("update CourseReview set review = :review where emailId = :emailId and courseId = :courseId");
			query.setParameter("review", cr.getReview());
			query.setParameter("emailId", cr.getUserEmailId());
			query.setParameter("courseId", cr.getCourseId());
			query.executeUpdate();
		} else {
			session.persist(cr);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseReview> getCourseReviews(Integer courseId) {
		
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select cr.userEmailId as userEmailId, cr.review as review from CourseReview cr" + 
				" where cr.courseId = :courseId").setResultTransformer(Transformers.aliasToBean(CourseReview.class));
		query.setParameter("courseId", courseId);
		List<CourseReview> cr = query.list();
		return cr;
	}
}
