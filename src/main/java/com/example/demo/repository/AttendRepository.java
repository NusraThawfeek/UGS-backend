package com.example.demo.repository;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Attend;

public interface AttendRepository extends JpaRepository<Attend, Integer> {
	List<Attend> getByfacmeeting_id(Integer id);

	List<Attend> getByfacmeeting_date(Date date);
}
