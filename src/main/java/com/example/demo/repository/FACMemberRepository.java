package com.example.demo.repository;


import java.util.Optional;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.example.demo.entity.FACMember;



public interface FACMemberRepository extends JpaRepository<FACMember, Long> {
	FACMember getByuserId(Long userId);
//	@Query(value = "SELECT * FROM `fac_member` WHERE is_academic_advisor=1", nativeQuery = true )
	List<FACMember> findAllByIsAcademicAdvisor(Boolean value);
	


	FACMember findByIsDean(boolean IsDean); 
	FACMember findByIsDugs(boolean IsDugs);  
	Optional<FACMember> findByuserId(FACMember userId);
	
	@Query(value ="select u from FACMember f, User u"
			+ " where f.userId=u.userId and f.department=:dep and f.isHod=:ishod")
	FACMember findByDepAndIshod(@Param("dep") String dep,@Param("ishod") boolean isHod);

	List<FACMember> findAll();
}

