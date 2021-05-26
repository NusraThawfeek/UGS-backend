package com.example.demo.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.AssistentRegistrar;
import com.example.demo.entity.FACMeeting;
import com.example.demo.entity.FACMember;
import com.example.demo.entity.MRoles;
import com.example.demo.entity.Roles;
import com.example.demo.repository.FACMeetingRepository;
import com.example.demo.repository.RolesRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.controller.MailController;

import com.example.demo.payload.MessageResponse;

@Service
public class FACMeetingService {

    @Autowired
    private FACMeetingRepository repository;
    private MailController mailController;
    private FACMemberService facmemberservice;
    @Autowired
    private MeetingEmailService meetingEmailService;

    @Autowired
    UserRepository urepo;

    @Autowired
    RolesRepository rolesrepo;

    public FACMeetingService(FACMeetingRepository repository, MailController mailController,
                             FACMemberService facmemberservice) {
        this.repository = repository;
        this.mailController = mailController;
        this.facmemberservice = facmemberservice;
    }

    public FACMeeting getMeeting(int facMeetingId) {
        return repository.findById(facMeetingId).orElse(null);
    }

    //my
    public FACMeeting getByMeetingId(Integer id) {
        return repository.getById(id);
    }

    public ResponseEntity<?> create(@RequestBody FACMeeting facMeeting) {

        Optional<Roles> role = rolesrepo.findByName(MRoles.ROLE_AR);
        AssistentRegistrar ar = urepo.findByRoles(role);

        facMeeting.setAssistantRegistrar(ar);

        repository.save(facMeeting);

        return ResponseEntity.status(200).body(new MessageResponse("Created Successfully"));

    }

    public List<FACMeeting> getAll() {
        return repository.findAll();
    }

    public ResponseEntity<?> Update(Integer Id, FACMeeting facMeeting) {

        var allfacs = repository.findAll();

        System.out.println(allfacs.size());
        FACMeeting facResponse = null;

        for (int i = 0; i < allfacs.size(); i++) {
            Integer facmetid = allfacs.get(i).getId();

            Integer id = allfacs.get(i).getId();
            if (Id.equals(facmetid) && Id.equals(facMeeting.getId())) {

                FACMeeting factoUpdate = repository.getByMeetingIDS(facmetid);
                factoUpdate.setId(id);
                factoUpdate.setLocation(facMeeting.getLocation());
                factoUpdate.setDate(facMeeting.getDate());
                factoUpdate.setMeetingTime(facMeeting.getMeetingTime());

                facResponse = repository.save(factoUpdate);
//                mail(factoUpdate); //uncomment this line for send email for successful update
                return ResponseEntity.ok("SuccessFully Updated");
            } else if (Id.equals(facmetid) && (!Id.equals(facMeeting.getId()))) {
                return ResponseEntity.status(404).body(new MessageResponse("Meeting Id Not Matched"));
            }
        }

        return ResponseEntity.status(404).body(new MessageResponse("Not Updated"));
    }

    public ResponseEntity<?> sendmail(int id, String[] mail) {

        FACMeeting newmeeting = repository.getById(id);
        if (newmeeting != null) {
            for (int i = 0; i < mail.length; i++) {
                mail(newmeeting, mail[i]);
            }
            return ResponseEntity.status(200).body(new MessageResponse("email send"));
        } else {
            return ResponseEntity.status(400).body(new MessageResponse("email not send"));
        }

    }

    public void mail(FACMeeting facMeeting, String email) {

        MultiValueMap<String, String> mMap = new LinkedMultiValueMap<>();
        mMap.add("emailTo", email);
        mMap.add("emailFrom", "teamaliens.b18it@gmail.com");
        mMap.add("emailSubject", "FAC Meeting");
        mMap.add("emailContent",
                "FAC Meeting ID: " + facMeeting.getId() + "\n" + "Meeting Location: " + facMeeting.getLocation() + "\n"
                        + "Meeeting Date: " + facMeeting.getDate() + "\n" + "Meeting Time: "
                        + facMeeting.getMeetingTime());

        mailController.sendmail(mMap);

    }

    public void scmail(FACMeeting facMeeting, String email) {
        if (facMeeting.getAgendaLink() == null) {
            facMeeting.setAgendaLink("http://localhost:3000/view-agenda/" + facMeeting.getId());
        }

        MultiValueMap<String, String> mMap = new LinkedMultiValueMap<>();
        mMap.add("emailTo", email);
        mMap.add("emailFrom", "teamaliens.b18it@gmail.com");
        mMap.add("emailSubject", "FAC Meeting");
        mMap.add("emailContent",
                "FAC Meeting ID: " + facMeeting.getId() + "\n" + "Meeting Location: " + facMeeting.getLocation() + "\n"
                        + "Meeeting Date: " + facMeeting.getDate() + "\n" + "Meeting Time: "
                        + facMeeting.getMeetingTime() + "\nLink: " + facMeeting.getAgendaLink());

        mailController.sendmail(mMap);

    }

    public void runmail() {
        List<FACMeeting> f = repository.findAll();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");
        String abc = formatter.format(date);

        for (int i = 0; i < f.size(); i++) {
            try {

                String idate = formatter.format(f.get(i).getDate());
                Date date1 = formatter.parse(abc);
                Date date2 = formatter.parse(idate);

                long diff = date2.getTime() - date1.getTime();

                if (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) == 1) {
                    List<FACMember> sm = facmemberservice.getAll();
                    for (int m = 0; m < sm.size(); m++) {
                        this.scmail(f.get(i), sm.get(m).getEmail());
                    }

                }
                System.out.println("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
            } catch (ParseException e) {
                e.printStackTrace();

            }
        }

    }

    public ResponseEntity<?> updateAgendaItems(FACMeeting facMeeting) {
        FACMeeting fm = repository.findById(facMeeting.getId()).orElse(null);
        fm.setAgendaItem(facMeeting.getAgendaItem());
        fm.setAgendaLink(facMeeting.getAgendaLink());
        FACMeeting res = repository.save(fm);
        return ResponseEntity.ok(res);
    }

    public ResponseEntity<?> updateMinuteItems(FACMeeting facMeeting) {
		FACMeeting fm = repository.findById(facMeeting.getId()).orElse(null);
		fm.setPriliminaries(facMeeting.getPriliminaries());
		fm.setMinuteLink1(facMeeting.getMinuteLink1());
		FACMeeting res = repository.save(fm);
		return ResponseEntity.ok(res);
	}
	public ResponseEntity<?> updateMinuteItemsByDugs(FACMeeting facMeeting) {
		FACMeeting fm = repository.findById(facMeeting.getId()).orElse(null);
		if(fm.getPriliminaries()!=null) {
		fm.setPriliminaries(fm.getPriliminaries()+","+facMeeting.getPriliminaries());
		}else {
			fm.setPriliminaries(facMeeting.getPriliminaries());
		}
		FACMeeting res = repository.save(fm);
		return ResponseEntity.ok(res);
	}

    public FACMeeting findUpcomingMeeting() {
        return repository.findUpcomingMeeting();
    }

    public List<FACMeeting> findPastMeeting() {
        return repository.pastMeeting();
    }

}
