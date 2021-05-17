package com.example.demo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.entity.AssistentRegistrar;
import com.example.demo.entity.FACMember;
import com.example.demo.entity.MRoles;
import com.example.demo.entity.Roles;
import com.example.demo.entity.UgsStaff;
import com.example.demo.repository.AssistentRegistrarRepository;
import com.example.demo.repository.FACMemberRepository;
import com.example.demo.repository.RolesRepository;
import com.example.demo.repository.UgsStaffRepository;

@SpringBootApplication
@EnableScheduling
public class UgsApplication implements CommandLineRunner {
	@Autowired
	private UgsStaffRepository repo;
	
	@Autowired
	private FACMemberRepository frepo;
	
	
	@Autowired
	private RolesRepository roleRepo;
	
	@Autowired

	private AssistentRegistrarRepository arRepo;


	
	public static void main(String[] args) {
		SpringApplication.run(UgsApplication.class, args);
	}
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@Override
	public void run(String... args) throws Exception {

//		System.out.println(encoder.encode("12345678"));

//		Roles student = new Roles(MRoles.ROLE_STUDENT);
//		Roles fac_member = new Roles(MRoles.ROLE_FAC_MEMBER);
//		Roles ugsRole = new Roles(MRoles.ROLE_UGS);
//		Roles ar1 = new Roles(MRoles.ROLE_AR);
//		Roles hod_IT = new Roles(MRoles.ROLE_HOD_IT);
//		Roles dean = new Roles(MRoles.ROLE_DEAN);
//		Roles dugs = new Roles(MRoles.ROLE_DUGS);
//
//
//		roleRepo.save(ar1);
//		roleRepo.save(ugsRole);
//		roleRepo.save(student);
//		roleRepo.save(fac_member);
//		roleRepo.save(hod_IT);
//		roleRepo.save(dean);
//		roleRepo.save(dugs);
//		
//		
////		Insert  5 FAC MEMBERS temporarily
//// FAC NO:1
//		FACMember fac1 = new FACMember();
//		fac1.setTitle("Mr");
//		fac1.setFirstName("B.H");
//		fac1.setLastName("Sudantha");
//		fac1.setNameToBeAppeared("MR. SUDANTHA B.H.");
//		fac1.setEmail("184160D@gmail.com");
//		fac1.setContactNo("0758989898");
//		fac1.setPassword("$2a$10$921Uz2.ZYthANXACWAr6MO6CX32XiTrXedlKVyH416fwGF0yM5Cnm");
//		fac1.setDepartment("IT");
//		fac1.setAcademicAdvisor(true);
//		Set<Roles> rolesFac1 = new HashSet<Roles>();
//		Roles roleAcaAd = roleRepo.findByName(MRoles.ROLE_FAC_MEMBER).get();
//		rolesFac1.add(roleAcaAd);
//		fac1.setRoles(rolesFac1);
//		fac1.setAcademicAdvisor(true);
//		fac1.setLectureGradeLevel("SENIOR LECTURER GRADE 1");
//		frepo.save(fac1);
////		
//////FAC NO:		2
//		FACMember fac2 = new FACMember();
//
//		fac2.setTitle("Mr");
//		fac2.setFirstName("S.C");
//		fac2.setLastName(" Premaratne");
//		fac2.setNameToBeAppeared( "Mr. S.C. Premaratne");
//		fac2.setEmail("184161D@gmail.com");
//		fac2.setContactNo("0758989898");
//		fac2.setPassword("$2a$10$921Uz2.ZYthANXACWAr6MO6CX32XiTrXedlKVyH416fwGF0yM5Cnm");
//		fac2.setDepartment("IT");
//		fac2.setAcademicAdvisor(true);
//		Set<Roles> rolesFac2 = new HashSet<Roles>();
//		rolesFac2.add(roleAcaAd);
//		fac2.setRoles(rolesFac2);
//		fac2.setAcademicAdvisor(true);
//		fac2.setLectureGradeLevel("SENIOR LECTURER GRADE 1");
//		frepo.save(fac2);
//
//
//		// FAC NO: 3 
//		FACMember fac3 = new FACMember(); 
//		fac3.setTitle("Mr");
//		fac3.setFirstName("M.F.M.");
//		fac3.setLastName("FIRDHOUS");
//		fac3.setNameToBeAppeared("DR. FIRDHOUS M.F.M.");
//		fac3.setEmail("184162D@gmail.com");
//		fac3.setContactNo("0758989898");
//		fac3.setPassword("$2a$10$921Uz2.ZYthANXACWAr6MO6CX32XiTrXedlKVyH416fwGF0yM5Cnm");
//		fac3.setDepartment("IT");
//		fac3.setAcademicAdvisor(true);
//		Set<Roles> rolesFac3 = new HashSet<Roles>();
//		rolesFac3.add(roleAcaAd);
//		fac3.setRoles(rolesFac3);
//		fac3.setAcademicAdvisor(true);
//		fac3.setLectureGradeLevel("SENIOR LECTURER GRADE 1");
//		frepo.save(fac3);
//
//		// FAC NO: 4 HOD
//		FACMember fac4 = new FACMember(); 
//		fac4.setTitle("Mr");
//		fac4.setFirstName("M.Z.M.");
//		fac4.setLastName("SUPUN");
//		fac4.setNameToBeAppeared("DR. SUPUN M.Z.M.");
//		fac4.setEmail("184163D@gmail.com");
//		fac4.setContactNo("0758989898");
//		fac4.setPassword("$2a$10$921Uz2.ZYthANXACWAr6MO6CX32XiTrXedlKVyH416fwGF0yM5Cnm");
//		fac4.setDepartment("IT");
//		fac4.setHod(true);
//		Set<Roles> rolesFac4 = new HashSet<Roles>();
//		Roles roleHOD = roleRepo.findByName(MRoles.ROLE_HOD_IT).get();
//		rolesFac4.add(roleAcaAd);
//		rolesFac4.add(roleHOD);
//		fac4.setRoles(rolesFac4);
//		fac4.setHod(true);
//		fac4.setLectureGradeLevel("SENIOR LECTURER GRADE 1");
//		frepo.save(fac4);
//		
//		// FAC NO:5 DEAN
//		FACMember fac5 = new FACMember(); 
//		fac5.setTitle("Mr");
//		fac5.setFirstName("M.L.M.");
//		fac5.setLastName("THARAKA");
//		fac5.setNameToBeAppeared("DR. THARAKA M.L.M.");
//		fac5.setEmail("184164D@gmail.com");
//		fac5.setContactNo("0758989898");
//		fac5.setPassword("$2a$10$921Uz2.ZYthANXACWAr6MO6CX32XiTrXedlKVyH416fwGF0yM5Cnm");
//		fac5.setDepartment("IT");
//		fac5.setDean(true);
//		Set<Roles> rolesFac5 = new HashSet<Roles>();
//		Roles roleDEAN = roleRepo.findByName(MRoles.ROLE_DEAN).get();
//		rolesFac5.add(roleAcaAd);
//		rolesFac5.add(roleDEAN);
//		fac5.setRoles(rolesFac5);
//		fac5.setDean(true);
//		fac5.setLectureGradeLevel("SENIOR LECTURER GRADE 1");
//		frepo.save(fac5);
		
//		// FAC NO:6 DUGS
//		FACMember fac6 = new FACMember(); 
//		fac6.setTitle("Mrs");
//		fac6.setFirstName("M.G.L.K.");
//		fac6.setLastName("DHEVI");
//		fac6.setNameToBeAppeared("DR. DHEVI M.G.L.K.");
//		fac6.setEmail("184165D@gmail.com");
//		fac6.setContactNo("0758989898");
//		fac6.setPassword("$2a$10$921Uz2.ZYthANXACWAr6MO6CX32XiTrXedlKVyH416fwGF0yM5Cnm");
//		fac6.setDepartment("IT");
//		fac6.setHod(true);
//		Set<Roles> rolesFac6 = new HashSet<Roles>();
//		Roles roleDUGS = roleRepo.findByName(MRoles.ROLE_DUGS).get();
//		rolesFac6.add(roleDUGS);
//		rolesFac6.add(roleAcaAd);
//		fac6.setRoles(rolesFac6);
//		fac6.setDugs(true);
//		fac6.setLectureGradeLevel("SENIOR LECTURER GRADE 1");
//		frepo.save(fac6);	
//		
		
//		
//		UgsStaff ugs = new UgsStaff();
//		ugs.setFirstName("Malith");
//		ugs.setLastName("fernando");
//		ugs.setNameToBeAppeared("M.Fernando");
//		ugs.setEmail("fayaz@gmail.com");
//		ugs.setContactNo("778366330");
//		ugs.setPassword("$2a$10$921Uz2.ZYthANXACWAr6MO6CX32XiTrXedlKVyH416fwGF0yM5Cnm");
//
//		Set<Roles> roles = new HashSet<Roles>();
//		Roles roleUgs = roleRepo.findByName(MRoles.ROLE_UGS).get();
//
//		roles.add(roleUgs);
//
//		ugs.setRoles(roles);
//
//		repo.save(ugs);
//		
//		AssistentRegistrar ar = new AssistentRegistrar();
//		ar.setFirstName("Sarath");
//		ar.setLastName("fernando");
//		ar.setNameToBeAppeared("S.Fernando");
//		ar.setEmail("sarath@gmail.com");
//		ar.setContactNo("778366330");
//		ar.setPassword("$2a$10$921Uz2.ZYthANXACWAr6MO6CX32XiTrXedlKVyH416fwGF0yM5Cnm");
//
//		Set<Roles> rolesAR = new HashSet<Roles>();
//		Roles roleAR = roleRepo.findByName(MRoles.ROLE_AR).get();
//
//		rolesAR.add(roleAR);
//
//		ar.setRoles(rolesAR);
//
//		arRepo.save(ar);	
//
//		List<FACMember> allAcademicAdvisors = frepo.findAllByIsAcademicAdvisor(true);



//		for(FACMember acad: allAcademicAdvisors) {
//			System.out.println(acad);
//		}
		
		
	}

}
