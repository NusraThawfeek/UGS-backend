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
	
	@Query(value = "select s  from student s WHERE s.batch_year=:batch", nativeQuery = true)
	List<Student> findAllStudentByBatchYear(@Param("batch")String batch);
	
	@Query(value = "select s.index_no  from student s WHERE s.batch_year=:batch", nativeQuery = true)
	List<String> findAllIndexByBatchYear(@Param("batch")String batch);
	
	@Query(value = "SELECT DISTINCT TOP 6 s.batch_year"
			+ "FROM  student s"
			+ "ORDER BY s.batch_year", nativeQuery = true)
	List<String> findRecentBatch();
	
}
