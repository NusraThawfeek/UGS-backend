package com.example.demo.entity;

import java.sql.Date;
import java.util.List;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "fac_meeting")
public class FACMeeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @DateTimeFormat(pattern = "yyyy/mm/dd")
    private Date date;
    private String meetingTime;
    private String meetingLink;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facMeeting")
    @JsonIgnore
    private List<Memo> memos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facMeeting1")
    @JsonIgnore
    private List<Request> requests;

    @OneToMany(mappedBy = "facmeeting")
    @JsonIgnore
    private List<Attend> attends;


    @OneToMany(mappedBy = "facmeeting")
    @JsonIgnore
    private List<Matters> matters;


    @ManyToOne(cascade = CascadeType.ALL)
    private AssistentRegistrar assistantRegistrar;


    @OneToMany(mappedBy = "facmeeting")
    @JsonIgnore
    private List<Priliminary> priliminary;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdFacMeeting")
    private List<SubComittee> createSubCommitee;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportSubmittedFacMeeting")
    private List<SubComittee> reportSubmittedSubCommitee;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "location_id1", referencedColumnName = "id")
    private Location location;


    public FACMeeting(Date date, String meetingTime, String meetingLink, Location location) {
        super();
        this.date = date;
        this.meetingTime = meetingTime;
        this.meetingLink = meetingLink;
        this.location = location;
    }

}
