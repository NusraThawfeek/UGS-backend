package com.example.demo.controller.all;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.ForgotUsernameOrPasswordRequest;
import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.response.UserDetailsResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.service.impl.AdminServiceImpl;
import com.example.demo.service.impl.UserDetailsImpl;
import com.example.demo.utils.JwtUtil;



@RestController
@CrossOrigin
@RequestMapping("/all")
public class LoginController {
	private static Logger log = Logger.getLogger(LoginController.class);
	@Autowired
	private AdminServiceImpl service;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil util;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<UserDetailsResponse> loginUser(@Valid @RequestBody LoginRequest request) {
		log.info("Login method at com.itfac.ugs.controllers.all.LoginController called with username "
				+ request.getUsername());
		String token = util.generateToken(request.getUsername());
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity
				.ok(new UserDetailsResponse(token, userDetails.getUserId(), userDetails.getNameToBeAppeared(), roles));
	}

	@GetMapping("/username")
	public ResponseEntity<UserResponse> response(Principal principal) {
		return ResponseEntity.ok(new UserResponse(principal.getName()));
	}
	
//	TODO:FOrget Username or Password
	@PostMapping("/changePassword")
	public ResponseEntity<String> forgotUsernameOrPassword(@Valid @RequestBody ForgotUsernameOrPasswordRequest fupr){
		String username = fupr.getUsername();
		Optional<User> optional = service.findUserByUserName(username);
		if (optional.isEmpty()) {
			throw new UsernameNotFoundException("User with email: " + username + " not found");
		}
		
		User user = optional.get();
		service.changePassword(user);
		return ResponseEntity.ok("Password for the username " + username + " was successfully changed and Check your mail to update the password"  );
	}
}
