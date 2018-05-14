/**
 * 
 */
package com.bridgelab.todo.user.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.bridgelab.todo.user.model.User;

/**
 * @author bridgeit
 *
 */
public interface IUserService {
	 void registerUser(User user, String emailVerificationUrl);

	/* public String loginUser(String email, String password); */
	 String loginUser(User user);

	 User getUserById(long userId);

	 User sendingMail(User user);

	 User getUserByEmail(String email);

	 void forgotPassword(User user, String forgotPasswordUrl);

//	 User getObjByUUID(String randomUUID);

	 String resetPassword(String token, HttpServletRequest request, String newPassword);

	 String activateAccount(String token, HttpServletRequest request);

	void saveImage(MultipartFile fileUpload, int userId) throws IOException;

	

}
