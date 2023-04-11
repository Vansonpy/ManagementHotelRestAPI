package com.example.reafult.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.reafult.entities.Services;
@Repository(value = "serviceRepository")
public interface ServiceRepository extends JpaRepository<Services, Integer>{
	List<Services> findBySpecies(String species);
	Services findByName(String name);
}
