package com.example.financial.model;

import java.util.List;

public interface IAccountModel {
	boolean checkAccount(String uid, String acname);
	void addAccount(BankAccount ba);
	List<BankAccount> getAccountList(String uid);
	void removeAccount(String userID, String displayName);
}
