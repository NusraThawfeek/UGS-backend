package com.example.demo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LateModuleChangeRequest;
import com.example.demo.entity.OtherAppeal;
import com.example.demo.entity.Request;

@Service
public class FileDownloadService {
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private LateModuleChangeRequestService lateModuleChangeRequestService;
	
	@Autowired
	private OtherAppealService otherAppealService;

	public void AnnexDownload(HttpServletResponse response, int rid) throws IOException {
		
		Request request = requestService.getRequest(rid);
		
		String ext1 = FilenameUtils.getExtension(request.getAnnexPath());
		String fileName = "request." + ext1;
		response.setHeader("Content-Disposition", "attachment; filename="+ fileName);
		
		
		InputStream inputStream = new FileInputStream(new File(request.getAnnexPath()));
		int nRead;
		while ((nRead = inputStream.read()) != -1) {
			response.getWriter().write(nRead);
		}
	}
	
	public void sgpadownloadLate(HttpServletResponse response, int rid) throws IOException {
		
		LateModuleChangeRequest request = lateModuleChangeRequestService.getRequest(rid);
		
		String ext1 = FilenameUtils.getExtension(request.getAnnexPath());
		String fileName = "SGPA." + ext1;
		response.setHeader("Content-Disposition", "attachment; filename="+ fileName);
		
		InputStream inputStream = new FileInputStream(new File(request.getScanCopyOfSgpaPath()));
		int nRead;
		while ((nRead = inputStream.read()) != -1) {
			response.getWriter().write(nRead);
		}
	}
	
	public void sgpadownloadAppeal(HttpServletResponse response, int rid) throws IOException {
		
		OtherAppeal request = otherAppealService.getRequest(rid);
		
		String ext1 = FilenameUtils.getExtension(request.getAnnexPath());
		String fileName = "SGPA." + ext1;
		response.setHeader("Content-Disposition", "attachment; filename="+ fileName);
		
		InputStream inputStream = new FileInputStream(new File(request.getScanCopyOfSGPAPath()));
		int nRead;
		while ((nRead = inputStream.read()) != -1) {
			response.getWriter().write(nRead);
		}
	}
}