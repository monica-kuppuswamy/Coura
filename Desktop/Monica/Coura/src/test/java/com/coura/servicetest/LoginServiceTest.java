package com.coura.servicetest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.coura.dao.LoginDao;
import com.coura.service.LoginServiceImpl;

public class LoginServiceTest {
	
	private LoginServiceImpl loginService;
	private LoginDao loginDao;
	
	@Before
	public void setupMock() {
		loginService = new LoginServiceImpl();
		loginDao = mock(LoginDao.class);
		loginService.setLoginDao(loginDao);
	}
	
	@Test
	public void testVerifyCredentials() {
		String emailId = "user@uncc.edu";
		String password1 = "password123";
		String password2 = "password124";
		
		System.out.println("Stubbing verifyCredentials(emailId, password1) to return " + true);
		when(loginDao.verifyCredentials(emailId, password1)).thenReturn(true);
		
		System.out.println("Stubbing verifyCredentials(emailId, password2) to return " + false);
		when(loginDao.verifyCredentials(emailId, password2)).thenReturn(false);
		
		System.out.println("Calling loginService.verifyCredentials(emailId, password1)");
		loginService.verifyCredentials(emailId, password1);
		
		System.out.println("Verifying loginDao.verifyCredentials(emailId, password1) is called");
		verify(loginDao).verifyCredentials(emailId, password1);
		
		assertNotNull(loginService.verifyCredentials(emailId, password1));
		assertEquals(true, (loginService.verifyCredentials(emailId, password1)));
		
		System.out.println("Calling loginService.verifyCredentials(emailId, password2)");
		loginService.verifyCredentials(emailId, password2);
		
		System.out.println("Verifying loginDao.verifyCredentials(emailId, password2) is called");
		verify(loginDao).verifyCredentials(emailId, password2);
		
		assertNotNull(loginService.verifyCredentials(emailId, password2));
		assertEquals(false, (loginService.verifyCredentials(emailId, password2)));
	}
}