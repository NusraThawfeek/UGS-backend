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

		.antMatchers("/*").permitAll()
		.antMatchers("/*/*").permitAll()
		.antMatchers("/*/*/*").permitAll()
		.antMatchers("/*/*/*/*").permitAll()

		// Shaja
		.antMatchers("/admin/**","/admin/ugs/getUserInfo").hasAuthority("ROLE_UGS")
		.antMatchers("/all/login").permitAll()
		
		//Razan
		.antMatchers("/request/leaverequest").hasAuthority("ROLE_STUDENT")
		.antMatchers("/pastrequest/leaverequestbyrid/{rid}").hasAnyAuthority("ROLE_STUDENT","ROLE_FAC")
		.antMatchers("/pastrequest/getallleaverequest").permitAll()
		.antMatchers("/pastrequest/leaverequestbyrid/{rid}").permitAll()
		.antMatchers("/pastrequest/getallleaverequest").hasAuthority("ROLE_AR")
		.antMatchers("/decisionleaverequest").hasAuthority("ROLE_AR")
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
		.antMatchers("/updateSendToFACBoard").hasAuthority("ROLE_FAC_MEMBER")
		
		.antMatchers("/postmemo").hasAuthority("ROLE_FAC_MEMBER")
		.antMatchers("/pastmemo/{mid}").hasAnyAuthority("ROLE_FAC_MEMBER", "ROLE_AR")
		.antMatchers("/pastallmemo").hasAuthority("ROLE_FAC_MEMBER")
		.antMatchers("/pastmemobyfacid/{facId}").hasAuthority("ROLE_FAC_MEMBER")
		.antMatchers("/updatememo").hasAuthority("ROLE_AR")
		.antMatchers("/memo/download_annex/{mid}").hasAnyAuthority("ROLE_FAC_MEMBER", "ROLE_AR")
		
		.antMatchers("/addmodule").hasAuthority("ROLE_UGS")
		.antMatchers("/getAllModule").hasAuthority("ROLE_UGS")
		.antMatchers("/getstudent/{uid}").permitAll()

		.antMatchers("/facmember").permitAll()
		.antMatchers("/facmember/{facId}").permitAll()
		
		
		//Nuzra
		.antMatchers("/PastReq/{uid}").hasAnyAuthority("ROLE_AR","ROLE_DUGS","ROLE_DEAN","ROLE_HOD","ROLE_ACADEMICADVISOR")
		.antMatchers("/PastReqAR").hasAuthority("ROLE_AR")
		.antMatchers("/NewReqAcademicAdvisor/{uid}").hasAuthority("ROLE_ACADEMICADVISOR")
		.antMatchers("/NewReqHOD/{uid}").hasAnyAuthority("ROLE_HOD")
		.antMatchers("/PastReqAR").hasAuthority("ROLE_AR")
		.antMatchers("/NewReqDean/{uid}").hasAuthority("ROLE_DEAN")
		.antMatchers("/NewReqDUGS/{uid}").hasAuthority("ROLE_DUGS")
		.antMatchers("/NewReqAR").hasAuthority("ROLE_AR")
		.antMatchers("/AddRequestToAgenda").hasAuthority("ROLE_AR")
		
		.antMatchers("/StudentReqByIndexNo/{indexNo}").hasAnyAuthority("ROLE_FAC_MEMBER","ROLE_AR")
		.antMatchers("/StudentByIndexNo/{indexNo}").hasAnyAuthority("ROLE_FAC_MEMBER","ROLE_AR")
		.antMatchers("/Student").hasAnyAuthority("ROLE_FAC_MEMBER","ROLE_AR")
		
		.antMatchers("/Commented/Request/{rid}").hasAnyAuthority("ROLE_AR","ROLE_DUGS","ROLE_DEAN","ROLE_HOD","ROLE_ACADEMICADVISOR")
		
		.antMatchers("/Commented/update").hasAnyAuthority("ROLE_DUGS","ROLE_DEAN","ROLE_HOD","ROLE_ACADEMICADVISOR")
		.antMatchers("/Commented/RequestbyIds/{rid},{uid}").hasAnyAuthority("ROLE_DUGS","ROLE_DEAN","ROLE_HOD","ROLE_ACADEMICADVISOR")
		.antMatchers("/Commented/EditByAcademicAdvisor/{rid},{uid}").hasAuthority("ROLE_ACADEMICADVISOR")
		.antMatchers("/Commented/EditByHOD/{rid},{uid}").hasAnyAuthority("ROLE_HOD")
		.antMatchers("/Commented/EditByDean/{rid},{uid}").hasAuthority("ROLE_DEAN")
		.antMatchers("/Commented/EditByDugs/{rid},{uid}").hasAuthority("ROLE_UGS")
		
		//Fayaz
				.antMatchers("/locations/get").permitAll()
		.antMatchers("/meetings").permitAll()
		.antMatchers("/meetings/get").hasAnyAuthority("ROLE_FAC_MEMBER","ROLE_AR")
		.antMatchers("/meetings/{id}").hasAuthority("ROLE_AR")				//update meeting
		.antMatchers("/meetings/{id}").hasAnyAuthority("ROLE_FAC_MEMBER","ROLE_AR","ROLE_UGS")  //get meeting by id

		.antMatchers("/meetings/mail/{id}").hasAuthority("ROLE_AR")
		.antMatchers("/subcomittee/**").hasAuthority("ROLE_AR")
				.antMatchers("/subcomittee/{id}").permitAll()
		.antMatchers("/subcomittee/fileupload/{id}").hasAuthority("ROLE_FAC_MEMBER")
		.antMatchers("/subcomittee/statusupdate/{id}").hasAuthority("ROLE_AR")
		.antMatchers("/attendance/create").hasAuthority("ROLE_AR")
		.antMatchers("/attendance/getByMeetingId/{meetingId}").hasAuthority("ROLE_AR")
		.antMatchers("/attendance/getByMeetingDate/{date}").hasAuthority("ROLE_AR")
		
		
		//Priya
		.antMatchers("/meetings/upcoming").hasAuthority("ROLE_AR")
		.antMatchers("/meetings/pastMeeting").hasAnyAuthority("ROLE_FAC_MEMBER","ROLE_AR","ROLE_UGS")
		.antMatchers("/meetings/addAgenda").hasAuthority("ROLE_AR")
		.antMatchers("/meetings/addMinute").hasAuthority("ROLE_AR")
		.antMatchers("/meetings/addMeetingMatters").hasAnyAuthority("ROLE_AR","ROLE_DUGS")
		.antMatchers("/meetings/addMinuteByDugs").hasAuthority("ROLE_DUGS")
		.antMatchers("/getrequestbyfacid/{facid}").hasAnyAuthority("ROLE_FAC_MEMBER","ROLE_AR","ROLE_UGS")
		.antMatchers("/getMemobyfacid/{facid}").hasAnyAuthority("ROLE_FAC_MEMBER","ROLE_AR","ROLE_UGS")
		.antMatchers("/attendance/getByAttendance/{meetingId}/{attendance}").hasAnyAuthority("ROLE_FAC_MEMBER","ROLE_AR","ROLE_UGS")
		.antMatchers("/attendance/apology/{meetingId}").hasAnyAuthority("ROLE_FAC_MEMBER","ROLE_AR","ROLE_UGS")
		.antMatchers("/meetings/getMattersByFacId/{id}").hasAnyAuthority("ROLE_FAC_MEMBER","ROLE_AR","ROLE_UGS")

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
