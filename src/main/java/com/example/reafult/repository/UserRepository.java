package com.example.reafult.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.reafult.entities.Users;

@Repository(value = "userRepository")
public interface UserRepository extends JpaRepository<Users, Integer>{
	Users findByUserNameAndPassword(String userName, String password);
	Users findByUserName(String userName);
	Users findByUserNameAndEmail(String userName, String email);
	Users findByEmail(String email);
}
