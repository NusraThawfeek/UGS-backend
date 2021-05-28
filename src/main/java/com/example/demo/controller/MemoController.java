package com.example.demo.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Memo;
import com.example.demo.service.FileDownloadService;
import com.example.demo.entity.Request;
import com.example.demo.service.MemoService;

@RestController
@CrossOrigin(origins = "*")
public class MemoController {

	@Autowired
	private MemoService service;
	
	@Autowired
	private FileDownloadService fileDownloadService;
	
	@PostMapping("/postmemo")
	public Memo postMemo(@RequestParam int facid, @RequestParam String description1, @RequestParam MultipartFile annex) {
		return service.postMemo(facid, description1, annex);
	}
	
	@GetMapping("/pastmemo/{mid}")
	public Memo getMemo(@PathVariable int mid) {
		return service.getMemo(mid);
	}
	
	@GetMapping("/pastallmemo")
	public List<Memo> getAllMemo(){
		return service.getAllMemo();
	}
	
	@GetMapping("/getallmemo")
	public List<Memo> getAllMemoDean(){
		return service.getAllMemoDean();
	}
	
	@GetMapping("/pastmemobyfacid/{facId}")
	public List<Memo> getMemoByFacId(@PathVariable int facId){
		return service.getMemoByFacId(facId);
	}
	
	@GetMapping("/memo/download_annex/{mid}")
	public void memoAnnexDownload(HttpServletResponse response, @PathVariable int mid) throws IOException{
		fileDownloadService.memoAnnexDownload(response, mid);
	}
	
	
	@PutMapping("/updatememo")
	public Memo updateDecision(@RequestBody Memo memo) throws UnsupportedEncodingException, MessagingException {
		return service.updateDecision(memo);
	}

	@GetMapping("/getMemobyfacid/{facid}")
	public List<Memo> getMemoByFacMeeting(@PathVariable int facid){
		return service.getMemoByFacMeeting(facid);
	}
	
	@GetMapping("/getallnewmemoAR")
	public List<Memo> getAllNewMemo() {
		return service.getAllNewMemo();
	}
	
	@GetMapping("/getallnewmemoDean")
	public List<Memo> getAllNewMemoDean() {
		return service.getAllNewMemoDean();
	}
	
	@PutMapping("/updateDean")
	public Memo updateDean(@ModelAttribute Memo memo) {
		return service.updateDean(memo);
	}
	@PutMapping("/updateAR")
	public Memo updateAR(@ModelAttribute Memo memo) {
		return service.updateAR(memo);
	}
	
	
}
