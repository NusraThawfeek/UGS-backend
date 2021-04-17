package com.example.demo.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.example.demo.entity.FACMember;

public interface FACMemberRepository extends JpaRepository<FACMember, Long> {

	FACMember findByIsDean(boolean IsDean); 
	FACMember findByIsDugs(boolean IsDugs);  
	Optional<FACMember> findByuserId(FACMember userId);
	
	@Query(value ="select u from FACMember f, User u"
			+ " where f.userId=u.userId and f.department=:dep and f.isHod=:ishod")
	FACMember findByDepAndIshod(@Param("dep") String dep,@Param("ishod") boolean isHod);

}
