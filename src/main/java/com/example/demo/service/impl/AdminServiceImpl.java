package com.example.demo.service.impl;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.ChangePasswordRequest;
import com.example.demo.dto.request.StudentBatchRequest;
import com.example.demo.dto.request.StudentSingleRegister;
import com.example.demo.entity.AssistentRegistrar;
import com.example.demo.entity.FACMember;
import com.example.demo.entity.MRoles;
import com.example.demo.entity.Roles;
import com.example.demo.entity.Student;
import com.example.demo.entity.UgsStaff;
import com.example.demo.entity.User;
import com.example.demo.repository.AssistentRegistrarRepository;
import com.example.demo.repository.FACMemberRepository;
import com.example.demo.repository.RolesRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UgsStaffRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.interfaces.IAdminService;
import com.example.demo.utils.EmailUtil;


@Service
public class AdminServiceImpl implements IAdminService, UserDetailsService {
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private FACMemberRepository facRepo;

	@Autowired
	private AssistentRegistrarRepository arRepo;

	@Autowired
	private UgsStaffRepository ugsRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RolesRepository roleRepo;

	@Autowired
	private EmailUtil email;

	@Override
	public String saveStudent(StudentSingleRegister studentReq) {
		if (userRepo.existsByEmail(studentReq.getEmail())) {
			return null;
		}

		if (studentRepo.existsByIndexNo(studentReq.getIndexNumber())) {
			return null;
		}
		Student student = new Student();
		student.setFirstName(studentReq.getFirstName());
		student.setLastName(studentReq.getLastName());
		student.setNameToBeAppeared(studentReq.getNameToBeAppeared());
		student.setEmail(studentReq.getEmail());
		String password = encoder.encode(studentReq.getIndexNumber()).substring(0, 10);

		student.setPassword(encoder.encode(password));
		student.setContactNo(studentReq.getContactNumber());
		student.setBatchYear(studentReq.getBatch());
		student.setCourseTitle(studentReq.getCourse());
		student.setLevelSemester(studentReq.getLevelSem());
		student.setIndexNo(studentReq.getIndexNumber());
		FACMember academicAdvisor = (FACMember) userRepo.findByEmail(studentReq.getAcademicAdvisorEmail()).get();
		student.setAcademicAdvisor(academicAdvisor);

		Set<Roles> roles = new HashSet<Roles>();
		Roles stdRole = roleRepo.findByName(MRoles.ROLE_STUDENT).get();
		roles.add(stdRole);
		student.setRoles(roles);

		Student savedStudent = studentRepo.save(student);
		if (savedStudent != null) {
			String message = "Hello from Division of Undergraduate Studies \n"
					+ "Please use the following password to log in to the system " + "\n " + password + "\n"
					+ "Please be kind to change the password soon after your first login";
			email.sendEmail(student.getEmail(), "One Time Login Password", message);
		}
		return savedStudent.getIndexNo();
	}

	@Override
	public int saveAll(List<StudentBatchRequest> students) {
//		user already exists scenario what should happen do we send the results or highlight them in frontEnd
		for (int i = 0; i < students.size(); i++) {
			StudentBatchRequest oneStudent = students.get(i);
			if (userRepo.existsByEmail(oneStudent.getEmail())) {
				return i;
			}

			if (studentRepo.existsByIndexNo(oneStudent.getIndexNo())) {
				return i;
			}

			Student student = new Student();
			student.setFirstName(oneStudent.getFirstName());
			student.setLastName(oneStudent.getLastName());
			student.setNameToBeAppeared(oneStudent.getNameToBeAppeared());
			student.setEmail(oneStudent.getEmail());
			String password = encoder.encode(oneStudent.getIndexNo()).substring(0, 10);

			student.setPassword(encoder.encode(password));
			student.setContactNo(oneStudent.getContactNo());
			student.setBatchYear(oneStudent.getBatchYear());
			student.setCourseTitle(oneStudent.getCourseTitle());
			student.setLevelSemester(oneStudent.getLevelSemester());
			student.setIndexNo(oneStudent.getIndexNo());
			Set<Roles> roles = new HashSet<Roles>();
			Roles stdRole = roleRepo.findByName(MRoles.ROLE_STUDENT).get();
			roles.add(stdRole);
			student.setRoles(roles);
			Student savedStudent = studentRepo.save(student);
			if (savedStudent != null) {
				String message = "Hello from Division of Undergraduate Studies \n"
						+ "Please use the following password to log in to the system " + "\n " + password + "\n"
						+ "Please be kind to change the password soon after your first login";
				email.sendEmail(student.getEmail(), "One Time Login Password", message);
			}
		}
		return students.size();
	}

	@Override
	public String saveFacMember(FACMember member) {
		String email = facRepo.save(member).getEmail();
		return email;
	}

	@Override
	public String saveAR(AssistentRegistrar ar) {
		String email = arRepo.save(ar).getEmail();
		return email;
	}

	@Override
	public String saveUGS(UgsStaff ugs) {
		String email = ugsRepo.save(ugs).getEmail();
		return email;
	}

	@Override
	public Optional<User> findUserByUserName(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public List<FACMember> getAllAcademicAdvisors() {
		return facRepo.findAllByIsAcademicAdvisor(true);
	}
	
	@Override
	public Roles getRole(MRoles role) {
		return roleRepo.findByName(role).get();
	}
	
	

	@Override
	public Student getStudent(long id) {
		return studentRepo.findById(id).get();
	}

	@Override
	public FACMember getFacMember(long id) {
		return facRepo.findById(id).get();
	}

	@Override
	public AssistentRegistrar getAr(long id) {
		return arRepo.findById(id).get();
	}

	@Override
	public UgsStaff getUgsStaff(long id) {
		return ugsRepo.findById(id).get();
	}

	
	@Override
	public int updatePassword(ChangePasswordRequest req) {
		User user = userRepo.findById(req.getId()).get();
		if (encoder.matches(req.getOldPassword(), user.getPassword())) {
			user.setPassword(encoder.encode(req.getNewPassword()));
			userRepo.save(user);
			return 1;
		}
		return 0;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optional = findUserByUserName(username);
		if (optional.isEmpty()) {
			throw new UsernameNotFoundException("User with email: " + username + " not found");
		}

		User user = optional.get();
		return UserDetailsImpl.build(user);
	}

	@Override
	public void changePassword(User user) {
		String password = encoder.encode(user.getPassword()).substring(0, 10);
		user.setPassword(encoder.encode(password));
		userRepo.save(user);
		String message = "Hello from Division of Undergraduate Studies \n"
				+ "Please use the following password to log in to the system " + "\n " + password + "\n"
				+ "Please be kind to change the password soon after your first login";
		email.sendEmail(user.getEmail(), "Password Change for UGS-IT ", message);
		
		
	}

}

