package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.FACMeeting;
import com.example.demo.entity.Matters;
import com.example.demo.repository.FACMeetingRepository;
import com.example.demo.repository.MattersRepository;

@Service
public class MattersService {
@Autowired
private MattersRepository repo;
@Autowired
private FACMeetingRepository repository;

public List<Matters> getByFacMeetingId(Integer meetingId) {
	return this.repo.getByfacmeeting_id(meetingId);
}

public void submitMeetingMatters(int meetingId, String matters[],String deci[], String deci_by[]) {
	FACMeeting facmeeting=repository.findById(meetingId).orElse(null);
	List<Matters> matterList=new ArrayList();
	for (int i = 0; i < matters.length; i++) {
		Matters matter = new Matters(facmeeting,matters[i],deci[i],deci_by[i]);
		matterList.add(matter);
	}
	repo.saveAll(matterList);
}
}
