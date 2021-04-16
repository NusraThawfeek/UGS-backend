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
		.antMatchers("/all/login").permitAll()
		.antMatchers("/admin/register/student/single").hasAuthority("ROLE_ADMIN")
		.antMatchers("/admin/register/fac").permitAll()
		
		.antMatchers("/request/leaverequest").hasAuthority("ROLE_STUDENT")
		.antMatchers("/pastrequest/leaverequestbyrid/{rid}").permitAll()
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
		
		.antMatchers("/facmember").permitAll()
		.antMatchers("/facmember/{facId}").permitAll()
		
		
		.antMatchers("/PastReq/{uid}").permitAll()
		.antMatchers("/PastReqAR").permitAll()
		.antMatchers("/NewReqAcademicAdvisor/{uid}").permitAll()
		.antMatchers("/NewReqHOD/{uid}").permitAll()
		.antMatchers("/NewReqDean/{uid}").permitAll()
		.antMatchers("/NewReqDUGS/{uid}").permitAll()
		.antMatchers("/NewReqAR").permitAll()
		.antMatchers("/AddRequestToAgenda").permitAll()
		
		
		.antMatchers("/StudentReqByIndexNo/{indexNo}").permitAll()
		.antMatchers("/StudentByIndexNo/{indexNo}").permitAll()
		.antMatchers("/Student").permitAll()
		
		
		.antMatchers("/Commented/Request/{rid}").permitAll()
		.antMatchers("/Commented/RequestbyUid/{uid}").permitAll()
		.antMatchers("/Commented").permitAll()
		.antMatchers("/Commented/update").permitAll()
		.antMatchers("/Commented/RequestbyIds/{rid},{uid}").permitAll()
		
		.antMatchers("/Commented/EditByAcademicAdvisor/{rid},{uid}").permitAll()
		.antMatchers("/Commented/EditByHOD/{rid},{uid}").permitAll()
		.antMatchers("/Commented/EditByDean/{rid},{uid}").permitAll()
		.antMatchers("/Commented/EditByDugs/{rid},{uid}").permitAll()
		
		
		.anyRequest().authenticated()
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(authenticationEntryPoint)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilterBefore( securityFilter, UsernamePasswordAuthenticationFilter.class);
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
