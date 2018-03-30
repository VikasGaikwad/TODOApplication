/**
 * 
 */
package com.bridgelab.todo.user.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelab.todo.user.dao.IUserDao;
import com.bridgelab.todo.user.model.User;
import com.bridgelab.todo.user.util.Mail;
import com.bridgelab.todo.user.util.Validator;

/**
 * @author bridgeit
 *
 */
public class UserServiceImpl implements IUserService {
	@Autowired
	IUserDao userDao;

	@Autowired
	Validator validateBean;
	@Autowired
	Mail mailService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public void registerUser(User user, String requestUrl) {

		String hashCode = passwordEncoder.encode(user.getPassword());

		user.setPassword(hashCode);
		String randomUUID = UUID.randomUUID().toString();
		System.out.println(randomUUID);
		user.setRandomUUID(randomUUID);
		int id = userDao.registerUser(user);
		System.out.println("record number " + id);
		if (id > 0) {
			String to = user.getEmail();			
			String from = "vikas343430@gmail.com";
			String message = requestUrl + "/RegistrationConfirm/"+ randomUUID;
			String subject="click to activate account";

			mailService.sendMail(to, from, message,subject);
		}

		/*
		 * if(count>0) { String to=user.getEmail();
		 * System.out.println(" mail id in UserServiceImpl----------"+to); String
		 * from=user.getEmail(); Mail.sendMail(to, from); }
		 */
	}

	@Override
	public User loginUser(User user) {

		return userDao.loginUser(user);
	}

	@Override
	public User getUserById(long userId) {

		return userDao.getUserById(userId);
	}

	@Override
	public User sendingMail(User user) {

		return userDao.sendingMail(user);
	}

	@Override
	public User getUserByEmail(String email) {

		return userDao.getUserByEmail(email);
	}

}
