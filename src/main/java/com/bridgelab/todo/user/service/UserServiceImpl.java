/**
 * 
 */
package com.bridgelab.todo.user.service;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Transactional
	public void registerUser(User user) {		
		System.out.println("inside UserServiceImpl");
			int count=userDao.registerUser(user);
			if(count>0){
				System.out.println("before mail");
				String message="successfull send";
				String subject="confirm mail";
				String to=user.getEmail();
			//	User user3=user.getEmail();
			mailService.sendMail(to,user.getEmail(), subject);
			//mailService.sendMail(to, subject, msg, subject)
			System.out.println("after mail");
		System.out.println("ok");
	
			}
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

}
