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


//	System.out.println(encoder.encode("12345678"));

//		Roles student = new Roles(MRoles.ROLE_STUDENT);
//		Roles fac_member = new Roles(MRoles.ROLE_FAC_MEMBER);
//		Roles ugsRole = new Roles(MRoles.ROLE_UGS);
//		Roles ar1 = new Roles(MRoles.ROLE_AR);
//		Roles dean = new Roles(MRoles.ROLE_DEAN);
//		Roles dugs = new Roles(MRoles.ROLE_DUGS);
//		Roles hod = new Roles(MRoles.ROLE_HOD);
//		Roles acadamicAdvisor = new Roles(MRoles.ROLE_ACADEMICADVISOR);
//
//		roleRepo.save(ar1);
//		roleRepo.save(ugsRole);
//		roleRepo.save(student);
//		roleRepo.save(fac_member);
//		roleRepo.save(dean);
//		roleRepo.save(dugs);
//		roleRepo.save(hod);
//		roleRepo.save(acadamicAdvisor);
//
//		Roles roleFacMember = roleRepo.findByName(MRoles.ROLE_FAC_MEMBER).get();
//		Roles roleAcaAd = roleRepo.findByName(MRoles.ROLE_ACADEMICADVISOR).get();
//		Roles roleHOD = roleRepo.findByName(MRoles.ROLE_HOD).get();
//		Roles roleDEAN = roleRepo.findByName(MRoles.ROLE_DEAN).get();
//		Roles roleDUGS = roleRepo.findByName(MRoles.ROLE_DUGS).get();
//		Roles roleAR = roleRepo.findByName(MRoles.ROLE_AR).get();
//		Roles roleUgs = roleRepo.findByName(MRoles.ROLE_UGS).get();
//
//
//		// Insert  5 FAC MEMBERS temporarily
//		// FAC NO:1
//		FACMember fac1 = new FACMember();
//		fac1.setTitle("Mr");
//		fac1.setFirstName("B.H");
//		fac1.setLastName("Amila");
//		fac1.setNameToBeAppeared("MR. Amila B.H.");
//		fac1.setEmail("184160D@gmail.com");
//		fac1.setContactNo("0758989898");
//		fac1.setPassword("$2a$10$921Uz2.ZYthANXACWAr6MO6CX32XiTrXedlKVyH416fwGF0yM5Cnm");
//		fac1.setDepartment("IT");
//		fac1.setAcademicAdvisor(true);
//		Set<Roles> rolesFac1 = new HashSet<Roles>();
//		rolesFac1.add(roleAcaAd);
//		rolesFac1.add(roleFacMember);
//		fac1.setRoles(rolesFac1);
//		fac1.setAcademicAdvisor(true);
//		fac1.setLectureGradeLevel("SENIOR LECTURER GRADE 1");
//		frepo.save(fac1);
//
//		//FAC NO: 2
//		FACMember fac2 = new FACMember();
//		fac2.setTitle("Mr");
//		fac2.setFirstName("S.C");
//		fac2.setLastName("Prem");
//		fac2.setNameToBeAppeared( "Mr. S.C. Prem");
//		fac2.setEmail("184161D@gmail.com");
//		fac2.setContactNo("0758989898");
//		fac2.setPassword("$2a$10$921Uz2.ZYthANXACWAr6MO6CX32XiTrXedlKVyH416fwGF0yM5Cnm");
//		fac2.setDepartment("IT");
//		fac2.setAcademicAdvisor(true);
//		Set<Roles> rolesFac2 = new HashSet<Roles>();
//		rolesFac2.add(roleAcaAd);
//		rolesFac1.add(roleFacMember);
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
//		fac3.setLastName("Mohamed");
//		fac3.setNameToBeAppeared("DR. Mohamed M.F.M.");
//		fac3.setEmail("184162D@gmail.com");
//		fac3.setContactNo("0758989898");
//		fac3.setPassword("$2a$10$921Uz2.ZYthANXACWAr6MO6CX32XiTrXedlKVyH416fwGF0yM5Cnm");
//		fac3.setDepartment("IT");
//		fac3.setOnlyLecturer(true);
//		Set<Roles> rolesFac3 = new HashSet<Roles>();
//		rolesFac3.add(roleFacMember);
//		fac3.setRoles(rolesFac3);
//		fac3.setLectureGradeLevel("SENIOR LECTURER GRADE 1");
//		frepo.save(fac3);
//
//			//	FAC NO: 4 HOD
//		FACMember fac4 = new FACMember();
//		fac4.setTitle("Mr");
//		fac4.setFirstName("M.Z.M.");
//		fac4.setLastName("Sathyan");
//		fac4.setNameToBeAppeared("DR. Sathyan M.Z.M.");
//		fac4.setEmail("184163D@gmail.com");
//		fac4.setContactNo("0758989898");
//		fac4.setPassword("$2a$10$921Uz2.ZYthANXACWAr6MO6CX32XiTrXedlKVyH416fwGF0yM5Cnm");
//		fac4.setDepartment("IT");
//		fac4.setHod(true);
//		Set<Roles> rolesFac4 = new HashSet<Roles>();
//		rolesFac4.add(roleHOD);
//		rolesFac1.add(roleFacMember);
//		fac4.setRoles(rolesFac4);
//		fac4.setLectureGradeLevel("SENIOR LECTURER GRADE 1");
//		frepo.save(fac4);
//
//		//FAC NO:5 DEAN
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
//		rolesFac5.add(roleDEAN);
//		rolesFac1.add(roleFacMember);
//		fac5.setRoles(rolesFac5);
//		fac5.setLectureGradeLevel("SENIOR LECTURER GRADE 1");
//		frepo.save(fac5);
//
//
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
//		fac6.setDugs(true);
//		rolesFac1.add(roleFacMember);
//		Set<Roles> rolesFac6 = new HashSet<Roles>();
//		rolesFac6.add(roleDUGS);
//		fac6.setRoles(rolesFac6);
//		fac6.setLectureGradeLevel("SENIOR LECTURER GRADE 1");
//		frepo.save(fac6);
//
//		// UGS staff
//
//		UgsStaff ugs = new UgsStaff();
//		ugs.setFirstName("Malith");
//		ugs.setLastName("fernando");
//		ugs.setNameToBeAppeared("M.Fernando");
//		ugs.setEmail("malith@gmail.com");
//		ugs.setContactNo("778366330");
//		ugs.setPassword("$2a$10$921Uz2.ZYthANXACWAr6MO6CX32XiTrXedlKVyH416fwGF0yM5Cnm");
//		Set<Roles> roles = new HashSet<Roles>();
//		roles.add(roleUgs);
//		ugs.setRoles(roles);
//		repo.save(ugs);
//
//		// AR
//		AssistentRegistrar ar = new AssistentRegistrar();
//		ar.setFirstName("Sarath");
//		ar.setLastName("fernando");
//		ar.setNameToBeAppeared("Mr S.Fernando");
//		ar.setEmail("sarath@gmail.com");
//		ar.setContactNo("778366330");
//		ar.setPassword("$2a$10$921Uz2.ZYthANXACWAr6MO6CX32XiTrXedlKVyH416fwGF0yM5Cnm");
//		Set<Roles> rolesAR = new HashSet<Roles>();
//		rolesAR.add(roleAR);
//		ar.setRoles(rolesAR);
//		arRepo.save(ar);



    }

}