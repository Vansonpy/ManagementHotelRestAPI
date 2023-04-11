package com.example.reafult.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.reafult.entities.FileDB;

@Repository(value = "fileDBRepository")
@EnableJpaRepositories
public interface FileDBRepository extends JpaRepository<FileDB, String>{
    List<FileDB> findByPage(Integer pageId);
}
