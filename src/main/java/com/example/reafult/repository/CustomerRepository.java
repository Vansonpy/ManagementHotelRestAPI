package com.example.reafult.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.reafult.entities.Customer;

@Repository(value = "customerRepository")
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	Customer findByEmail(String email);
}
