package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.CommentKey;
import com.example.demo.entity.Commented;
import com.example.demo.entity.FACMember;
import com.example.demo.entity.Request;
import com.example.demo.repository.CommentedRepository;
import com.example.demo.service.CommentedService;

@RestController
@CrossOrigin(origins = "*")
public class CommentController {

	@Autowired
	CommentedRepository crepo;

	@Autowired
	CommentedService cservice;

	@CrossOrigin("*")

	@GetMapping("/Commented")
	public List<Commented> getComments() {
		return crepo.findAll();
	}

	@GetMapping("/Commented/Request/{rid}")
	public List<Commented> FindByReqId(@PathVariable Request rid) {
		return crepo.findByrid(rid);
	}

	@GetMapping("/Commented/RequestbyUid/{uid}")
	public List<Commented> FindByReqId(@PathVariable FACMember uid) {
		return crepo.findByuid(uid);
	}

	@PostMapping("/Commented")
	public Commented addComment(@ModelAttribute Commented c) throws UnsupportedEncodingException, MessagingException {

		return cservice.addComment(c);
	}

	@PutMapping("/Commented/update")
	public Commented updateComment(@ModelAttribute Commented c)
			throws UnsupportedEncodingException, MessagingException {

		return cservice.updateComment(c);
	}

	@DeleteMapping("/Commented/{cid}")
	public String deleteComment(@PathVariable CommentKey cid) {

		Commented c = crepo.getOne(cid);
		crepo.delete(c);
		return "Deleted successfully";
	}

	@GetMapping("/Commented/RequestbyIds/{rid},{uid}")
	public Commented FindByIds(@PathVariable(value = "rid") Request rid, @PathVariable(value = "uid") FACMember uid) {
		return cservice.getByRidAndUid(rid, uid);
	}

	@GetMapping("/Commented/EditByAcademicAdvisor/{rid},{uid}")
	public boolean editForAcademivAdv(@PathVariable(value = "rid") Request rid, @PathVariable(value = "uid") Long uid) {
		return cservice.editForAcademivAdv(rid, uid);
	}

	@CrossOrigin("*")
	@GetMapping("/Commented/EditByHOD/{rid},{uid}")
	public boolean editForHOD(@PathVariable(value = "rid") Request rid, @PathVariable(value = "uid") Long uid) {
		return cservice.editForHOD(rid, uid);
	}

	@CrossOrigin("*")
	@GetMapping("/Commented/EditByDean/{rid},{uid}")
	public boolean editForDean(@PathVariable(value = "rid") Request rid, @PathVariable(value = "uid") Long uid) {
		return cservice.editForDean(rid, uid);
	}

	@GetMapping("/Commented/EditByDugs/{rid},{uid}")
	public boolean editForDUGS(@PathVariable(value = "rid") Request rid, @PathVariable(value = "uid") Long uid) {
		return cservice.editForDugs(rid, uid);
	}

}
