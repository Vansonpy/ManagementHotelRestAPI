package com.example.reafult.services.impl;

import com.example.reafult.repository.UserRepository;
import com.example.reafult.entities.CustomUserDetails;
import com.example.reafult.entities.Users;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Users appUser =userRepository.findByUserName(userName);
		if (appUser == null) {
			logger.error("User not found! " + userName);
			throw new UsernameNotFoundException("User " + userName + " was not found in the database");
		}
		logger.info("Found: " + appUser);
		// [ROLE_USER, ROLE_ADMIN,..]
		String roleNames = userRepository.findById(appUser.getUserId()).get().getRole();
		
//		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
//		if (roleNames != null) {			
//				GrantedAuthority authority = new SimpleGrantedAuthority(roleNames);
//				grantList.add(authority);			
//		}
		GrantedAuthority grantList = null;
		if(roleNames!=null) {
			grantList = new SimpleGrantedAuthority(roleNames);
		}
		UserDetails userDetails = (UserDetails) new CustomUserDetails(appUser.getUserName(),
				appUser.getPassword(), grantList);
		return userDetails;
	}
}