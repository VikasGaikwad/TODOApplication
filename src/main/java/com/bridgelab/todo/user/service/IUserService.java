/**
 * 
 */
package com.bridgelab.todo.user.service;

import javax.servlet.http.HttpServletRequest;

import com.bridgelab.todo.user.model.User;

/**
 * @author bridgeit
 *
 */
public interface IUserService {
	public void registerUser(User user, String emailVerificationUrl);

	/* public String loginUser(String email, String password); */
	public String loginUser(User user);

	User getUserById(long userId);

	public User sendingMail(User user);

	public User getUserByEmail(String email);

	public void forgotPassword(User user, String forgotPasswordUrl);

	public User getObjByUUID(String randomUUID);

	public String resetPassword(String token, HttpServletRequest request, String newPassword);

	public String activateAccount(String token, HttpServletRequest request);

	

}
