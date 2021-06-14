package com.example.demo.service;

import java.sql.Date;
import java.util.List;

import com.example.demo.entity.FACMeeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Attend;
import com.example.demo.repository.AttendRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.example.demo.payload.MessageResponse;

import javax.persistence.Id;

@Service
public class AttendService {

    @Autowired
    private AttendRepository repository;

    public AttendService(AttendRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private FACMemberService facMemberService;

    @Autowired
    private FACMeetingService fACMeetingService;

    public ResponseEntity<?> create(List<Attend> attendance) {
        int count = 0;
        for (int i = 0; i < attendance.size(); i++) {
            Attend attend = repository.getByfacmeeting_idAndFacmember_userId(attendance.get(i).getFacmeeting().getId(),
                    attendance.get(i).getFacmember().getUserId());
            if (attend == null) {
                this.repository.save(attendance.get(i));
            } else {
                Attend updateData = attend;
                updateData.setId(attend.getId());
                updateData.setAttendence(attendance.get(i).getAttendence());
                this.repository.save(updateData);
            }
            count++;
        }
        return ResponseEntity.status(200).body(new MessageResponse("Created Successfully"));
    }

    public List<Attend> getByFacMeetingId(Integer meetingId) {
        return this.repository.getByfacmeeting_id(meetingId);
    }

    public List<Attend> getByFacMeetingDate(Date date) {
        return this.repository.getByfacmeeting_date(date);
    }

    public List<Attend> getByfacmeetingIdAndAttendance(Integer meetingId, String attendence) {
        return this.repository.getByfacmeeting_idAndAttendence(meetingId, attendence);
    }

    public List<Attend> getByfacmeeting_idAndApologyIsNotNull(Integer facId) {
        return this.repository.getByfacmeeting_idAndApologyIsNotNull(facId);
    }
}