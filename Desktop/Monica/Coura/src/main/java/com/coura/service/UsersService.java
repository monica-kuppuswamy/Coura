package com.coura.service;

import java.util.List;

import com.coura.model.Users;

public interface UsersService {
	public Users findUserByEmailId(String emailId);
	public boolean isExistingEmailId(String emailId);
	public boolean saveUsers(Users user);
	public List<Users> listAllUsers();
	public void deleteUser(String emailId);
}
