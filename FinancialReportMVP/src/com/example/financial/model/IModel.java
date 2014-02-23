package com.example.financial.model;

/**
 * This is the Model interface for MemoryModel.
 * @author Farong Cheng
 *
 */
public interface IModel {
	
	/**********************************
	 * Add a new User to the model
	 * 
	 * @param newuser the user to add
	 */
	void addUser(User newuser);
	
	/**********************************
	 * Remove a user from the model 
	 * 
	 * @param uid the id of the user to remove
	 */
	void removeUser(String uid);
	
	/**********************************
	 * Lookup a user by their username
	 * 
	 * @param uid the id to search for
	 * 
	 * @return the user with that id or null if not found
	 */
	User findUserById(String uid);
	
	/**********************************
	 * Get all the users in the model as an array
	 * 
	 * @return an array of all users in the model.
	 */
	User[] getUsers();
}
