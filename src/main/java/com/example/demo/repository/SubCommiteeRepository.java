package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.SubComittee;

@Repository
public interface SubCommiteeRepository extends JpaRepository<SubComittee, Long> {
    SubComittee getById(Long id);
}
