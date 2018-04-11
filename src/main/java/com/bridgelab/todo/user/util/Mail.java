/**
 * 
 */
package com.bridgelab.todo.user.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.bridgelab.todo.user.model.User;

/**
 * @author bridgeit
 *
 */
public class Mail {
	@Autowired
	MailSender mailSending;
	User user;
	public void setMailSending(MailSender mailSending) {
		this.mailSending = mailSending;
	}	
	public boolean sendMail(String to,String msg) {		
		
		String from = "vikas343430@gmail.com";
		String subject = "succssfully reistered, click on link to activate account";
		SimpleMailMessage message=new SimpleMailMessage();		
		message.setFrom(from);

		message.setTo(to);
		message.setText(msg);		
		mailSending.send(message);
		message.setSubject(subject);
		System.out.println("mail sent successfully");
		return true;
	}


}
