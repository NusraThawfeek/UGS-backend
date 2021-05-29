package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.entity.Attend;
import com.example.demo.entity.FACMeeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.entity.FACMember;
import com.example.demo.entity.SubComittee;
import com.example.demo.repository.SubCommiteeRepository;
import com.example.demo.payload.MessageResponse;

@Service
public class SubCommiteeService {

    @Autowired
    private SubCommiteeRepository repository;

    @Autowired
    private FACMemberService facMemberService;

    public SubCommiteeService(SubCommiteeRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<?> create(SubComittee subcomittee) {
        repository.save(subcomittee);
        return ResponseEntity.status(200).body(new MessageResponse("created"));
    }

    public List<SubComittee> getAll() {
        return repository.findAll();
    }

    public ResponseEntity<?> uploadFile(Long id, SubComittee fileName) {
        SubComittee subcomitee = repository.findById(id).orElseThrow();
        subcomitee.setSubcomitteefileName(fileName.getSubcomitteefileName());
        SubComittee res = repository.save(subcomitee);
        return ResponseEntity.ok(res);
    }

    public ResponseEntity<?> updateStatus(Long id, SubComittee subcomittee) {
        SubComittee sm = repository.findById(id).orElseThrow();
        sm.setSubcomitteeStatus(subcomittee.getSubcomitteeStatus());
        SubComittee res = repository.save(sm);
        return ResponseEntity.ok(res);
    }

    public SubComittee getBySubcomitteeId(Long id) {
        return repository.getById(id);
    }

}
