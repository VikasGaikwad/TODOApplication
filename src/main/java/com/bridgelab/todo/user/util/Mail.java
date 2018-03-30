/**
 * 
 */
package com.bridgelab.todo.user.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
	public boolean sendMail(String to,String from,String msg,String subject) {		
		SimpleMailMessage message=new SimpleMailMessage();		
		message.setFrom(from);
		
		message.setTo(to);
		message.setText(msg);		
		mailSending.send(message);
		message.setSubject(subject);
		System.out.println("mail sent successfully");
		return true;
	}
	
	/*@Autowired
	MailSender mailSending;
	public static boolean sendMail(String to,String from){
		System.out.println(" mail id in Mail class----------"+to);
	String host="localhost";
	final String user="vikas343430@gmail.com";
	final String password="vikas@123456";
	String too=to;
	Properties properties=new Properties();
	properties.put("mail.smtp.host", "smtp.gmail.com");
	properties.put("mail.smtp.port", "587");
	properties.put("mail.smtp.auth", "true");
	properties.put("mail.smtp.starttls.enable", "true");
	//properties.put("mail.smtp.port", 465);
	//properties.put("mail.smtp.host","smtp.gmail.com");
	//properties.put("mail.smtp.auth", "true");
	
	Session session=Session.getDefaultInstance(properties,new Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(user, password);
		}
	});
	
	
	try {
		
		MimeMessage message=new MimeMessage(session);
		message.setFrom(new InternetAddress(user));
		System.out.println("hiiiiiiiiiiiiii");
		message.addRecipient(Message.RecipientType.TO	, new InternetAddress(too));
		message.setSubject("from vikas gaikwad...");
		message.setText("checkout the link...");
		Transport.send(message);
		System.out.println("mail sent successfully...");
		
	} catch (Exception e) {
		System.out.println("mail sending fail...");
		e.printStackTrace();
	}
	return true;
	}
	*/

	
}
