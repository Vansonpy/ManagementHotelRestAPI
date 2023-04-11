package com.example.reafult.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.reafult.entities.Contact;

@Repository(value = "contactRepository")
public interface ContactRepository extends JpaRepository<Contact, Integer>{
}
