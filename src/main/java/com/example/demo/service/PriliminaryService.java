package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.FACMeeting;
import com.example.demo.entity.Matters;
import com.example.demo.entity.Priliminary;
import com.example.demo.repository.FACMeetingRepository;
import com.example.demo.repository.PriliminaryRepository;

@Service
public class PriliminaryService {
	@Autowired
	private PriliminaryRepository repo;
	@Autowired
	private FACMeetingRepository repository;
	
	public void submitPriliminaries(int meetingId, String priliminary[]) {
		FACMeeting facmeeting=repository.findById(meetingId).orElse(null);
		List<Priliminary> priliminaryList=new ArrayList();
		for (int i = 0; i < priliminary.length; i++) {
			Priliminary prilim = new Priliminary(facmeeting,priliminary[i]);
			priliminaryList.add(prilim);
		}
		repo.saveAll(priliminaryList);
	}
	public List<Priliminary> getPrilimByfacmeeting_id(Integer meetingId){
		return this.repo.getByfacmeeting_id(meetingId);
	}
	public void updatePriliminary(int Id,String priliminary) {
		Priliminary pril= repo.findById(Id).orElse(null);
		pril.setPriliminary(priliminary);
		repo.save(pril);
	}
	public Priliminary getPriliminaryById(int id) {
		return repo.findById(id).orElse(null);
	}
}
