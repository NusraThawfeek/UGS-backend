package com.example.demo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.demo.entity.AssistentRegistrar;
import com.example.demo.entity.MRoles;
import com.example.demo.entity.Roles;
import com.example.demo.entity.Student;
import com.example.demo.entity.UgsStaff;
import com.example.demo.repository.AssistentRegistrarRepository;
import com.example.demo.repository.RolesRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UgsStaffRepository;

@SpringBootApplication
@EnableScheduling
public class UgsApplication implements CommandLineRunner{
	@Autowired
	private UgsStaffRepository repo;
	
	@Autowired
	private RolesRepository roleRepo;
	
	@Autowired
	private StudentRepository stdRepo;
	
	@Autowired
	private AssistentRegistrarRepository assistentRegistrarRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(UgsApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
//		System.out.println(encoder.encode("12345"));
//		Roles studentRole = new Roles(MRoles.ROLE_STUDENT);
//		Roles admin = new Roles(MRoles.ROLE_ADMIN);
//		Roles ugsRole = new Roles(MRoles.ROLE_UGS);
//		Roles dugs = new Roles(MRoles.ROLE_DUGS);
//		Roles dean = new Roles(MRoles.ROLE_DEAN);
//		Roles hod = new Roles(MRoles.ROLE_HOD);
//		Roles ar = new Roles(MRoles.ROLE_AR);
//		
//		roleRepo.save(admin);
//		roleRepo.save(ugsRole);
//		roleRepo.save(studentRole);
//		roleRepo.save(dugs);
//		roleRepo.save(dean);
//		roleRepo.save(hod);
//		roleRepo.save(ar);
//		
//		Student student = new Student();
//		student.setFirstName("Malith");
//		student.setLastName("fernando");
//		student.setEmail("mhdrazan7@gmail.com");
//		student.setContactNo("778366330");
//		student.setPassword("$2a$10$NuUxIue/pmtKrU1t/MAo/em4cgD8UDsOh09xVgqmBAxOGzWtFc31O");
//		student.setIndexNo("184142R");
//		student.setBatchYear("B18");
//		student.setCourseTitle("IT");
//		student.setLevelSemester("L2S2");
//		student.setShortTermBal(21);
//		student.setLongTermBal(365);
//		
//		
//		Set<Roles> roles1 = new HashSet<Roles>();
//		Roles roleStudent = roleRepo.findByName(MRoles.ROLE_STUDENT).get();
//		
//		roles1.add(roleStudent);
//		
//		student.setRoles(roles1);
//		
//		stdRepo.save(student);
//		
//		UgsStaff ugs = new UgsStaff();
//		ugs.setFirstName("Malith");
//		ugs.setLastName("fernando");
//		ugs.setEmail("fayaz@gmail.com");
//		ugs.setContactNo("778366330");
//		ugs.setPassword("$2a$10$NuUxIue/pmtKrU1t/MAo/em4cgD8UDsOh09xVgqmBAxOGzWtFc31O");
//		
//		Set<Roles> roles = new HashSet<Roles>();
//		Roles roleAdmin = roleRepo.findByName(MRoles.ROLE_ADMIN).get();
//		Roles roleUgs = roleRepo.findByName(MRoles.ROLE_UGS).get();
//		
//		roles.add(roleUgs);
//		roles.add(roleAdmin);
//		
//		ugs.setRoles(roles);
//		
//		repo.save(ugs);
		
//		AssistentRegistrar assistentRegistrar = new AssistentRegistrar();
//		assistentRegistrar.setFirstName("Malith");
//		assistentRegistrar.setLastName("fernando");
//		assistentRegistrar.setEmail("ar@gmail.com");
//		assistentRegistrar.setContactNo("778366330");
//		assistentRegistrar.setPassword("$2a$10$NuUxIue/pmtKrU1t/MAo/em4cgD8UDsOh09xVgqmBAxOGzWtFc31O");
//		
//		Set<Roles> roles2 = new HashSet<Roles>();
//		Roles roleAR = roleRepo.findByName(MRoles.ROLE_AR).get();
//		
//		roles2.add(roleAR);
//		
//		assistentRegistrar.setRoles(roles2);
//		
//		assistentRegistrarRepository.save(assistentRegistrar);
		
		
	}
//	@Bean
//	public WebMvcConfigurer configure() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/*").allowedOrigins("http://localhost:3000");
//			}
//		};
//	}

}
