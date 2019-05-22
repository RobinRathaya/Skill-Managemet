package com.chainsys.util;


import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.chainsys.model.Student;

public class SendEmail {

	public static void sendMail(Student studentInfoModel) throws Exception {
		try {
			HtmlEmail email = new HtmlEmail();
			email.setSmtpPort(587);
			email.setAuthenticator(new DefaultAuthenticator("kaviarasan150697@gmail.com", "K@viyarasan15"));
			email.setDebug(false);
			email.setHostName("smtp.gmail.com");
			email.setFrom("kaviarasan150697@gmail.com");
			email.setSubject("Login credentials");
			email.setHtmlMsg("<html><body><div>user name:" + studentInfoModel.getUserName() + "</div><div>password:"
					+ studentInfoModel.getPassword() + "</div></body></html>");
			email.addTo(studentInfoModel.getEmail());
			email.setStartTLSEnabled(true);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
			throw new Exception("unable to sent mail!");
		}
	}
}
