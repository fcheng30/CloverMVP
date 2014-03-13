package com.example.financial.view;

import com.example.financial.model.Transaction;


public interface IAddTransactionView {
	String getName();
	String getType();
	String getDate();
	String getAmount();
	String getBKDisname();
	String getUserid();
	boolean addTrans(Transaction t);
	void setText(String t);
	void goBack();
}
