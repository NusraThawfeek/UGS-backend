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

public List<Matters> getAgendaByFacMeetingId(Integer meetingId,Boolean agenda) {
	return this.repo.getByfacmeeting_idAndAgenda(meetingId,agenda);
}
public List<Matters> getMinuteByFacMeetingId(Integer meetingId,Boolean minute) {
	return this.repo.getByfacmeeting_idAndMinute(meetingId,minute);
}

public List<Matters> getByfacmeeting_id(Integer meetingId){
	return this.repo.getByfacmeeting_id(meetingId);
}

public void submitMeetingMatters(int meetingId, String matters[],String deci[], String deci_by[]) {
	FACMeeting facmeeting=repository.findById(meetingId).orElse(null);
	List<Matters> matterList=new ArrayList();
	for (int i = 0; i < matters.length; i++) {
		Matters matter = new Matters(facmeeting,matters[i],deci[i],deci_by[i]);
		matter.setMinute(true);
		matterList.add(matter);
	}
	repo.saveAll(matterList);
}
public void submitAgendaItems(int meetingId, String matters[]) {
	FACMeeting facmeeting=repository.findById(meetingId).orElse(null);
	List<Matters> agendaList=new ArrayList();
	for (int i = 0; i < matters.length; i++) {
		Matters matter = new Matters(facmeeting,matters[i]);
		matter.setAgenda(true);
		agendaList.add(matter);
	}
	repo.saveAll(agendaList);
}
public void submitAgendaDecision(int Id[], String deci[], String deci_by[]) {
	for (int i = 0; i < Id.length; i++) {
		Matters matter = repo.findById(Id[i]).orElse(null);
		matter.setDecission(deci[i]);
		matter.setDecissionBy(deci_by[i]);
		repo.save(matter);
	}
}
public List<Matters> getAgendaWithoutDecission(int id){
	return this.repo.findAgendaWithoutDecision(id);
}



}
