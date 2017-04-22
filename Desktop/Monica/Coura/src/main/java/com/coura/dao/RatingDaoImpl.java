package com.coura.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coura.model.CourseRating;

@Repository
@Transactional
public class RatingDaoImpl implements RatingDao {
	
	private static final Logger logger = LoggerFactory.getLogger(RatingDaoImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> courseRatedByUser(String emailId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select cr.courseId as courseId from CourseRating cr where cr.userEmailId like" + 
				" :emailId").setResultTransformer(Transformers.aliasToBean(CourseRating.class));
		query.setParameter("emailId", emailId + "%");
		List<CourseRating> cr = query.list();
		List<Integer> courseIds = new ArrayList<Integer>();
		for (int i = 0; i < cr.size(); i++) {
			courseIds.add(cr.get(i).getCourseId());
		}
		return courseIds;
	}
	
	@Override
	public void insertCourseRating(CourseRating cr) {
		
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<Integer> cIds = courseRatedByUser(cr.getUserEmailId());
		if (cIds.contains(cr.getCourseId())) {
			Query query = session.createQuery("update CourseRating set difficultyRating = :difficultyRating, usefulnessRating = :usefulnessRating" + 
						" where emailId like :emailId and courseId = :courseId");
			query.setParameter("difficultyRating", cr.getDifficultyRating());
			query.setParameter("usefulnessRating", cr.getUsefulnessRating());
			query.setParameter("emailId", cr.getUserEmailId() + "%");
			query.setParameter("courseId", cr.getCourseId());
			query.executeUpdate();
		} else {
			session.persist(cr);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseRating> getCourseRating(Integer courseId) {
		
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select cr.difficultyRating as difficultyRating, cr.usefulnessRating as usefulnessRating " + 
				"from CourseRating cr where cr.courseId = :courseId").setResultTransformer(Transformers.aliasToBean(CourseRating.class));
		query.setParameter("courseId", courseId);
		List<CourseRating> cr = query.list();
		return cr;
	}
}
