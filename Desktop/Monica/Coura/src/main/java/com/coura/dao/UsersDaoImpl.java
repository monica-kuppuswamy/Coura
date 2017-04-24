package com.coura.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coura.model.Users;

@Repository
@Transactional
public class UsersDaoImpl implements UsersDao {
	
	private static final Logger logger = LoggerFactory.getLogger(UsersDaoImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	public Users findUserByEmailId(String emailId) {
		Session session = this.sessionFactory.getCurrentSession();
		Users user = (Users) session.get(Users.class, emailId);
		return user;
	}
	
	public boolean isExistingEmailId(String emailId) {
		Users u = findUserByEmailId(emailId);
		if (u != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public void saveUsers(Users user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(user);
	}
	
	@SuppressWarnings("unchecked")
	public List<Users> listAllUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		
		// To retrieve specific columns using hibernate query
		Criteria criteria = session.createCriteria(Users.class);
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("firstName"), "firstName")
				.add(Projections.property("lastName"), "lastName")
				.add(Projections.property("emailId"), "emailId"))
		.setResultTransformer(Transformers.aliasToBean(Users.class));
		List<Users> usersList = criteria.list();
		for (int i = 0; i < usersList.size(); i++) {
			if (usersList.get(i).getEmailId().equalsIgnoreCase("admin@uncc.edu")) {
				usersList.remove(i);
			}
		}
		return usersList;
	}
	
	public void deleteUser(String emailId) {
		
		Session session = this.sessionFactory.getCurrentSession();
		
		Query query1 = session.createQuery("delete from CourseRating where emailId like :emailID");
		query1.setParameter("emailID", emailId+"%");
		query1.executeUpdate();
		
		Query query2 = session.createQuery("delete from CourseReview where emailId like :emailID");
		query2.setParameter("emailID", emailId+"%");
		query2.executeUpdate();
		
		Query query3 = session.createQuery("delete from InstructorRating where emailId like :emailID");
		query3.setParameter("emailID", emailId+"%");
		query3.executeUpdate();
		
		Query query5 = session.createQuery("delete from InstructorReview where emailId like :emailID");
		query5.setParameter("emailID", emailId+"%");
		query5.executeUpdate();
		
		Query query4 = session.createQuery("delete Users where emailId like :emailID");
		query4.setParameter("emailID", emailId+"%");
		query4.executeUpdate();
	}
}