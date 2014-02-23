package com.example.financial.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * This is the actual implementation of the IModel interface.
 * @author Farong Cheng
 *
 */
public class MemoryModel implements IModel{
	
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
	public User findUserById(String uid) {
		User result = users.get(uid);
		if (result == null){
			return User.NULL_USER;
		}
		return result;
		
	}

	@Override
	public User[] getUsers() {
		Collection<User> uCopy = users.values();
		User[] uList = new User[uCopy.size()];
		int i = 0;
		for (User u : uCopy) uList[i++] = u;
		return uList;
	}

}
