package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Priliminary;


public interface PriliminaryRepository extends JpaRepository<Priliminary, Integer> {
List<Priliminary> getByfacmeeting_id(Integer id);
}
