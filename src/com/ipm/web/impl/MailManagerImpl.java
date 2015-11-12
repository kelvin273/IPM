package com.ipm.web.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailManagerImpl {

	public static void sendMail(String from, String message) {

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from, from));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					"kelvin.273@gmail.com", "Mr. User"));
			msg.setSubject("Contact Message from IPM web site");
			msg.setText(message);
			Transport.send(msg);

		} catch (Exception e) {
			// ...
			
		}
	}
}
