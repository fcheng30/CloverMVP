package com.example.financial.view;

import com.example.financial.model.User;

public interface IRegisterView {
	/**
	 * get user id from user's input
	 */
	String getUsername();
	
	/**
	 * get user password from user's input
	 */
	String getPassword();
	
	/**
	 * get user name from user's input
	 */
	String getName();
	
	/**
	 * get user email
	 */
	String getEmail();
	
	/**
	 * addUser into model
	 */
	void addUser(User user);
	
	/**
	 * set display result text to show any error
	 */
	void setRegisterText(String text);
	
	/**
	 * Find user by given param
	 * @param uid
	 * @return
	 */
	User findUser(String uid);
	void goLoginPage();
}
