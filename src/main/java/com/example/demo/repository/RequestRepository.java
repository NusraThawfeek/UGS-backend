package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.FACMeeting;
import com.example.demo.entity.FACMember;
import com.example.demo.entity.Request;
import com.example.demo.entity.Student;

public interface RequestRepository extends JpaRepository<Request, Integer> {

	List<Request> findByStd(Student std);
	
	
	@Query(value = "select r.rid, r.type,r.entered_date,r.annex_path,r.is_send_to_fac_board,r.decision,r.std_user_id,r.status,\r\n"
			+ "		r.fac_meeting1_id,0 AS clazz_ from request r, student s\r\n"
			+ "	    where r.std_user_id=s.user_id and s.index_no=:indexNo", nativeQuery = true)
	List<Request> findByindexNo(@Param("indexNo")String indexNo);
	
	
	//passed req
		@Query(value = "SELECT *,0 AS clazz_ \r\n"
				+ "		FROM  commented c,request r\r\n"
				+ "		WHERE  r.rid=c.rid and c.fac_id=:uid order by r.entered_date desc"
				, nativeQuery = true)
		List<Request> findByuid(@Param("uid") FACMember uid);
		
		
		//new req for academic adv
		@Query(value = " SELECT distinct(r.rid),r.status,r.entered_date,r.is_send_to_fac_board,r.annex_path,r.type,r.std_user_id,r.fac_meeting1_id,"
				+ "r.decision,0 AS clazz_\r\n"
				+ "		FROM request r\r\n"
				+ "		WHERE  r.rid not in(SELECT r.rid\r\n"
				+ "		FROM request r, commented c\r\n"
				+ "		WHERE  r.rid=c.rid and c.fac_id=:uid)\r\n"
				+ "order by entered_date desc ", nativeQuery = true)
		List<Request> findByuidnew(@Param("uid") FACMember uid);
		
		
		//new req for hod
		@Query(value = "    select *,0 AS clazz_ from request\r\n"
				+ "		where rid in (\r\n"
				+ "		select c.rid from commented c, facmember f\r\n"
				+ "		where c.fac_id=f.user_id \r\n"
				+ "		and is_forwarded=true\r\n"
				+ "		and is_academic_advisor=true \r\n"
				+ "		and department=(select department from facmember where user_id=:uid))\r\n"
				+ "		 and rid not in (SELECT r.rid FROM request r, commented c WHERE  r.rid=c.rid and c.fac_id=:uid)\r\n"
				+ "		order by entered_date desc ", nativeQuery = true)
		List<Request> findnewRequestForHod(@Param("uid") FACMember uid);
		
		
		//new req for dean
		@Query(value = " select s.rid,s.type,s.annex_path,s.status,s.is_send_to_fac_board,s.entered_date,s.std_user_id,s.decision,s.fac_meeting1_id"
				+ "		,0 AS clazz_\r\n"
				+ "		from request s, commented c, facmember f\r\n"
				+ "		where s.rid= c.rid and c.fac_id=f.user_id and is_hod=true and is_forwarded=true and s.rid not in \r\n"
				+ "		(SELECT r.rid FROM request r, commented c WHERE  r.rid=c.rid and c.fac_id=:uid)\r\n"
				+ "		order by s.entered_date desc", nativeQuery = true)
		List<Request> findnewRequestForDean(@Param("uid") FACMember uid);
		
		//new req for dugs
		@Query(value = "select s.rid,s.type,s.annex_path,s.status,s.is_send_to_fac_board,s.entered_date,s.decision,s.fac_meeting1_id,s.std_user_id,0 AS clazz_\r\n"
				+ "		from request s, commented c, facmember f\r\n"
				+ "		where s.rid= c.rid and c.fac_id=f.user_id and is_dean=true and is_forwarded=true and s.rid not in \r\n"
				+ "		(SELECT r.rid FROM request r, commented c WHERE  r.rid=c.rid and c.fac_id=:uid) order by s.entered_date desc", nativeQuery = true)
		List<Request> findnewRequestForDUGS(@Param("uid") FACMember uid);
		
		
		//past req for AR
		@Query(value = "select distinct(r.rid),r.annex_path,r.decision,r.entered_date,r.fac_meeting1_id,r.is_send_to_fac_board,"
				+ "r.status,r.std_user_id,r.type,0 AS clazz_\r\n"
				+ "from commented c, request r,facmember f\r\n"
				+ "where c.rid=r.rid and c.fac_id=f.user_id and r.is_send_to_fac_board=true\r\n"
				+ "order by r.entered_date desc", nativeQuery = true)
		List<Request> findPastRequestForAR();
	
		//New req for AR
		@Query(value = "select distinct(r.rid),r.annex_path,r.decision,r.entered_date,r.fac_meeting1_id,r.is_send_to_fac_board,"
				+ "r.status,r.std_user_id,r.type,0 AS clazz_\r\n"
				+ "from commented c, request r,facmember f\r\n"
				+ "where c.rid=r.rid and c.fac_id=f.user_id and c.is_forwarded=true and f.is_dugs=true and r.rid not in\r\n"
				+ "(select distinct(r.rid) \r\n"
				+ "from commented c, request r,facmember f\r\n"
				+ "where c.rid=r.rid and c.fac_id=f.user_id and r.is_send_to_fac_board=true)  order by r.entered_date desc", nativeQuery = true)
		List<Request> findNewRequestForAR();
		
		
		List<Request> findByFacMeeting1(FACMeeting facid);
		
		
}
