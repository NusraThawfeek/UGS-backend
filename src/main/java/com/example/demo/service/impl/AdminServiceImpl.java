package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.example.demo.dto.request.AcadAdvisorBatchRequest;
import com.example.demo.dto.request.ChangePasswordRequest;
import com.example.demo.dto.request.FACRequest;
import com.example.demo.dto.request.FacAcademicRequest;
import com.example.demo.dto.request.ModuleAdd;
import com.example.demo.dto.request.RegistrationRequestAcademic;
import com.example.demo.dto.request.StudentBatchRequest;
import com.example.demo.dto.request.StudentSingleRegister;
import com.example.demo.entity.AssistentRegistrar;
import com.example.demo.entity.FACMember;
import com.example.demo.entity.MRoles;
import com.example.demo.entity.Module1;
import com.example.demo.entity.Roles;
import com.example.demo.entity.Student;
import com.example.demo.entity.UgsStaff;
import com.example.demo.entity.User;
import com.example.demo.repository.AssistentRegistrarRepository;
import com.example.demo.repository.FACMemberRepository;
import com.example.demo.repository.ModuleRepository;
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

	@Autowired
	private ModuleRepository moduleRepository;

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
		student.setShortTermBal(21);
		student.setLongTermBal(365);

		Set<Roles> roles = new HashSet<Roles>();
		Roles ROLESTUDENT = roleRepo.findByName(MRoles.ROLE_STUDENT).get();
		roles.add(ROLESTUDENT);
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
	public List<Object> saveAll(List<StudentBatchRequest> students) {
		List<Object> response = new ArrayList<>();
//		Hashmap to store registered user's in "index" : "Success" pair
		HashMap<String, String> registeredUser = new HashMap<String, String>();

//		HashMap to store with already exists message
		HashMap<String, String> alreadyExists = new HashMap<String, String>();
		for (int i = 0; i < students.size(); i++) {
			StudentBatchRequest oneStudent = students.get(i);
//			if (userRepo.existsByEmail(oneStudent.getEmail())) {
//				alreadyExists.put(oneStudent.getIndexNo(), "Already exist user");
//			}

			if (studentRepo.existsByIndexNo(oneStudent.getIndexNo())) {
				alreadyExists.put(oneStudent.getIndexNo(), "Already exist user");
				continue;
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
			student.setIndexNo(oneStudent.getIndexNo());
			Set<Roles> roles = new HashSet<Roles>();
			Roles ROLESTUDENT = roleRepo.findByName(MRoles.ROLE_STUDENT).get();
			roles.add(ROLESTUDENT);
			student.setRoles(roles);
			Student savedStudent = studentRepo.save(student);
			if (savedStudent != null) {
				String message = "Hello from Division of Undergraduate Studies \n"
						+ "Please use the following password to log in to the system " + "\n " + password + "\n"
						+ "Please be kind to change the password soon after your first login";
				email.sendEmail(student.getEmail(), "One Time Login Password", message);
				registeredUser.put(savedStudent.getIndexNo(), "Success");
			}
		}
		response.add(alreadyExists);
		response.add(registeredUser);
		return response;
	}

	@Override
	public String saveFacMember(FACRequest request) {
		if (userRepo.existsByEmail(request.getEmail())) {
			return null;
		}

		FACMember member = new FACMember();
		member.setTitle(request.getTitle());
		member.setFirstName(request.getFirstName());
		member.setLastName(request.getLastName());
		member.setNameToBeAppeared(request.getNameToBeAppeared());
		member.setEmail(request.getEmail());
		member.setContactNo(request.getContactNumber());
		member.setExtCode(request.getExtCode());
		member.setDepartment(request.getDepartment());
		member.setAcademicAdvisor(request.isAcadAvisor());
		String password = encoder.encode(request.getEmail()).substring(0, 10);

		member.setPassword(encoder.encode(password));
		Set<Roles> roles = new HashSet<Roles>();
		Roles ROLEFACMEMBER = roleRepo.findByName(MRoles.ROLE_FAC_MEMBER).get();
		if (request.isAcadAvisor()) {
			Roles ROLEACADEMICADVISOR = roleRepo.findByName(MRoles.ROLE_ACADEMICADVISOR).get();
			roles.add(ROLEACADEMICADVISOR);
		}
		roles.add(ROLEFACMEMBER);
		member.setRoles(roles);
		FACMember save = facRepo.save(member);
		if (save != null) {
			String message = "Hello from Division of Undergraduate Studies \n"
					+ "Please use the following password to log in to the system " + "\n " + password + "\n"
					+ "Please be kind to change the password soon after your first login";
			email.sendEmail(save.getEmail(), "One Time Login Password", message);
		}
		;
		return save.getEmail();
	}

	@Override
	public String saveAR(RegistrationRequestAcademic request) {
		AssistentRegistrar ar = new AssistentRegistrar();
		ar.setTitle(request.getTitle());
		ar.setFirstName(request.getFirstName());
		ar.setLastName(request.getLastName());
		ar.setNameToBeAppeared(request.getNameToBeAppeared());
		ar.setEmail(request.getEmail());
		String password = encoder.encode(request.getEmail()).substring(0, 10);
		ar.setPassword(encoder.encode(password));
		ar.setContactNo(request.getContactNumber());
		Set<Roles> roles = new HashSet<Roles>();
		Roles ROLEAR = roleRepo.findByName(MRoles.ROLE_AR).get();
		roles.add(ROLEAR);
		ar.setRoles(roles);
		AssistentRegistrar savedAr = arRepo.save(ar);
		if (savedAr != null) {
			String message = "Hello from Division of Undergraduate Studies \n"
					+ "Please use the following password to log in to the system " + "\n " + password + "\n"
					+ "Please be kind to change the password soon after your first login";
			email.sendEmail(savedAr.getEmail(), "One Time Login Password", message);
		}
		return savedAr.getEmail();
	}

	@Override
	public String saveUGS(RegistrationRequestAcademic request) {
		UgsStaff ugs = new UgsStaff();
		ugs.setTitle(request.getTitle());
		ugs.setFirstName(request.getFirstName());
		ugs.setLastName(request.getLastName());
		ugs.setNameToBeAppeared(request.getNameToBeAppeared());
		ugs.setEmail(request.getEmail());
		String password = encoder.encode(request.getEmail()).substring(0, 10);
		ugs.setPassword(encoder.encode(password));
		ugs.setContactNo(request.getContactNumber());
		Set<Roles> roles = new HashSet<Roles>();
		Roles ROLEUGS = roleRepo.findByName(MRoles.ROLE_UGS).get();
		roles.add(ROLEUGS);
		ugs.setRoles(roles);
		UgsStaff savedUgs = ugsRepo.save(ugs);
		if (savedUgs != null) {
			String message = "Hello from Division of Undergraduate Studies \n"
					+ "Please use the following password to log in to the system " + "\n " + password + "\n"
					+ "Please be kind to change the password soon after your first login";
			email.sendEmail(savedUgs.getEmail(), "One Time Login Password", message);
		}
		return savedUgs.getEmail();
	}

	@Override
	public Optional<User> findUserByUserName(String email) {
		return userRepo.findByEmail(email);
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

// Change Password from forgot username or password
	@Override
	public void changePassword(User user) {
		String password = encoder.encode(user.getPassword()).substring(0, 10);
		user.setPassword(encoder.encode(password));
		userRepo.save(user);
		String message = "Hello from Division of Undergraduate Studies \n"
				+ "Please use the following password to log in to the system " + "\n " + password + "\n"
				+ "This message was was sent for the request of password change";
		email.sendEmail(user.getEmail(), "Request for Password Change - Division UGS-IT UOM ", message);

	}

//	Find all Academic Advisors 
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

//	Student
	@Override
	public List<String> getAllStudentByBatch(String batch) {
		return studentRepo.findAllIndexByBatchYear(batch);
	}

	@Override
	public String setAcademicAdvisor(AcadAdvisorBatchRequest request) {
		Student student = studentRepo.findByIndexNo(request.getIndexNumber()).get();
		FACMember acad = (FACMember) userRepo.findByEmail(request.getAcadAdvisorEmail()).get();
		student.setAcademicAdvisor(acad);
		return studentRepo.save(student).getIndexNo();
	}

	@Override
	public String setModule(ModuleAdd request) {
		if(moduleRepository.existsById(request.getModuleCode())) {
			return null;
		}
		Module1 module = new Module1();
		module.setDegreeProgramme(request.getDegreeProgramme());
		module.setSemester(request.getSemester());
		module.setDep(request.getDepartment());
		module.setMcode(request.getModuleCode());
		module.setMtitle(request.getModuleName());
		module.setCredits(request.getCredits());
		return moduleRepository.save(module).getMcode();
	}

	@Override
	public String setFacRoleAcademicAdvisor(FacAcademicRequest request) {
		FACMember fac = (FACMember) userRepo.findByEmail(request.getEmail()).get();
		if (fac.isAcademicAdvisor()) {
			fac.setAcademicAdvisor(true);
			Set<Roles> roles = fac.getRoles();
			Roles ROLEACADEMICADVISOR = roleRepo.findByName(MRoles.ROLE_ACADEMICADVISOR).get();
			roles.add(ROLEACADEMICADVISOR);
			FACMember saved = facRepo.save(fac);
			return "Role Added Successfully with for MR/MRS :" + saved.getNameToBeAppeared();
		} else {
			return "Already Academic Advisor";
		}
		
	}

//	facDean

}
