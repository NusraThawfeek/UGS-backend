package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.filter.SecurityFilter;


@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

	@Autowired
	private InvalidUserAuthEntryPoint authenticationEntryPoint;
	
	@Autowired	
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private SecurityFilter securityFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(encoder);
	}
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()

		.antMatchers("/all/**").permitAll()
//		.antMatchers("/*").permitAll()
//		.antMatchers("/*/*").permitAll()
//		.antMatchers("/*/*/*").permitAll()
//		.antMatchers("/*/*/*/*").permitAll()
		.antMatchers("/admin/**","/admin/ugs/getUserInfo").hasAuthority("ROLE_UGS")
		

		.antMatchers("/all/login").permitAll()
		.antMatchers("/admin/register/student/single").hasAuthority("ROLE_ADMIN")
		.antMatchers("/admin/register/fac").permitAll()
		
		.antMatchers("/getrequestbyrid/{rid}").permitAll()
		.antMatchers("/meetings/addAgenda").permitAll()
		.antMatchers("/meetings/addMinute").permitAll() 
		.antMatchers("/getrequestbyfacid/{facid}").permitAll()
		.antMatchers("/attendance/getByAttendance/{meetingId}/{attendance}").permitAll()
		.antMatchers("/attendance/apology/{meetingId}").permitAll() 
		.antMatchers("/meetings/upcoming").permitAll()
		.antMatchers("/meetings/pastMeeting").permitAll()
		.antMatchers("/request/addToAgenda").permitAll()
		
		.antMatchers("/request/leaverequest").hasAuthority("ROLE_STUDENT")
		.antMatchers("/pastrequest/leaverequestbyrid/{rid}").hasAnyAuthority("ROLE_STUDENT","ROLE_FAC")
		.antMatchers("/pastrequest/getallleaverequest").permitAll()
		.antMatchers("/pastrequest/leaverequest/{sid}").hasAuthority("ROLE_STUDENT")
		.antMatchers("/updateleaverequest").hasAuthority("ROLE_AR")
		
		.antMatchers("/request/alternativemodulerequest").hasAuthority("ROLE_STUDENT")
		.antMatchers("/pastrequest/alternativemodulerequestbyrid/{rid}").permitAll()
		.antMatchers("/pastrequest/getallalternativemodulerequest").permitAll()
		.antMatchers("/pastrequest/leaverequest/{sid}").hasAuthority("ROLE_STUDENT")
		.antMatchers("/updateAlterRequest").hasAuthority("ROLE_AR")
		
		.antMatchers("/request/latemodulechangerequest").hasAuthority("ROLE_STUDENT")
		.antMatchers("/pastrequest/latemodulechangerequestbyrid/{rid}").permitAll()
		.antMatchers("/pastrequest/getallLateModuleChangerequest").permitAll()
		.antMatchers("/pastrequest/latemodulechangerequest/{sid}").hasAuthority("ROLE_STUDENT")
		.antMatchers("/updatelatemodulechangerequest").hasAuthority("ROLE_AR")
		.antMatchers("/request/download_sgpa_late/{rid}").permitAll()
		
		.antMatchers("/request/otherappeal").hasAuthority("ROLE_STUDENT")
		.antMatchers("/pastrequest/otherappealbyrid/{rid}").permitAll()
		.antMatchers("/pastrequest/getallAppeal").permitAll()
		.antMatchers("/pastrequest/otherappeal/{sid}").hasAuthority("ROLE_STUDENT")
		.antMatchers("/updateappeal").hasAuthority("ROLE_AR")
		.antMatchers("/request/download_sgpa_appeal/{rid}").permitAll()
		
		.antMatchers("/request/download_annex/{rid}").permitAll()
		
		.antMatchers("/addmodule").hasAuthority("ROLE_UGS")
		.antMatchers("/getstudent/{uid}").permitAll()

		.antMatchers("/facmember").permitAll()
		.antMatchers("/facmember/{facId}").permitAll()
		
		
		.antMatchers("/PastReq/{uid}").hasAnyAuthority("ROLE_AR","ROLE_DUGS","ROLE_DEAN","ROLE_HOD_IT","ROLE_HOD_IDS","ROLE_HOD_CM","ROLE_ACADEMICADVISOR","ROLE_FAC_MEMBER")
		.antMatchers("/PastReqAR").hasAuthority("ROLE_AR")
		.antMatchers("/NewReqAcademicAdvisor/{uid}").hasAuthority("ROLE_ACADEMICADVISOR")
		.antMatchers("/NewReqHOD/{uid}").hasAnyAuthority("ROLE_HOD_IT","ROLE_HOD_IDS","ROLE_HOD_CM")
		.antMatchers("/PastReqAR").hasAuthority("ROLE_AR")
		.antMatchers("/NewReqDean/{uid}").hasAuthority("ROLE_DEAN")
		.antMatchers("/NewReqDUGS/{uid}").hasAuthority("ROLE_DUGS")
		.antMatchers("/NewReqAR").hasAuthority("ROLE_AR")
		.antMatchers("/AddRequestToAgenda").hasAuthority("ROLE_AR")
		
		
		.antMatchers("/StudentReqByIndexNo/{indexNo}").hasAnyAuthority("ROLE_FAC_MEMBER","ROLE_AR")
		.antMatchers("/StudentByIndexNo/{indexNo}").hasAnyAuthority("ROLE_FAC_MEMBER","ROLE_AR")
		.antMatchers("/Student").hasAnyAuthority("ROLE_FAC_MEMBER","ROLE_AR")
		
		
		.antMatchers("/Commented/Request/{rid}").hasAnyAuthority("ROLE_AR","ROLE_DUGS","ROLE_DEAN","ROLE_HOD_IT","ROLE_HOD_IDS","ROLE_HOD_CM","ROLE_ACADEMICADVISOR","ROLE_FAC_MEMBER")
		
		.antMatchers("/Commented/update").hasAnyAuthority("ROLE_DUGS","ROLE_DEAN","ROLE_HOD_IT","ROLE_HOD_IDS","ROLE_HOD_CM","ROLE_ACADEMICADVISOR")
		.antMatchers("/Commented/RequestbyIds/{rid},{uid}").hasAnyAuthority("ROLE_DUGS","ROLE_DEAN","ROLE_HOD_IT","ROLE_HOD_IDS","ROLE_HOD_CM","ROLE_ACADEMICADVISOR")
		
		.antMatchers("/Commented/EditByAcademicAdvisor/{rid},{uid}").hasAuthority("ROLE_ACADEMICADVISOR")
		.antMatchers("/Commented/EditByHOD/{rid},{uid}").hasAnyAuthority("ROLE_HOD_IT","ROLE_HOD_IDS","ROLE_HOD_CM")
		.antMatchers("/Commented/EditByDean/{rid},{uid}").hasAuthority("ROLE_DEAN")
		.antMatchers("/Commented/EditByDugs/{rid},{uid}").hasAuthority("ROLE_UGS")
		

		.antMatchers("/meetings").hasAuthority("ROLE_AR")
		.antMatchers("/meetings/get").hasAnyAuthority("ROLE_AR","ROLE_FAC_MEMBER")
		.antMatchers("/meetings/{id}").hasAnyAuthority("ROLE_AR","ROLE_FAC_MEMBER")
		.antMatchers("/meetings/mail/{id}").hasAuthority("ROLE_AR")
		.antMatchers("/subcomittee/**").hasAuthority("ROLE_AR")
		.antMatchers("/subcomittee/fileupload/{id}").hasAuthority("ROLE_FAC_MEMBER")
		.antMatchers("/subcomittee/statusupdate/{id}").hasAuthority("ROLE_AR")
		.antMatchers("/attendance/create").hasAuthority("ROLE_AR")
		.antMatchers("/attendance/getByMeetingId/{meetingId}").hasAuthority("ROLE_AR")
		.antMatchers("/attendance/getByMeetingDate/{date}").hasAuthority("ROLE_AR")
	
		
	
		

		.anyRequest().authenticated()
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(authenticationEntryPoint)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
		;

		http.cors();
	}

//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring()
//		.antMatchers(HttpMethod.POST,"/all/login")
//		.antMatchers(HttpMethod.POST,"/admin/register");
//	
//	}

}
