package com.backend.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

	public boolean sendEmail(String to, String subject, String message) {
		
		boolean flag = false;
		
		//from address
		String from = "sripriyanagaraju@gmail.com";
		
		//variable for gmail host
		String host = "smtp.gmail.com";
		
		//get the system property
		Properties properties = System.getProperties();
		
		//set the important information to that properties object
		//set the host, port, username, password
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		//steps 1 : to get the session object by giving proper username and password to authentication
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("sripriyanagaraju@gmail.com", "pzoz oipu tdsz kupp");
			}
			
		});
		
		session.setDebug(true);
		
		//step 2 : compose the message [text, multimedia like image, video, etc]
		MimeMessage mimeMessage = new MimeMessage(session);
		
		try {
			//set from mail
			mimeMessage.setFrom(from);
			
			//add recipient i.e. to address
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			//set subject
			mimeMessage.setSubject(subject);
			
			//set message
			mimeMessage.setText(message);
			
			//step 3 : send message using Transport class
			Transport.send(mimeMessage);
			System.out.println("sent successfully...");
			
			flag = true;
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
}
