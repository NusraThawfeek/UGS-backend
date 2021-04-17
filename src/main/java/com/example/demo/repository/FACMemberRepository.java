package com.example.demo.repository;


<<<<<<< HEAD
import java.util.Optional;
=======
import java.util.List;
>>>>>>> 8ff7ad82f2521e5cef821f951247516812963dfb

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.example.demo.entity.FACMember;
import com.example.demo.entity.User;



public interface FACMemberRepository extends JpaRepository<FACMember, Long> {
	FACMember getByuserId(Long userId);

<<<<<<< HEAD
	FACMember findByIsDean(boolean IsDean); 
	FACMember findByIsDugs(boolean IsDugs);  
	Optional<FACMember> findByuserId(FACMember userId);
	
	@Query(value ="select u from FACMember f, User u"
			+ " where f.userId=u.userId and f.department=:dep and f.isHod=:ishod")
	FACMember findByDepAndIshod(@Param("dep") String dep,@Param("ishod") boolean isHod);

=======
	List<FACMember> findAll();
>>>>>>> 8ff7ad82f2521e5cef821f951247516812963dfb
}
