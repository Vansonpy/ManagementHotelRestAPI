package com.example.reafult.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.reafult.entities.Pages;

@Repository(value = "pageRepository")
public interface PageRepository extends JpaRepository<Pages, Integer>{
	List<Pages> findByTitle(String title);
	List<Pages> findByPageName(String pageName);
	List<Pages> findByPageNameAndLocation(String pageName,String location);
}
