package com.coura.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coura.model.Users;

@Repository
@Transactional
public class LoginDaoImpl implements LoginDao {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginDaoImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	@Transactional
	public boolean verifyCredentials(String emailId, String password) {
		boolean userRegistered = false;
		Session session = this.sessionFactory.getCurrentSession();
		String sqlQuery = "from Users as u where u.emailId = ? and u.password = ?";
		Query query = session.createQuery(sqlQuery);
		query.setParameter(0, emailId);
		query.setParameter(1, password);
		List list = query.list();
		if (list != null && list.size() > 0) {
			userRegistered = true;
		}
		return userRegistered;
	}
}
