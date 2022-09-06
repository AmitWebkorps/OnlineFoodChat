package com.onlinefoodchat.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class OtpService {

	@Autowired
	private JavaMailSender javaMailSender;

	public int sendOtpMail(String to) {
		int otp = this.generateOTP();
		String message = "Your One time Password is:" + otp + " only valid for 2 minutes";
		String subject = "One time Password";
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("amity1809@gmail.com");
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		javaMailSender.send(simpleMailMessage);
		return otp;
	}

	public int generateOTP() {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		return otp;
	}

	public boolean validateOtp(int Otp, String enteredOtp) {
		return false;
	}
}
