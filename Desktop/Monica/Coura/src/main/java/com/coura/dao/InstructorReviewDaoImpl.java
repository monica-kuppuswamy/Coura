package com.coura.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coura.model.InstructorReview;

public class InstructorReviewDaoImpl implements InstructorReviewDao {
	
	private static final Logger logger = LoggerFactory.getLogger(ReviewDaoImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> instructorReviewedByUser(String emailId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select ir.instructorId as instructorId from InstructorReview ir where ir.userEmailId like" + 
				" :emailId").setResultTransformer(Transformers.aliasToBean(InstructorReview.class));
		query.setParameter("emailId", emailId + "%");
		List<InstructorReview> ir = query.list();
		List<Integer> instructorIds = new ArrayList<Integer>();
		for (int i = 0; i < ir.size(); i++) {
			instructorIds.add(ir.get(i).getInstructorId());
		}
		return instructorIds;
	}

	@Override
	public void insertInstructorReview(InstructorReview ir) {
		
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<Integer> iIds = instructorReviewedByUser(ir.getUserEmailId());
		if (iIds.contains(ir.getInstructorId())) {
			Query query = session.createQuery("update InstructorReview set review = :review where emailId = :emailId and instructorId = :instructorId");
			query.setParameter("review", ir.getReview());
			query.setParameter("emailId", ir.getUserEmailId());
			query.setParameter("instructorId", ir.getInstructorId());
			query.executeUpdate();
		} else {
			session.persist(ir);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InstructorReview> getInstructorReviews(Integer instructorId) {
		
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select ir.userEmailId as userEmailId, ir.review as review from InstructorReview ir" + 
				" where ir.instructorId = :instructorId").setResultTransformer(Transformers.aliasToBean(InstructorReview.class));
		query.setParameter("instructorId", instructorId);
		List<InstructorReview> ir = query.list();
		return ir;
	}
}
