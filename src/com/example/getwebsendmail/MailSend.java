package com.example.getwebsendmail;

import com.example.mailserver.MailSender;

public class MailSend {
	public void sendMail() throws Exception {
		MailSender mailSender = new MailSender("smtp.163.com", "jianghuatemporary", "01290129", true);
		mailSender.sendMail("subject", "body", "jianghuatemporary@163.com", "jianghua5417@163.com");
	}	
}
