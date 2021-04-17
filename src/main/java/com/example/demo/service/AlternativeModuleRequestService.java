package com.example.demo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.AlternativeModuleRequest;
import com.example.demo.entity.FACMember;
import com.example.demo.entity.LeaveRequest;
import com.example.demo.entity.ModuleDiscontinue;
import com.example.demo.entity.ModuleDiscontinueKey;
import com.example.demo.entity.Student;
import com.example.demo.exception.FileStorageException;
import com.example.demo.repository.AlternativeModuleRequestRepository;
import com.example.demo.repository.ModuleDiscontinueRepository;


@Service
public class AlternativeModuleRequestService {

	@Autowired
	private AlternativeModuleRequestRepository alterRepo;
	
	@Autowired
	private ModuleDiscontinueRepository moduleDiscontinueRepo;
	
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private StudentService studentService;
	
	private AlternativeModuleRequest alterRequest;
	
	@Autowired
	private DecisionMailService mailService;
	
	public void submitAlternativemoduleRequest(int sid, MultipartFile annex, String mcode[], String currentGrade[], String comment) throws MessagingException {
		
		String fileName = StringUtils.cleanPath(annex.getOriginalFilename());

        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            
            Date dNow = new Date();
	        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
	        String datetime = ft.format(dNow);
            
            File convertFile = new File("E:\\Spring Boot\\ugs\\src\\main\\resources\\static\\Upload\\annex\\"+"FAC-" + datetime + annex.getOriginalFilename());
    		convertFile.createNewFile();
    		FileOutputStream fout = new FileOutputStream(convertFile);
    		fout.write(annex.getBytes());
    		fout.close();
    		
    		String filePath = "E:\\Spring Boot\\ugs\\src\\main\\resources\\static\\Upload\\annex\\"+ "FAC-" + datetime + annex.getOriginalFilename();
    		
    		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    		Date enteredDate = new Date();
    		
    		AlternativeModuleRequest req = new AlternativeModuleRequest(filePath, "", enteredDate, false, studentService.getStudent(sid), comment, "Alternative");
    		alterRepo.save(req);
    		
    		List<ModuleDiscontinue> moduleDiscontinueList = new ArrayList();
    		
    		for(int i = 0; i < mcode.length; i++) {
    			ModuleDiscontinue moduleDiscontinue = new ModuleDiscontinue(req, moduleService.getModuleById(mcode[i]), "", "", currentGrade[i]);
    			
    			moduleDiscontinueList.add(moduleDiscontinue);
    		}
    		
    		moduleDiscontinueRepo.saveAll(moduleDiscontinueList);
    		
    		Student std=studentService.getStudent(sid);
    		
    		mailService.newRequest(std.getAcademicAdvisor(),"AcademicAdvisor", std);
    		
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
	}
	
	public AlternativeModuleRequest getModuleDiscontinue(int rid) {
		
		return alterRepo.findById(rid).orElse(null);
	}
	
	public AlternativeModuleRequest getAlterRequest(int rid) {
		return alterRepo.findById(rid).orElse(null);
	}
	
	public List<AlternativeModuleRequest> getAllRequest(){
		return alterRepo.findAll();
	}
	
	public List<AlternativeModuleRequest> pastAlternativeModuleRequest(int sid) {
		
		return alterRepo.findByStd(studentService.getStudent(sid));
	} 
	
	public String updateRequest(int rid, String status, String decision, String alterMcode[], String offeringSem[]) throws UnsupportedEncodingException, MessagingException {
		
		AlternativeModuleRequest existingAlterRequest = getAlterRequest(rid); 
		
		existingAlterRequest.setStatus(status);
		existingAlterRequest.setDecision(decision);
		
		List<ModuleDiscontinue> mdc = existingAlterRequest.getModuleDiscontinue();
		
		for(int i = 0; i < alterMcode.length; i++) {
			
			mdc.get(i).setAlterMcode(alterMcode[i]);
			mdc.get(i).setOfferingSem(offeringSem[i]);
		}
		
		alterRepo.save(existingAlterRequest);
		
		mailService.decsisionEmail(existingAlterRequest, "http://localhost:3000");
		
		return "Successfully updated";
	}
}
