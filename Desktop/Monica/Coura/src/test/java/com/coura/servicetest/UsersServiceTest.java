package com.coura.servicetest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.coura.dao.UsersDao;
import com.coura.model.Users;
import com.coura.service.UsersServiceImpl;

public class UsersServiceTest {
	
	private UsersServiceImpl usersService;
	private UsersDao usersDao;
	private Users user1, user2;
	
	@Before
	public void setupMock() {
		usersService = new UsersServiceImpl();
		usersDao = mock(UsersDao.class);
		user1 = mock(Users.class);
		user2 = mock(Users.class);
		usersService.setUserDao(usersDao);
	}
	
	@Test
	public void testListAllUsers() {
		Users user = new Users("Shubham","Pawar","spawar3@uncc.edu","Shubham123");
		
		List<Users> list = new ArrayList<Users>();
		list.add(user);
		
		System.out.println("Stubbing listAllUsers() to return " + list);
		when(usersDao.listAllUsers()).thenReturn(list);
		
		System.out.println("Calling UsersService.listAllUsers()");
		usersService.listAllUsers();
		
		System.out.println("Verifying UsersDao.listAllUsers() is called");
		verify(usersDao).listAllUsers();
		
		assertNotNull(usersService.listAllUsers());
		assertEquals(list, (usersService.listAllUsers()));
		assertEquals(1, (usersService.listAllUsers().size()));
	}
	
	@Test
	public void testDeleteUser() {
		
		String emailId = "spawar3@uncc.edu";
		
		System.out.println("Calling UsersService.deleteUser()");
		usersService.deleteUser(emailId);
		
		System.out.println("Verifying UsersDao.deleteUser() is called");
		verify(usersDao).deleteUser(emailId);
	}
	
	@Test
	public void testFindUserByEmailId() {
		String emailId = "spawar3@uncc.edu";
		Users user = new Users("Shubham","Pawar","spawar3@uncc.edu","Shubham123");
		
		System.out.println("Stubbing findUserByEmailId(emailId) to return " + user);
		when(usersDao.findUserByEmailId(emailId)).thenReturn(user);
		
		System.out.println("Calling UsersService.findUserByEmailId(emailId)");
		usersService.findUserByEmailId(emailId);
		
		System.out.println("Verifying UsersDao.findUserByEmailId(emailId) is called");
		verify(usersDao).findUserByEmailId(emailId);
		
		assertNotNull(usersService.findUserByEmailId(emailId));
		assertEquals(user, (usersService.findUserByEmailId(emailId)));
	}
	
	@Test
	public void testIsExistingEmailId() {
		String emailId1 = "spawar3@uncc.edu";
		String emailId2 = "mpadmana@uncc.edu";
		
		System.out.println("Stubbing isExistingEmailId(emailId1) to return " + true);
		when(usersDao.isExistingEmailId(emailId1)).thenReturn(true);
		
		System.out.println("Stubbing isExistingEmailId(emailId2) to return " + false);
		when(usersDao.isExistingEmailId(emailId2)).thenReturn(false);
		
		System.out.println("Calling UsersService.isExistingEmailId(emailId1)");
		usersService.isExistingEmailId(emailId1);;
		
		System.out.println("Verifying UsersDao.isExistingEmailId(emailId1) is called");
		verify(usersDao).isExistingEmailId(emailId1);
		
		System.out.println("Calling UsersService.isExistingEmailId(emailId2)");
		usersService.isExistingEmailId(emailId2);;
		
		System.out.println("Verifying UsersDao.isExistingEmailId(emailId2) is called");
		verify(usersDao).isExistingEmailId(emailId2);
		
		assertNotNull(usersService.isExistingEmailId(emailId1));
		assertNotNull(usersService.isExistingEmailId(emailId2));
		assertEquals(true, (usersService.isExistingEmailId(emailId1)));
		assertEquals(false, (usersService.isExistingEmailId(emailId2)));
	}
	
	@Test
	public void testSaveUsers() {
		
		System.out.println("Stubbing getEmailId() to return 'spawar3@uncc.edu'");
		when(user1.getEmailId()).thenReturn("spawar3@uncc.edu");
		
		System.out.println("Stubbing getEmailId() to return 'spawar3@uncc.edu'");
		when(user2.getEmailId()).thenReturn("mpadmana@uncc.edu");
		
		System.out.println("Stubbing isExistingEmailId(emailId1) to return " + true);
		when(usersDao.isExistingEmailId("spawar3@uncc.edu")).thenReturn(true);
		
		System.out.println("Stubbing isExistingEmailId(emailId2) to return " + false);
		when(usersDao.isExistingEmailId("mpadmana@uncc.edu")).thenReturn(false);
		
		System.out.println("Calling UsersService.saveUsers(user1)");
		usersService.saveUsers(user1);
		
		System.out.println("Verifying Users.getEmailId() is called");
		verify(user1).getEmailId();
		
		System.out.println("Verifying UsersDao.isExistingEmailId(emailId1) is called");
		verify(usersDao).isExistingEmailId(user1.getEmailId());
		
		assertNotNull(usersService.saveUsers(user1));
		assertEquals(false, (usersService.saveUsers(user1)));
		
		System.out.println("Calling UsersService.saveUsers(user2)");
		usersService.saveUsers(user2);
		
		System.out.println("Verifying Users.getEmailId() is called");
		verify(user2).getEmailId();
		
		System.out.println("Verifying UsersDao.isExistingEmailId(emailId1) is called");
		verify(usersDao).isExistingEmailId(user2.getEmailId());
		
		assertNotNull(usersService.saveUsers(user2));
		assertEquals(true, (usersService.saveUsers(user2)));
		
	}
}



