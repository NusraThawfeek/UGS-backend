package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Attend;
import com.example.demo.entity.Matters;
import com.example.demo.entity.Priliminary;

public interface MattersRepository extends JpaRepository<Matters, Integer> {
	List<Matters> getByfacmeeting_id(Integer id);
	
	List<Matters> getByfacmeeting_idAndAgenda(Integer id,Boolean agenda);
	List<Matters> getByfacmeeting_idAndMinute(Integer id,Boolean minute);
}
