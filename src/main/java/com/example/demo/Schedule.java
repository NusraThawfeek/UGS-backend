package com.example.demo;

import org.springframework.stereotype.Component;
import com.example.demo.service.FACMeetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

@Component 
public class Schedule {
	private FACMeetingService facmeetingservice;

	public Schedule(FACMeetingService facmeetingservice) {
		this.facmeetingservice = facmeetingservice;
	}

	private static final Logger log = LoggerFactory.getLogger(Schedule.class);

	@Scheduled(cron = "0 51 00 * * ?",zone="Asia/Kolkata")
	public void sendMail() {
		facmeetingservice.runmail();
		System.out.println("methods works");
		
	}

}
