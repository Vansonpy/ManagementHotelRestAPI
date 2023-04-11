package com.example.reafult.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.reafult.JwtTokenProvider;
import com.example.reafult.dto.UsersDTO;
import com.example.reafult.entities.CustomUserDetails;
import com.example.reafult.entities.Users;
import com.example.reafult.services.UsersService;
import com.example.reafult.services.impl.UserDetailsServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UsersService usersService;
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	private JwtTokenProvider tokenProvider;
	@Autowired
	AuthenticationManager authenticationManager;


	@PostMapping(value = "/login")
	public ResponseEntity<UsersDTO> checkLogin(@RequestBody Users userRequest, HttpServletResponse response) throws Exception {
		try {
			
			UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userRequest.getUserName());
			if(userDetails!=null) {
				Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(userRequest.getUserName(), userRequest.getPassword()));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				String token = tokenProvider.createToken((CustomUserDetails) authentication.getPrincipal());
		        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		        UsersDTO usersDTO = new UsersDTO();
		        usersDTO.setUserName(userDetails.getUsername());
		        for (GrantedAuthority authorities : userDetails.getAuthorities()) {
		        	usersDTO.setRole(authorities.toString());
				}		        
				return new ResponseEntity<UsersDTO>(usersDTO, HttpStatus.OK);
			}
			
			return new ResponseEntity<UsersDTO>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UsersDTO>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/registerMember")
	public ResponseEntity<UsersDTO> registerMember(@Valid @RequestBody Users userRequest) throws Exception {
		try {
			UsersDTO currentUser = usersService.save(userRequest);
			return new ResponseEntity<UsersDTO>(currentUser, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UsersDTO>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/viewAllMember")
	public ResponseEntity<List<UsersDTO>> viewAllMember() throws Exception {
		try {
			List<UsersDTO> listUsersDTO = usersService.findAll();
			if (listUsersDTO.isEmpty()) {
				return new ResponseEntity<List<UsersDTO>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<UsersDTO>>(listUsersDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<UsersDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/viewByUserName/{userName}")
	public ResponseEntity<UsersDTO> viewUser(@PathVariable("userName") String userName) throws Exception {
		try {
			UsersDTO usersDTO = usersService.findUserName(userName);
			if (usersDTO == null) {
				return new ResponseEntity<UsersDTO>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<UsersDTO>(usersDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UsersDTO>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/checkUserNameAndEmail")
	public ResponseEntity<UsersDTO> checkUserNameAndEmail(@RequestBody Users userRequest) throws Exception {
		try {
			UsersDTO usersDTO = usersService.findUserNameAndEmail(userRequest.getUserName(), userRequest.getEmail());
			if (usersDTO == null) {
				return new ResponseEntity<UsersDTO>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<UsersDTO>(usersDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UsersDTO>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/forgetPassword/{id}")
	public ResponseEntity<UsersDTO> forgotPassword(@PathVariable("id") Integer id, @RequestBody Users userRequest)
			throws Exception {
		try {
			UsersDTO currentUser = usersService.findById(id);
			if (currentUser == null || userRequest.getUserId() != id) {
				return new ResponseEntity<UsersDTO>(HttpStatus.NO_CONTENT);
			}
			currentUser = usersService.save(userRequest);
			return new ResponseEntity<UsersDTO>(currentUser, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UsersDTO>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/updateMember/{id}")
	public ResponseEntity<UsersDTO> doUpdateMember(@PathVariable("id") Integer id,
			@Valid @RequestBody Users userRequest) throws Exception {
		try {
			UsersDTO currentUser = usersService.findById(id);
			if (currentUser == null || userRequest.getUserId() != id) {
				return new ResponseEntity<UsersDTO>(HttpStatus.NO_CONTENT);
			}
			currentUser = usersService.save(userRequest);
			return new ResponseEntity<UsersDTO>(currentUser, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UsersDTO>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/deleteMember/{id}")
	public ResponseEntity<UsersDTO> delete(@PathVariable(value = "id") Integer id) throws Exception {
		try {
			UsersDTO userDTO = usersService.findById(id);
			if (userDTO == null) {
				return new ResponseEntity<UsersDTO>(HttpStatus.NO_CONTENT);
			}
			usersService.delete(id);
			return new ResponseEntity<UsersDTO>(userDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UsersDTO>(HttpStatus.BAD_REQUEST);
		}
	}
}
