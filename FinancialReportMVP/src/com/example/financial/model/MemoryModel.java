package com.example.financial.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This is the actual implementation of the IModel interface.
 * @author Farong Cheng
 *
 */
public class MemoryModel implements IUserModel{
	
	/** the data store of all users */
	private static Map<String, User> users = new HashMap<String, User>();
	
	static {
		users.put("admin", User.ADMIN);
	}
	
	@Override
	public void addUser(User newuser) {
		users.put(newuser.getUserid(), newuser);
		
	}

	@Override
	public void removeUser(String uid) {
		users.remove(uid);
		
	}

	@Override
	public User findUser(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resetPW(String uid) {
		// TODO Auto-generated method stub
		
	}

}
