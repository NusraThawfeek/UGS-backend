package com.example.demo.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Request;
import com.example.demo.entity.Student;

@Service
public class DecisionMailService {

	@Autowired
    private JavaMailSender mailSender;
	
	public void decsisionEmail(Request request, String siteURL)
	        throws MessagingException, UnsupportedEncodingException {
		
		Student student = request.getStd();
		
	    String toAddress = student.getEmail();
	    String fromAddress = "mhdrazan200@gmail.com";
	    String senderName = "Dr. Supunmali Ahangama";
	    String subject = "Decsision added for your request";
	    String content = "Dear [[name]],<br>"
	            + "Please click the link below to view your request status:<br>"
	            + "<h3><a href=\"[[URL]]\" target=\"_self\">Click</a></h3>"
	            + "Thank you,<br>"
	            + "Director/Undergraduate Studies, <br>"
	            + "Faculty of Information Technology,<br>"
	            + "University of Moratuwa";
	     
	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom(fromAddress, senderName);
	    helper.setTo(toAddress);
	    helper.setSubject(subject);
	     
	    content = content.replace("[[name]]", student.getIndexNo());
	    String verifyURL = siteURL + "/pastrequest/past_leave_request/" + request.getRid();
	     
	    content = content.replace("[[URL]]", verifyURL);
	     
	    helper.setText(content, true);
	     
	    mailSender.send(message);
	     
	}
}
