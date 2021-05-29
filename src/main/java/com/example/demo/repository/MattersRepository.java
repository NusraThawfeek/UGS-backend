package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Attend;
import com.example.demo.entity.FACMeeting;
import com.example.demo.entity.FACMember;
import com.example.demo.entity.Matters;
import com.example.demo.entity.Priliminary;

public interface MattersRepository extends JpaRepository<Matters, Integer> {
	List<Matters> getByfacmeeting_id(Integer id);
	
	List<Matters> getByfacmeeting_idAndAgenda(Integer id,Boolean agenda);
	List<Matters> getByfacmeeting_idAndMinute(Integer id,Boolean minute);
	
	@Query(value = "select * from matters m where m.meeting_id=?1 and m.agenda=true and m.decission is null", nativeQuery = true)
	List<Matters> findAgendaWithoutDecision(Integer id);
}
