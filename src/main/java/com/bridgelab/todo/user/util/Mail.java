/**
 * 
 */
package com.bridgelab.todo.user.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author bridgeit
 *
 */
public class Mail {
	@Autowired
	MailSender mailSending;

	

	public void setMailSending(MailSender mailSending) {
		this.mailSending = mailSending;
	}
	
	public boolean sendMail(String to,String from,String subject) {
		
		SimpleMailMessage message=new SimpleMailMessage();
		
		message.setFrom(from);
		
		message.setTo(to);
		message.setSubject(subject);
		
		//message.setText(msg);
		System.out.println("in Mail class");
		mailSending.send(message);
		System.out.println("after Mail class");
		return true;
	}

}
