package com.example.financial.view;

import com.example.financial.model.User;

public interface ILoginView {
	/**
	 * get user id from user's input
	 */
	String getUserid();
	/**
	 * get user password from user's input
	 */
	String getUserPassword();
	/**
	 * find user from model
	 */
	User findUser(String uid);
	/**
	 * set display result text to show any error
	 */
	void setResultText(String text);
	void goUserPage();
	void goAdminPage();
}
