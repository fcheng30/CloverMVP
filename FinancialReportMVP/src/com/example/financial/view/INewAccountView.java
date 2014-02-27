package com.example.financial.view;

import com.example.financial.model.BankAccount;

public interface INewAccountView {
	/**
	 * get AccountName from user's input
	 */
	String getAcName();
	
	/**
	 * get AccountDisplayName from user's input
	 */
	String getDisName();

	/**
	 * get Userid from intent extra.
	 */
	String getUserid();
	
	/**
	 * get Balance from user's input
	 */
	String getBalance();
	
	/**
	 * get Monthly Interest Rate from user's input
	 */
	String getMIR();
	
	/**
	 * add new account into database
	 */
	void addAccount(BankAccount ba);
	
	/**
	 * set display result text to show any error
	 */
	boolean checkAccount();
	void setText(String text);
	void goAccountPage();

	
}
