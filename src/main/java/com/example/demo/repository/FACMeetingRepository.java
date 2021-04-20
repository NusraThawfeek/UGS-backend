package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.FACMeeting;



public interface FACMeetingRepository extends JpaRepository<FACMeeting, Integer> {
	FACMeeting getById(Integer Id);

	List<FACMeeting> findAll();

	boolean existsById(Integer integer);

	@Query(value = "select * from fac_meeting fc where fc.id=?1", nativeQuery = true)
	FACMeeting getByMeetingIDS(Integer id);

	FACMeeting findFirstByOrderByIdDesc();
}
