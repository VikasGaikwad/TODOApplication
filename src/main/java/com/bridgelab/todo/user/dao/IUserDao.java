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

	User getUserById(long userId);

	User sendingMail(User user);

	User getUserByEmail(String email);

	String getUserEmailId(String randomUUID);

	boolean resetPassword( String username,String password);

	User getUserByRandomId(String randomUUID);

	User updateRecord(User user);



}
