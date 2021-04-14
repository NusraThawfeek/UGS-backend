package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;



@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repository;
	
	public Student regStudent(Student std) {
		return repository.save(std);
	}
	
	public Student getStudent(long userId) {
		return repository.findById(userId).orElse(null);
	}
}
