package com.coura.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coura.model.CourseRating;
import com.coura.model.InstructorRating;

public class InstructorRatingDaoImpl implements InstructorRatingDao {
	
	private static final Logger logger = LoggerFactory.getLogger(RatingDaoImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> instructorRatedByUser(String emailId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select ir.instructorId as instructorId from InstructorRating ir where ir.userEmailId like" + 
				" :emailId").setResultTransformer(Transformers.aliasToBean(InstructorRating.class));
		query.setParameter("emailId", emailId + "%");
		List<InstructorRating> ir = query.list();
		List<Integer> instructorIds = new ArrayList<Integer>();
		for (int i = 0; i < ir.size(); i++) {
			instructorIds.add(ir.get(i).getInstructorId());
		}
		return instructorIds;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> courseRatedByUser(String emailId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select ir.courseId as courseId from InstructorRating ir where ir.userEmailId like" + 
				" :emailId").setResultTransformer(Transformers.aliasToBean(CourseRating.class));
		query.setParameter("emailId", emailId + "%");
		List<CourseRating> ir = query.list();
		List<Integer> courseIds = new ArrayList<Integer>();
		for (int i = 0; i < ir.size(); i++) {
			courseIds.add(ir.get(i).getCourseId());
		}
		return courseIds;
	}
	
	@Override
	public void insertInstructorRating(InstructorRating ir) {
		
		Session session = this.sessionFactory.getCurrentSession();
		List<Integer> iIds = instructorRatedByUser(ir.getUserEmailId());
		List<Integer> cIds = courseRatedByUser(ir.getUserEmailId());
		if (iIds.contains(ir.getInstructorId()) && cIds.contains(ir.getCourseId())) {
			Query query = session.createQuery("update InstructorRating set qualityOfTeachingRating = :qualityOfTeachingRating, gradingStyleRating = :gradingStyleRating, leniencyRating = :leniencyRating" + 
						" where emailId like :emailId and instructorId = :instructorId and courseId = :courseId");
			query.setParameter("qualityOfTeachingRating", ir.getQualityOfTeachingRating());
			query.setParameter("gradingStyleRating", ir.getGradingStyleRating());
			query.setParameter("leniencyRating", ir.getLeniencyRating());
			query.setParameter("emailId", ir.getUserEmailId() + "%");
			query.setParameter("instructorId", ir.getInstructorId());
			query.setParameter("courseId", ir.getCourseId());
			query.executeUpdate();
		} else {
			session.persist(ir);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InstructorRating> getInstructorRating(Integer instructorId) {
		
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select ir.courseId as courseId, ir.qualityOfTeachingRating as qualityOfTeachingRating, ir.gradingStyleRating as gradingStyleRating, ir.leniencyRating as leniencyRating " + 
				"from InstructorRating ir where ir.instructorId = :instructorId").setResultTransformer(Transformers.aliasToBean(InstructorRating.class));
		query.setParameter("instructorId", instructorId);
		List<InstructorRating> ir = query.list();
		return ir;
	}
}
