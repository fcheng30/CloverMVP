package com.example.financial.model;

import java.util.List;

public interface IUserModel {
	User findUser(String uid);
	void addUser(User user);
	void removeUser(String userid);
	List<User> getUserList();
	void resetPW(String uid);
}
