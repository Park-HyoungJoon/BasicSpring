package kr.inhatc.spring.login.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SendMail {
	
	@Autowired
	private JavaMailSender sender;
	
	public boolean sendmail(String address, String subject, String body) {
		
		System.out.println(address + " : 보낼주소");
		
		MimeMessage mes = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mes);
		
		try {
//			System.out.println("시작!");
			helper.setTo(address);
//			System.out.println("1성공");
			helper.setSubject(subject);
//			System.out.println("2성공");
			helper.setText(body);
//			System.out.println("3성공");
			
		} catch (MessagingException e) {
			System.out.println("메일 전송 실패");
			return false;
		}
		sender.send(mes);
		System.out.println("메일 전송 성공");
		return true;
	}
}