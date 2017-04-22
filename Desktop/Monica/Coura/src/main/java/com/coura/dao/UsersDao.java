package com.coura.dao;

import java.util.List;

import com.coura.model.Users;

public interface UsersDao {
	public Users findUserByEmailId(String emaildId);
	public boolean isExistingEmailId(String emailId);
	public void saveUsers(Users user);
	public List<Users> listAllUsers();
	public void deleteUser(String emailId);
}
