package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	Optional<Student> findByIndexNo(String indexNo);
	Boolean existsByIndexNo(String indexNo);
	@Query(value = "select s.index	", nativeQuery = true)
	List<Student> findAllByBatchYear(@Param("batch")String batch);
	
}
