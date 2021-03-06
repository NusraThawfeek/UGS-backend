package com.example.demo.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AlternativeModuleRequest;
import com.example.demo.entity.AssistentRegistrar;
import com.example.demo.entity.CommentKey;
import com.example.demo.entity.Commented;
import com.example.demo.entity.FACMember;
import com.example.demo.entity.MRoles;
import com.example.demo.entity.ModuleDiscontinue;
import com.example.demo.entity.Request;
import com.example.demo.entity.Roles;
import com.example.demo.repository.AssistentRegistrarRepository;
import com.example.demo.repository.CommentedRepository;
import com.example.demo.repository.FACMemberRepository;
import com.example.demo.repository.RequestRepository;
import com.example.demo.repository.RolesRepository;
import com.example.demo.repository.UserRepository;

@Service
public class CommentedService {

	@Autowired
	CommentedRepository crepo;

	@Autowired
	FACMemberRepository frepo;

	@Autowired
	AssistentRegistrarRepository arrepo;

	@Autowired
	UserRepository urepo;

	@Autowired
	RequestRepository rrepo;

	@Autowired
	RolesRepository rolesrepo;

	@Autowired
	private RequestService sservice;

	@Autowired
	private DecisionMailService mailService;
	
	@Autowired
	private AlternativeModuleRequestService alterService;

	public Commented getByRidAndUid(Request rid, FACMember uid) {
		// TODO Auto-generated method stub
		return crepo.findByRidAndUid(rid, uid).orElse(null);
	}

	public Commented getComment(CommentKey cid) {
		// TODO Auto-generated method stub
		return crepo.findById(cid).orElse(null);
	}

	public Commented updateComment(Commented c) throws UnsupportedEncodingException, MessagingException {
		Commented comment = getComment(c.getCid());
		if ((comment.isIsForwarded() == false) && (c.isIsForwarded() == true)) {
			if (c.getUid().isAcademicAdvisor() == true) {
				// find department
				String dep = c.getUid().getDepartment();

				// hod email by dep
				FACMember f = getHod(dep);
				mailService.newRequest(f, "HOD", c.getRid().getStd());

			}
			if (c.getUid().isHod() == true) {

				FACMember f = frepo.findByIsDean(true);
				mailService.newRequest(f, "Dean", c.getRid().getStd());
			}

			if (c.getUid().isDean() == true) {

				FACMember f = frepo.findByIsDugs(true);
				mailService.newRequest(f, "DUGS", c.getRid().getStd());
			}

			if (c.getUid().isDugs() == true) {

				Optional<Roles> role = rolesrepo.findByName(MRoles.ROLE_AR);
				AssistentRegistrar ar = urepo.findByRoles(role);

				mailService.newRequest(ar, "AR", c.getRid().getStd());

			}
		}
		comment.setDescription(c.getDescription());
		comment.setIsForwarded(c.isIsForwarded());
		comment.setIsRecomended(c.isIsRecomended());
		comment.setIsRejected(c.isIsRejected());
		comment.setEnteredDate(c.getEnteredDate());
		return crepo.save(comment);
	}

	public FACMember getHod(String Dep) {
		FACMember f = frepo.findByDepAndIshod(Dep, true);
		return f;
	}

	public Commented addComment(Commented c) throws UnsupportedEncodingException, MessagingException {
		// TODO Auto-generated method stub
		Commented comment = crepo.save(c);
		if (c.isIsForwarded() == true) {
			if (c.getUid().isAcademicAdvisor() == true) {
				// find department
				String dep = c.getUid().getDepartment();

				// hod email by dep
				FACMember f = getHod(dep);
				mailService.newRequest(f, "HOD", c.getRid().getStd());

			}
			if (c.getUid().isHod() == true) {

				FACMember f = frepo.findByIsDean(true);
				mailService.newRequest(f, "Dean", c.getRid().getStd());
			}

			if (c.getUid().isDean() == true) {

				Optional<Roles> role = rolesrepo.findByName(MRoles.ROLE_AR);
				AssistentRegistrar ar = urepo.findByRoles(role);

				mailService.newRequest(ar, "AR", c.getRid().getStd());
			}

		}
		if (c.isIsRejected()) {
			Request r = sservice.getRequest(c.getRid().getRid());
			
			String role = "";
			
			if(c.getUid().isAcademicAdvisor()){
				role = "Acadamic Advisor";
			}
			
			else if(c.getUid().isHod()){
				role = "HOD";
			}
			
			else if(c.getUid().isDean()){
				role = "Dean";
			}

			r.setStatus("Rejected by " + role);
			r.setDecision(c.getDescription());
			
			if(r.getType().equals("Alternative")) {
				AlternativeModuleRequest alterRequest = alterService.getModuleDiscontinue(r.getRid());
				List<ModuleDiscontinue> moduleDiscontinue = alterRequest.getModuleDiscontinue();
				
				for(int i = 0; i < moduleDiscontinue.size(); i++) {
					moduleDiscontinue.get(i).setAlterMcode("Rejected");
					moduleDiscontinue.get(i).setOfferingSem("Rejected");
				}
			}
			
			mailService.decsisionEmail(r, "http://localhost:3000");
			rrepo.save(r);

		}
		return comment;

	}

	public boolean editForAcademivAdv(Request rid, Long id) {
		String dep = frepo.findById(id).get().getDepartment();
		FACMember f = getHod(dep);

		if ((frepo.findById(id).get().isAcademicAdvisor())) {

			if ((getByRidAndUid(rid, f) != null)) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}

	}

	public boolean editForHOD(Request rid, Long id) {

		FACMember f = frepo.findByIsDean(true);

		if (frepo.findById(id).get().isHod()) {

			if ((getByRidAndUid(rid, f) != null)) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	public boolean editForDean(Request rid, Long id) {

		// FACMember f = frepo.findByIsDugs(true);

		if (frepo.findById(id).get().isDean()) {
			if (rid.getIsSendToFacBoard()) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	public boolean editForDugs(Request rid, Long id) {

		if (frepo.findById(id).get().isDugs()) {
			if (rid.getIsSendToFacBoard()) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}

	}

}
