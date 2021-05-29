package com.example.demo.controller;

import java.util.List;

import com.example.demo.entity.Attend;
import com.example.demo.entity.FACMeeting;
import com.example.demo.repository.SubCommiteeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Matters;
import com.example.demo.entity.SubComittee;
import com.example.demo.service.SubCommiteeService;

@CrossOrigin(origins = "*")
@RestController
public class SubCommiteeController {


    @Autowired
    private SubCommiteeService service;
    private SubCommiteeRepository repository;

    public SubCommiteeController(SubCommiteeService service, SubCommiteeRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @PostMapping(path = "/subcomittee")
    public ResponseEntity<?> create(@RequestBody SubComittee subcomittee) {
        return service.create(subcomittee);
    }

    @GetMapping(path = "/subcomittee/get")
    public List<SubComittee> getAll() {
        return service.getAll();
    }

    @PostMapping(path = "/subcomittee/fileupload/{id}")
    public ResponseEntity<?> uploadFile(@PathVariable(value = "id") Long id, @RequestBody SubComittee subcomittee) {
        return service.uploadFile(id, subcomittee);
    }

    @PutMapping(path = "/subcomittee/statusupdate/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable(value = "id") Long id, @RequestBody SubComittee subcomittee) {
        return service.updateStatus(id, subcomittee);
    }

    @GetMapping(path = "/subcomittee/{id}")
    public SubComittee getSubComitteebyId(@PathVariable("id") Long id) {
        return this.service.getBySubcomitteeId(id);
    }


    @GetMapping(path = "/subcomittee/getSubcommiteeByFacId/{id}")
    public List<SubComittee> getSubcommiteeByFacId(@PathVariable("id") Integer meetingId) {
        return service.getByfacmeeting_id(meetingId);
    }

    @GetMapping(path = "/subcomittee/getSubcommiteeBeforeAddMeeting")
    public List<SubComittee> getSubcomiteeForAddMeeting() {
        return service.getSubcomiteeForAddMeeting();
    }

    @PutMapping(path = "/subcomittee/ReportSubmitedIdUpdate")
    public String updateStatus(@RequestParam long commiteeId, @RequestParam int meetingId) {
        this.service.updateMeetingId(commiteeId, meetingId);
        return "success";
    }

}
