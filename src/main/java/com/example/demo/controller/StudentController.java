package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

@RestController
@CrossOrigin(origins = "*")
public class StudentController {

	@Autowired
	private StudentService service;

	@PostMapping("/regstudent")
	public Student regStudent(@RequestBody Student std) {
		return service.regStudent(std);
	}

	@GetMapping("/Student")
	public List<Student> getStudent() {

		return service.getStudent();
	}

	@GetMapping("/StudentByIndexNo/{indexNo}")
	public Optional<Student> findByindexNo(@PathVariable String indexNo) {
		return service.findByindexNo(indexNo);
	}

}
