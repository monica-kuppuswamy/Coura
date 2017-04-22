package com.coura.service;

import org.springframework.stereotype.Service;

import com.coura.dao.LoginDao;

@Service
public class LoginServiceImpl implements LoginService {
	
	private LoginDao loginDao;
	
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	@Override
	public boolean verifyCredentials(String emailId, String password) {
		return this.loginDao.verifyCredentials(emailId, password);
	}

}
