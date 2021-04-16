package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AssistentRegistrar;
import com.example.demo.entity.CommentKey;
import com.example.demo.entity.Commented;
import com.example.demo.entity.FACMember;
import com.example.demo.entity.Request;

public interface CommentedRepository extends JpaRepository<Commented, CommentKey> {
	
	List<Commented> findByrid(Request rid);
	List<Commented> findByuid(FACMember uid);
	Optional<Commented> findByRidAndUid(Request rid, FACMember uid);
	//Optional<Commented> findARByRidAndUid(Request rid, AssistentRegistrar uid);
}
