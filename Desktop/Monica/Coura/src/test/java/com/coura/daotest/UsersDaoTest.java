package com.coura.daotest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coura.dao.UsersDao;
import com.coura.model.Users;

import net.minidev.json.JSONArray;

@ContextConfiguration(locations = "classpath:Coura-servlet-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UsersDaoTest {
	
	@Autowired
	private UsersDao userDao;
	
	@Test
	@Rollback(true)
	public void saveUsersTest() {
		Users u1 = new Users();
		u1.setEmailId("testuser@uncc.edu");
		u1.setFirstName("testfirstName");
		u1.setLastName("testlastName");
		u1.setPassword("test1");
		userDao.saveUsers(u1);
		Users findUser = userDao.findUserByEmailId(u1.getEmailId());
		assertEquals(findUser.getEmailId(), u1.getEmailId());
	}

}
