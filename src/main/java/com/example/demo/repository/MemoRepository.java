package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.FACMeeting;
import com.example.demo.entity.FACMember;
import com.example.demo.entity.Memo;
import com.example.demo.entity.Request;

public interface MemoRepository extends JpaRepository<Memo, Integer> {

	List<Memo> findByFacMember(FACMember facMember);
	List<Memo> findByfacMeeting(FACMeeting facid);
	@Query(value = "select * from memo r where r.fac_meeting_id=?1 and r.decision is not null", nativeQuery = true)
	List<Memo> findMemoWithDecision(Integer id);
}
