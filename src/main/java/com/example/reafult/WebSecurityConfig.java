package com.example.reafult;

import com.example.reafult.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Bean
    public JwtTokenAuthenticationFilter jwtTokenAuthenticationFilterr() {
        return new JwtTokenAuthenticationFilter();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/api/rooms/viewAllRoom", "/api/users/login", "/api/users/registerMember","/viewByPagesNameAndLocation").permitAll();
        http.authorizeRequests().antMatchers("/api/users/viewAllMember","/api/sales/viewAllSale","/api/rooms/addRoom","/api/rooms/updateRoom","/api/pages/addPage",
        		"/api/pages/updatePage/{id}","/api/pages/deletePage/{id}","/api/contact/viewAllContact").hasAuthority("AdminRole");
        http.authorizeRequests().antMatchers("/api/users/viewByUserName/{userName}","/api/users/deleteMember/{id}"
        		,"/api/sales/viewSalesUser").hasAnyAuthority("AdminRole","UserRole");
        http.addFilterBefore
                (jwtTokenAuthenticationFilterr(),
                UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Get AuthenticationManager bean
        return super.authenticationManagerBean();
    }
    
}

