package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Matters;

public interface MattersRepository extends JpaRepository<Matters, Integer> {
	List<Matters> getByfacmeeting_id(Integer id);
}
