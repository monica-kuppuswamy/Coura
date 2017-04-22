package com.coura.dao;

public interface LoginDao {
	public boolean verifyCredentials(String emailId, String password);
}
