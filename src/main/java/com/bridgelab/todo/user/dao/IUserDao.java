/**
 * 
 */
package com.bridgelab.todo.user.dao;

import com.bridgelab.todo.user.model.User;

/**
 * @author bridgeit
 *
 */
public interface IUserDao {

	

	int registerUser(User user);

	User loginUser(User user);

	User getUserById(long  userId);
	

	User sendingMail(User user);

	User getUserByEmail(String email);

	User getObjByUUID(String randomUUID);

	boolean resetPassword( String randomUUID,String password);

	User getUserByRandomId(String randomUUID);

	User updateRecord(User user);

	String getUserPassword();

	



}
