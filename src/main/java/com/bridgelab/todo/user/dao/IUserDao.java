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



}
