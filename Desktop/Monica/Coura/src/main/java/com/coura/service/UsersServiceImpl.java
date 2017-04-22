package com.coura.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coura.dao.UsersDao;
import com.coura.model.Users;

@Service
public class UsersServiceImpl implements UsersService {
	
	private UsersDao userDao;

	public void setUserDao(UsersDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	@Transactional
	public Users findUserByEmailId(String emailId) {
		return this.userDao.findUserByEmailId(emailId);
	}
	
	@Override
	@Transactional
	public boolean isExistingEmailId(String emailId) {
		return this.userDao.isExistingEmailId(emailId);
	}
	
	@Override
	@Transactional
	public boolean saveUsers(Users user) {
		if (this.isExistingEmailId(user.getEmailId())) {
			return false;
		} else {
			this.userDao.saveUsers(user);
			return true;
		}
	}
	
	@Override
	@Transactional
	public List<Users> listAllUsers() {
		return this.userDao.listAllUsers();
	}
	
	@Override
	@Transactional
	public void deleteUser(String emailId) {
		this.userDao.deleteUser(emailId);
	}
}