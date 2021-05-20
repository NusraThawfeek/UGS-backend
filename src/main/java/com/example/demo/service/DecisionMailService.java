package com.example.demo.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.demo.entity.FACMember;
import com.example.demo.entity.Memo;
import com.example.demo.entity.Request;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;

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
	
	public void decisionMemoEmail(Memo memo, String siteURL)
	        throws MessagingException, UnsupportedEncodingException {
		
		FACMember facMember = memo.getFacMember();
		
	    String toAddress = facMember.getEmail();
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
	     
	    content = content.replace("[[name]]", facMember.getNameToBeAppeared());
	    String verifyURL = siteURL + "/pastrequest/past_leave_request/" + memo.getMid();
	     
	    content = content.replace("[[URL]]", verifyURL);
	     
	    helper.setText(content, true);
	     
	    mailSender.send(message);
	     
	}
	
	public void newRequest(User member, String post, Student student)
	        throws MessagingException, UnsupportedEncodingException {
		
		
		
	    String toAddress = member.getEmail();
	    String fromAddress = "mhdrazan200@gmail.com";
	    String senderName = "Dr. Supunmali Ahangama";
	    String subject = "New Request";
	    String content = "Dear [[nameFACmember]],<br>"
	    		+"You have New Requests from [[nameStudent]].<br>"
	            + "Please click the link below to view New request :<br>"
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
	     
	    content = content.replace("[[nameStudent]]", student.getIndexNo());
	    String verifyURL ="http://localhost:3000/"+post+"/NewRequest";
	     
	    content = content.replace("[[URL]]", verifyURL);
	    content = content.replace("[[nameFACmember]]", member.getFirstName()+" "+member.getLastName());
	     
	    helper.setText(content, true);
	     
	    mailSender.send(message);
	     
	}
	
}
