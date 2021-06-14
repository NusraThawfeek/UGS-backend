package com.example.demo.repository;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Attend;

public interface AttendRepository extends JpaRepository<Attend, Integer> {
	Attend getByfacmeeting_idAndFacmember_userId(Integer id,Long userId); //unique key

	List<Attend> getByfacmeeting_id(Integer id);

	List<Attend> getByfacmeeting_date(Date date);
	
	List<Attend> getByfacmeeting_idAndAttendence(Integer facId,String attendence);
	
	List<Attend> getByfacmeeting_idAndApologyIsNotNull(Integer facId);
}
