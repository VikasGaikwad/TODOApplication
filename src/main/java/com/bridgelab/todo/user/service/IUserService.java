/**
 * 
 */
package com.bridgelab.todo.user.service;

import com.bridgelab.todo.user.model.User;

/**
 * @author bridgeit
 *
 */
public interface IUserService {
	public void registerUser(User user);
	public User loginUser(User user);
	
	User getUserById(long userId);
	public User sendingMail(User user);

	

}
