package com.coura.daotest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.coura.dao.LoginDao;
import com.coura.dao.UsersDao;
import com.coura.model.Users;

@ContextConfiguration(locations = "classpath:Coura-servlet-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class LoginDaoTest {
	
	@Autowired
	LoginDao loginDao;
	
	@Autowired
	UsersDao userDao;
	
	@Test
	@Rollback(true)
	public void verifyCredentialsTest() {
		Users u1 = new Users();
		u1.setEmailId("testuser@uncc.edu");
		u1.setFirstName("testfirstName");
		u1.setLastName("testlastName");
		u1.setPassword("test1");
		userDao.saveUsers(u1);	
		assertTrue(loginDao.verifyCredentials(u1.getEmailId(), u1.getPassword()));
	}
}